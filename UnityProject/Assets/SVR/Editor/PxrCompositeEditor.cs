using UnityEditor;
using UnityEngine;

namespace Pxr.Composite
{
    [CustomEditor(typeof(PxrComposite))]
    public class PxrCompositeEditor : Editor
    {
        public override void OnInspectorGUI()
        {
            PxrComposite composite = (PxrComposite) target;
            if (composite == null)
            {
                return;
            }
            //-Order-
            EditorGUILayout.LabelField("Display Order", EditorStyles.boldLabel);
            composite.compositeType = (CompositeType)EditorGUILayout.EnumPopup(new GUIContent("Composite Type", "Whether this composite should layer behind the scene or in front of it"), composite.compositeType);
            composite.layerID = EditorGUILayout.IntField(new GUIContent("Layer ID", "Depth value used to sort layers in the scene, bigger value appears in front"), composite.layerID);
            EditorGUILayout.Space();

            //-Shape-
            EditorGUILayout.LabelField(new GUIContent("Composite Shape", "The shape of this composite"), EditorStyles.boldLabel);
            composite.currentShape = (CompositeShape)EditorGUILayout.EnumPopup(new GUIContent("Composite Shape", "The shape of this composite"), composite.currentShape);
            composite.meshFace = (MeshFace)EditorGUILayout.EnumPopup(new GUIContent("Mesh Face", "The mesh direction"), composite.meshFace);
            EditorGUILayout.Space();
            EditorGUILayout.Separator();

            //-Texture-
            EditorGUILayout.LabelField("Textures", EditorStyles.boldLabel);
#if !UNITY_ANDROID
            bool lastIsExternalSurface = composite.isOESTexture;
            composite.isOESTexture = EditorGUILayout.Toggle(new GUIContent("Is External Surface", "On Android, retrieve an Android Surface object to render to (e.g., video playback)"), composite.isOESTexture);
            if (lastIsExternalSurface)
            {
                composite.texture_width = EditorGUILayout.IntField("External Surface Width", composite.texture_width);
                composite.texture_height = EditorGUILayout.IntField("External Surface Height", composite.texture_height);
                composite.texture = null;
            }
            else
#endif
            {
                if (composite.texture == null)
                {
                    Texture[] tmp = new Texture[1];
                    composite.texture = tmp[0];
                }
                var labelControlRect = EditorGUILayout.GetControlRect();
                EditorGUI.LabelField(new Rect(labelControlRect.x, labelControlRect.y, labelControlRect.width / 2, labelControlRect.height), new GUIContent("Texture", "Texture used for the layer"));

                var textureControlRect = EditorGUILayout.GetControlRect(GUILayout.Height(64));

                composite.texture = (Texture)EditorGUI.ObjectField(new Rect(textureControlRect.x, textureControlRect.y, 64, textureControlRect.height), composite.texture, typeof(Texture), true);
            }

            EditorUtility.SetDirty(composite);
        }
    }
}
