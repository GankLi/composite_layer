using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Rendering;

namespace Pxr.Composite
{
    public enum CompositeType
    {
        Overlay = 1,
        Underlay = 0
    }

    public enum CompositeShape
    {
        Quad,
        Cylinder,
        Sphere
    }

    public enum MeshFace
    {
        Front,
        Back
    }

    public class PxrCompositeManager : MonoBehaviour
    {

        public static List<PxrComposite> _PxrCompositeList = new List<PxrComposite>();

        private void Awake()
        {
            //NativeMethods.SetLayerRenderQuality(1.3f);
        }

        private void OnEnable()
        {
#if UNITY_2019_1_OR_NEWER
            if (GraphicsSettings.renderPipelineAsset != null)
            {
                RenderPipelineManager.beginCameraRendering += BeginCameraRendering;
                RenderPipelineManager.endCameraRendering += EndCameraRendering;
            }
            else
            {
                Camera.onPreRender += OnPreRenderCallBack;
                Camera.onPostRender += OnPostRenderCallBack;
            }
#endif
        }

        private void OnDisable()
        {
#if UNITY_2019_1_OR_NEWER
            if (GraphicsSettings.renderPipelineAsset != null)
            {
                RenderPipelineManager.beginCameraRendering -= BeginCameraRendering;
                RenderPipelineManager.endCameraRendering -= EndCameraRendering;
            }
            else
            {
                Camera.onPreRender -= OnPreRenderCallBack;
                Camera.onPostRender -= OnPostRenderCallBack;
            }
#endif
        }

        private void OnDestroy()
        {
            foreach (var pxrComposite in _PxrCompositeList)
            {
                pxrComposite.Destroy();
            }
            _PxrCompositeList.Clear();
        }

        //-------------------------------------
        private void BeginCameraRendering(ScriptableRenderContext contex, Camera camera)
        {
            OnPreRenderCallBack(camera);
        }

        private void EndCameraRendering(ScriptableRenderContext contex, Camera camera)
        {
            OnPostRenderCallBack(camera);
        }

        private void OnPreRenderCallBack(Camera camera)
        {

        }

        private void OnPostRenderCallBack(Camera camera)
        {
            foreach (var pxrComposite in _PxrCompositeList)
            {
                if (pxrComposite.gameObject != null && pxrComposite.isMeshInit)
                {
                    pxrComposite.UpdateCameraInfo(camera);
                    pxrComposite.UpdateModelInfo();
                }
            }
        }
    }
}
