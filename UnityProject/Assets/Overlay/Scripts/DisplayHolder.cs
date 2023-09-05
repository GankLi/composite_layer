using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DisplayHolder : MonoBehaviour
{
    private Texture2D texture;
    private int textureId = 0;

    public bool isOes = false;
    public int VD_WIDTH = 2880;
    public int VD_HEIGHT = 1600;

    #region nativeFunction
    AndroidJavaObject nativeDisplayHolder;
#if UNITY_ANDROID
    private int _createVd(bool oesTexture)
    {
        if (nativeDisplayHolder != null)
        {
            return nativeDisplayHolder.Call<int>("createVd", oesTexture);
        }
        return 0;
    }

    private void _updateTexture2D()
    {
        if (nativeDisplayHolder != null)
        {
            nativeDisplayHolder.Call("updateTexture2D");
        }
    }
#endif
    #endregion

    void Awake()
    {
        Debug.Log("DisplayHolder ----- Awake");
#if UNITY_ANDROID && !UNITY_EDITOR
        nativeDisplayHolder = new AndroidJavaObject("com.pimax.vrshell.vd.holder.DisplayHolder");
        Debug.Log(nativeDisplayHolder==null?"Start nativeDisplayHolder is null":"Start nativeDisplayHolder success");
#endif
    }

    void Update()
    {
#if UNITY_ANDROID
        if (Time.realtimeSinceStartup - startupTime > 0.5f)
        {
            _updateTexture2D();
        }
#endif
    }

    float startupTime = 0;
    // Update is called once per frame
    // Start is called before the first frame update
    void Start()
    {
        Debug.Log("DisplayHolder ----- Start");
#if UNITY_ANDROID
        startupTime = Time.realtimeSinceStartup;
        textureId = _createVd(isOes);
#endif
        if(isOes)
        {
            SvrOverlay[] overlays = GetComponents<SvrOverlay>();
            Debug.Log("DisplayHolder ----- Overlay Length = " + overlays.Length);
            if (overlays != null && overlays.Length > 0)
            {
                for(int i = 0; i < overlays.Length; i++)
                {
                    overlays[i].TextureId = textureId;
                    Debug.Log("DisplayHolder ----- Overlay[" + i + "] TextureID = " + textureId);
                }
            }
        } else {
            // entry only once
            Debug.Log("DisplayHolder create external 2dtextureID = " + textureId);
            texture = Texture2D.CreateExternalTexture(VD_WIDTH, VD_HEIGHT, TextureFormat.ARGB32, false, false, (System.IntPtr)textureId);
            texture.wrapMode = TextureWrapMode.Clamp;
            texture.filterMode = FilterMode.Bilinear;

            Sprite sprite = Sprite.Create(texture, new Rect(0, 0, VD_WIDTH, VD_HEIGHT), Vector2.zero);
            UnityEngine.UI.Image img = GetComponent<UnityEngine.UI.Image>();//GetComponent<UnityEngine.UI.Image>();
            if (img != null)
            {
                Debug.Log("DisplayHolder -- set sprite");
                img.sprite = sprite;
            }
        }
    }
}
