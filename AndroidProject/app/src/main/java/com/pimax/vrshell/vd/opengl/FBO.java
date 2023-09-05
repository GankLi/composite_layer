package com.pimax.vrshell.vd.opengl;

//import android.opengl.GLES32;
import android.util.Log;

import opengl.platform.android.GLES32;

//import platform.opengl.android.GLES32;

public class FBO {
    private GLTexture2D mTexture2D;// the texture to draw
    private int mFBOID;

    public FBO(GLTexture2D texture2D) {
        mTexture2D = texture2D;
        int depthID;
        int[] temps = new int[1];
        // Render buffer
        GLES32.glGenTextures(1, temps, 0);
        depthID = temps[0];
        GLES32.glBindTexture(GLES32.GL_TEXTURE_2D, temps[0]);
        GLES32.glTexImage2D(GLES32.GL_TEXTURE_2D, 0, GLES32.GL_DEPTH_COMPONENT,
                texture2D.getWidth(), texture2D.getHeight(),
                0, GLES32.GL_DEPTH_COMPONENT, GLES32.GL_UNSIGNED_SHORT, null);
//        GLES32.glTexImage2D(GLES32.GL_TEXTURE_2D, 0, GLES32.GL_RGBA,
//                texture2D.getWidth(), texture2D.getHeight(),
//                0, GLES32.GL_RGBA, GLES32.GL_UNSIGNED_BYTE, null);
        GLES32.glTexParameterf(GLES32.GL_TEXTURE_2D, GLES32.GL_TEXTURE_MIN_FILTER, GLES32.GL_NEAREST);
        GLES32.glTexParameterf(GLES32.GL_TEXTURE_2D, GLES32.GL_TEXTURE_MAG_FILTER, GLES32.GL_LINEAR);
        GLES32.glTexParameteri(GLES32.GL_TEXTURE_2D, GLES32.GL_TEXTURE_WRAP_S, GLES32.GL_CLAMP_TO_EDGE);
        GLES32.glTexParameteri(GLES32.GL_TEXTURE_2D, GLES32.GL_TEXTURE_WRAP_T, GLES32.GL_CLAMP_TO_EDGE);
        GLES32.glBindTexture(GLES32.GL_TEXTURE_2D, 0);

        int[] frames = new int[1];
        GLES32.glGenFramebuffers(1, frames, 0);
        mFBOID = frames[0];
        GLES32.glBindFramebuffer(GLES32.GL_FRAMEBUFFER, mFBOID);
//        GLES32.glFramebufferTexture2D(GLES32.GL_FRAMEBUFFER, GLES32.GL_COLOR_ATTACHMENT0,
//                GLES32.GL_TEXTURE_2D, depthID, 0);
        GLES32.glFramebufferTexture2D(GLES32.GL_FRAMEBUFFER, GLES32.GL_COLOR_ATTACHMENT0,
                GLES32.GL_TEXTURE_2D, mTexture2D.getTextureID(), 0);
        int status = GLES32.glCheckFramebufferStatus(GLES32.GL_FRAMEBUFFER);
        if (status != GLES32.GL_FRAMEBUFFER_COMPLETE){
            Log.d(GLTexture2D.TAG, "Error 01, frame buffer not completed!");
            return;
        }
//        GLES32.glFramebufferTexture2D(GLES32.GL_FRAMEBUFFER, GLES32.GL_DEPTH_ATTACHMENT,
//                GLES32.GL_TEXTURE_2D, mTexture2D.getTextureID(), 0);
        GLES32.glFramebufferTexture2D(GLES32.GL_FRAMEBUFFER, GLES32.GL_DEPTH_ATTACHMENT,
                GLES32.GL_TEXTURE_2D, depthID, 0);
        status = GLES32.glCheckFramebufferStatus(GLES32.GL_FRAMEBUFFER);
        if (status != GLES32.GL_FRAMEBUFFER_COMPLETE){
            Log.d(GLTexture2D.TAG, "Error 02, frame buffer not completed!");
            return;
        }
        GLES32.glBindFramebuffer(GLES32.GL_FRAMEBUFFER, 0);
    }

    public void FBOBegin() {
        GLES32.glBindFramebuffer(GLES32.GL_FRAMEBUFFER, mFBOID);
//        GLES32.glBindBuffer(GLES32.GL_ELEMENT_ARRAY_BUFFER, 0);
//        GLES32.glBindBuffer(GLES32.GL_ARRAY_BUFFER, 0);
    }

    public void FBOEnd() {
        GLES32.glBindRenderbuffer(GLES32.GL_RENDERBUFFER, GLES32.GL_NONE);
        GLES32.glBindFramebuffer(GLES32.GL_FRAMEBUFFER, 0);
    }
}

