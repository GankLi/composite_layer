Shader "QVR/OesTexture"
{
	Properties
	{
		_MainTex("Texture", 2D) = "white" {}
	}
 
		SubShader
	{
		Tags { "Queue" = "Transparent" "IgnoreProjector" = "True" "RenderType" = "Transparent"}
		ZWrite On
		LOD 100
		Blend SrcAlpha OneMinusSrcAlpha

		Pass
		{
			GLSLPROGRAM
			#pragma only_renderers gles3
			#include "UnityCG.glslinc"
			#version 320 es
			#extension GL_OES_EGL_image_external_essl3 : require
 
#ifdef VERTEX
			out vec2 textureCoord;
			void main()
			{
				gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;
				textureCoord = gl_MultiTexCoord0.xy;
			}
#endif // VERTEX
 
#ifdef FRAGMENT
			in vec2 textureCoord;
			layout(binding = 0) uniform samplerExternalOES _MainTex;
			out vec4 fragColor;
			void main()
			{
				fragColor  = texture(_MainTex, textureCoord);
			}
#endif // FRAGMENT
			ENDGLSL
		}
	}
	FallBack Off
}
