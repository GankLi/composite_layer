using System.Collections;
using System.Collections.Generic;
using System;
using UnityEngine;
using UnityEngine.Experimental.Rendering;

namespace Pxr.Composite
{
    [RequireComponent(typeof(PxrCompositeMeshGenerator))]
    public class PxrComposite : MonoBehaviour
    {
        protected string TAG = "PxrComposite";

        public CompositeType compositeType = CompositeType.Overlay;

        private readonly string compositeID_str = Guid.NewGuid().ToString("N");
        public int layerID;

        protected float[] m_modelScale = new float[3];
        protected float[] m_modelRotation = new float[4];
        protected float[] m_modelTrans = new float[3];
        protected float[] m_vertexs;
        protected int[] m_indices;
        protected float[] m_uv;
        protected IntPtr m_textureID;
        private int texture_format = 0;
        public int texture_width;
        public int texture_height;

        protected float[] m_cameraPosition = new float[3];
        protected float[] m_cameraRotation = new float[4];
        protected float m_camera_far = 1000;
        protected float m_camera_near = 0.01f;

        public bool isOESTexture = false;
        public bool isVisable = false;

        //
        public float[] colorScale_override = new float[4] { 1, 1, 1, 1 };
        public float[] colorOffset_override = new float[4] { 0, 0, 0, 0 };
        //

        //------------------------------------------------
        [HideInInspector]
        public bool isMeshInit = false;

        [Tooltip("The Texture to show in the layer.")]
        public Texture texture = null;

        [Tooltip("Specify composite's shape")]
        public CompositeShape currentShape = CompositeShape.Quad;

        [Tooltip("mesh face direction")]
        public MeshFace meshFace = MeshFace.Front;

        //------------------------------------------------
        public virtual void Awake()
        {
            PxrCompositeMeshGenerator pxrCompositeMeshGenerator = GetComponent<PxrCompositeMeshGenerator>();
            pxrCompositeMeshGenerator.updateComposite(this);
        }

        public virtual void Start()
        {
            PxrCompositeManager._PxrCompositeList.Add(this);
            InitMesh();
#if !UNITY_EDITOR
            MeshRenderer meshRenderer = GetComponent<MeshRenderer>();
            if (meshRenderer != null && compositeType == CompositeType.Overlay)
            {
                meshRenderer.enabled = false;
            }
#endif
        }

        public virtual void OnEnable()
        {
            isVisable = true;

        }

        public virtual void OnDisable()
        {
            isVisable = false;
        }

        private void OnDestroy()
        {
            Destroy();
            PxrCompositeManager._PxrCompositeList.Remove(this);
        }

        //-----------------------------------------
        public virtual void InitMesh()
        {
            InitModelVertexs();
            InitModelIndices();
            InitModelUV();
            InitCompositeMesh();
        }

        public virtual void InitModelVertexs()
        {
            MeshFilter meshFilter = GetComponent<MeshFilter>();
            if (null != meshFilter && meshFilter.mesh != null)
            {
                m_vertexs = new float[meshFilter.mesh.vertexCount * 3];
                int j = 0;
                for (int i = 0; i < meshFilter.mesh.vertexCount; i++)
                {
                    m_vertexs[j++] = meshFilter.mesh.vertices[i].x;
                    m_vertexs[j++] = meshFilter.mesh.vertices[i].y;
                    m_vertexs[j++] = meshFilter.mesh.vertices[i].z;
                }
            }
        }

        public virtual void InitModelIndices()
        {
            MeshFilter meshFilter = GetComponent<MeshFilter>();
            if (null != meshFilter && meshFilter.mesh != null)
            {
                m_indices = meshFilter.mesh.GetIndices(0);
            }
        }

        public virtual void InitModelUV()
        {
            MeshFilter meshFilter = GetComponent<MeshFilter>();
            if (null != meshFilter && meshFilter.mesh != null)
            {
                m_uv = new float[meshFilter.mesh.uv.Length * 2];
                int j = 0;
                for (int i = 0; i < meshFilter.mesh.uv.Length; i++)
                {
                    m_uv[j++] = meshFilter.mesh.uv[i].x;
                    m_uv[j++] = meshFilter.mesh.uv[i].y;
                }
            }
        }

        protected virtual void InitCompositeMesh()
        {
            if (m_vertexs == null || m_indices == null || m_uv == null)
            {
                isMeshInit = false;
            }
            else
            {
                Debug.Log(string.Format(TAG + " ::` >> Composite IdStr [{0}] Id [{1}] Oes [{2}] CompositeType [{3}] Vertex Count [{4}] Indices Count [{5}] UV Count [{6}]", compositeID_str, layerID, isOESTexture, compositeType, m_vertexs.Length, m_indices.Length, m_uv.Length));
                Debug.Log(string.Format(TAG + " ::` >> Composite Vertex [{0}, {1}, {2}] [{3}, {4}, {5}]", m_vertexs[0], m_vertexs[1], m_vertexs[2], m_vertexs[3], m_vertexs[4], m_vertexs[5]));
                Debug.Log(string.Format(TAG + " ::` >> Composite Vertex [{0}, {1}, {2}] [{3}, {4}, {5}]", m_vertexs[6], m_vertexs[7], m_vertexs[8], m_vertexs[9], m_vertexs[10], m_vertexs[11]));
                Debug.Log(string.Format(TAG + " ::` >> Composite Indice [{0}, {1}, {2}] [{3}, {4}, {5}]", m_indices[0], m_indices[1], m_indices[2], m_indices[3], m_indices[4], m_indices[5]));
                Debug.Log(string.Format(TAG + " ::` >> Composite UV     [{0}, {1}] [{2}] [{3}]  [{4}, {5}] [{6}, {7}]", m_uv[0], m_uv[1], m_uv[2], m_uv[3], m_uv[4], m_uv[5], m_uv[6], m_uv[7]));
                //SvrPlugin.Instance.InitCompositeLayerMesh(compositeID_str, layerID, isOESTexture, (int)compositeType, m_vertexs.Length, m_vertexs, m_indices.Length, m_indices, m_uv);
                isMeshInit = true;
            }
        }

        public virtual void UpdateModelInfo()
        {
            if (isMeshInit)
            {
                UpdateModelScale();
                UpdateModelRotation();
                UpdateModelPosition();
                if (texture != null)
                {
                    SetTexture(texture);
                }
                if (GetTextureID().ToInt32() == 0)
                {
                    return;
                }
                DrawLayer();
            }
        }

        public virtual void SetTexture(Texture texture)
        {
            if (isOESTexture)
                texture = null;
            if (texture != null)
            {
                m_textureID = texture.GetNativeTexturePtr();
                texture_width = texture.width;
                texture_height = texture.height;
                texture_format = (int)texture.graphicsFormat;
                if (texture_format == 0)
                {
                    Debug.LogError(TAG + " Unsupported image compression format,Please modify the image format.");
                }
            }
        }

        public virtual void SetTextureID(IntPtr textureID, GraphicsFormat tex_format)
        {
            m_textureID = textureID;
            texture_format = (int)tex_format;
            if (texture_format == 0)
            {
                Debug.LogError(TAG + " Unsupported image compression format,Please modify the image format.");
            }
        }

        public virtual void SetAndroidOESTextureID(int oesID)
        {
            Debug.Log("SetAndroidOESTextureID is " + oesID);
            m_textureID = new IntPtr(oesID);
        }

        public IntPtr GetTextureID()
        {
            return m_textureID;
        }

        public virtual void UpdateModelScale()
        {
            m_modelScale[0] = transform.lossyScale.x;
            m_modelScale[1] = transform.lossyScale.y;
            m_modelScale[2] = transform.lossyScale.z;
        }

        public virtual void UpdateModelRotation()
        {
            m_modelRotation[0] = transform.rotation.w;
            m_modelRotation[1] = transform.rotation.x;
            m_modelRotation[2] = transform.rotation.y;
            m_modelRotation[3] = transform.rotation.z;
        }

        public virtual void UpdateModelPosition()
        {
            m_modelTrans[0] = transform.position.x;
            m_modelTrans[1] = transform.position.y;
            m_modelTrans[2] = transform.position.z;
        }

        public void DrawLayer()
        {
            //SvrPlugin.Instance.DrawCompositeLayer(compositeID_str, layerID, m_textureID.ToInt32(), m_textureID, texture_format, texture_width, texture_height, m_modelScale, m_modelRotation, m_modelTrans, m_cameraRotation, m_cameraPosition, m_cameraRotation, m_cameraPosition, m_camera_near, m_camera_far, colorScale_override, colorOffset_override);
            //SvrPlugin.Instance.SetCompositeLayerRender(compositeID_str, isVisable);
        }

        public virtual void UpdateCameraInfo(Camera camera)
        {
            if (camera == null)
            {
                Debug.LogError(TAG + " Please add PxrCompositeManager.cs to XR Camera");
                return;
            }
            Vector3 pos;
            Quaternion quaternion;
            pos = camera.transform.position;
            quaternion = camera.transform.rotation;
            m_cameraPosition[0] = pos.x;
            m_cameraPosition[1] = pos.y;
            m_cameraPosition[2] = pos.z;

            m_cameraRotation[0] = quaternion.w;
            m_cameraRotation[1] = quaternion.x;
            m_cameraRotation[2] = quaternion.y;
            m_cameraRotation[3] = quaternion.z;

            m_camera_far = camera.farClipPlane;
            m_camera_near = camera.nearClipPlane;
        }

        public void Destroy()
        {
            Debug.Log(string.Format(TAG + "Destroy Composite {0}", compositeID_str));
            //SvrPlugin.Instance.RemoveCompositeLayer(compositeID_str);
            m_textureID = IntPtr.Zero;
        }
    }
}
