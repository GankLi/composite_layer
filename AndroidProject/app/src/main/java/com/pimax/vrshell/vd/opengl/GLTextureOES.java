package com.pimax.vrshell.vd.opengl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
//import platform.opengl.android.GLES11Ext;
//import platform.opengl.android.GLES32;

//import android.opengl.GLES11Ext;
//import android.opengl.GLES32;
import opengl.platform.android.GLES11Ext;
import opengl.platform.android.GLES32;
import android.opengl.Matrix;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

//import junit.framework.Assert;

public class GLTextureOES extends GLTexture2D {

    public GLTextureOES(Context context, Bitmap bitmap) { super(context, bitmap); }

    public GLTextureOES(Context context, int width, int height) {
        super(context, width, height);
    }

    @Override
    protected int createTexture(int width, int height) {
        int[] temps = new int[1];
        GLES32.glGenTextures(1, temps, 0);
        mTextureID = temps[0];
        GLES32.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, mTextureID);

        GLES32.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES32.GL_TEXTURE_MIN_FILTER, GLES32.GL_NEAREST);
        GLES32.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES32.GL_TEXTURE_MAG_FILTER, GLES32.GL_LINEAR);
        GLES32.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES32.GL_TEXTURE_WRAP_S, GLES32.GL_CLAMP_TO_EDGE);
        GLES32.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES32.GL_TEXTURE_WRAP_T, GLES32.GL_CLAMP_TO_EDGE);
        GLES32.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, 0);
        return mTextureID;
    }

    public void draw(float[] mvpMatrix) {
        GLES32.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES32.glClear(GLES32.GL_DEPTH_BUFFER_BIT | GLES32.GL_COLOR_BUFFER_BIT);
        GLES32.glUseProgram(mProgram);
        GLES32.glBindVertexArray(mVao[0]);
        GLES32.glActiveTexture(GLES32.GL_TEXTURE0);
        GLES32.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, mTextureID);
        int mvpMatrixHandle = GLES32.glGetUniformLocation(mProgram, "uMVPMatrix");
        // Pass the projection and view transformation to the shader
        GLES32.glUniformMatrix4fv(mvpMatrixHandle, 1, false, mvpMatrix, 0);
        Utils.checkGlError("glUniformMatrix4fv mvpMatrixHandle");
        GLES32.glDrawElements(GLES32.GL_TRIANGLE_FAN, drawOrder.length, GLES32.GL_UNSIGNED_INT, 0);
        GLES32.glBindVertexArray(0);
        GLES32.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, 0);
    }

    @Override
    protected void initShader() {
        super.initShader();
        mFragmentCode = readShader("fragment_ext.glsl");
    }
}
