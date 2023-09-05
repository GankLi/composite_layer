package com.pimax.vrshell.vd.opengl;

import android.content.Context;
import android.graphics.Bitmap;
//import platform.opengl.android.GLES32;
//import android.opengl.GLES32;
//import android.opengl.GLUtils;
import android.util.Log;

import opengl.platform.android.GLES11Ext;
import opengl.platform.android.GLES32;
import opengl.platform.android.GLUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class GLTexture2D {

    protected int mTextureID;
    protected String mVertexCode;
    protected String mFragmentCode;
    protected int mProgram;

    protected FloatBuffer vertexBuffer;
    protected IntBuffer drawListBuffer;

    protected static final String TAG = "GLTexture2D";

    public int getTextureId() {
        return mTextureID;
    }

    // 顶点坐标
    static final int COORDS_PER_VERTEX = 3;
    static float squareCoords[] = {
            -1f, -1f, 0.0f, //left bottom
            1f, -1f, 0.0f,
            -1f, 1f, 0.0f,
            1f, 1f, 0.0f
    };
    protected int drawOrder[] = { 0, 1, 2, 2, 3, 1 }; // 顶点绘制顺序
    protected final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

    // 贴图坐标
    float[] uvs = new float[] {
            0.0f, 0.0f, // top left (V2)
            0.0f, 1.0f, // bottom left (V1)
            1.0f, 0.0f, // top right (V4)
            1.0f, 1.0f  // bottom right (V3)
    };

    //float[] projectionMat = new float[16];

    protected FloatBuffer uvBuffer;
    protected Context mContext;

    protected int mWidth;
    protected int mHeight;

    protected int[] mVao = new int[1];
    protected int[] mVbo = new int[3];

    public GLTexture2D(int textureID) {
        mTextureID = textureID;
    }

    public GLTexture2D(Context context, Bitmap bitmap) {
        mContext = context;
        initVertex();
        initShader();
        createProgram();

        int[] textures = new int[1];
        GLES32.glGenTextures(1, textures, 0);
        mTextureID = textures[0];
        GLES32.glBindTexture(GLES32.GL_TEXTURE_2D, mTextureID);

        //GLES32.glTexParameterf(GLES32.GL_TEXTURE_2D, GLES32.GL_TEXTURE_MIN_FILTER, GLES32.GL_NEAREST);
        GLES32.glTexParameterf(GLES32.GL_TEXTURE_2D, GLES32.GL_TEXTURE_MIN_FILTER, GLES32.GL_LINEAR_MIPMAP_NEAREST);
        GLES32.glTexParameterf(GLES32.GL_TEXTURE_2D, GLES32.GL_TEXTURE_MAG_FILTER, GLES32.GL_LINEAR);
        GLES32.glTexParameteri(GLES32.GL_TEXTURE_2D, GLES32.GL_TEXTURE_WRAP_S, GLES32.GL_CLAMP_TO_EDGE);
        GLES32.glTexParameteri(GLES32.GL_TEXTURE_2D, GLES32.GL_TEXTURE_WRAP_T, GLES32.GL_CLAMP_TO_EDGE);

        GLUtils.texImage2D(GLES32.GL_TEXTURE_2D, 0, bitmap,0);
        GLES32.glGenerateMipmap(GLES32.GL_TEXTURE_2D);
        GLES32.glBindTexture(GLES32.GL_TEXTURE_2D, 0);

        mWidth = bitmap.getWidth();
        mHeight = bitmap.getHeight();

        bitmap.recycle();
    }

    public GLTexture2D(Context context, int width, int height) {
        mContext = context;
        initVertex();
        initShader();
        createProgram();
        initGLES();
        createTexture(width, height);
        bindGLES();
        mWidth = width;
        mHeight = height;
    }

    protected void initVertex() {
        // init VBO
        ByteBuffer vByteBuffer = ByteBuffer.allocateDirect(squareCoords.length * 4); // 4 bytes per float
        vByteBuffer.order(ByteOrder.nativeOrder());
        vertexBuffer = vByteBuffer.asFloatBuffer();
        vertexBuffer.put(squareCoords);
        vertexBuffer.position(0);

        // init drawOrder
        ByteBuffer dByteBuffer = ByteBuffer.allocateDirect(drawOrder.length * 4); // 2 bytes per short
        dByteBuffer.order(ByteOrder.nativeOrder());
        drawListBuffer = dByteBuffer.asIntBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);

        // init uv
        ByteBuffer uvByteBuffer = ByteBuffer.allocateDirect(uvs.length * 4); // 4 bytes per float
        uvByteBuffer.order(ByteOrder.nativeOrder());
        uvBuffer = uvByteBuffer.asFloatBuffer();
        uvBuffer.put(uvs);
        uvBuffer.position(0);
    }

    protected void createProgram() {
        int vertexShader = Utils.loadShader(GLES32.GL_VERTEX_SHADER, mVertexCode);
        int fragmentShader = Utils.loadShader(GLES32.GL_FRAGMENT_SHADER, mFragmentCode);
        mProgram = GLES32.glCreateProgram();
        GLES32.glAttachShader(mProgram, vertexShader);
        Utils.checkGlError("glAttachShader vertexShader");
        GLES32.glAttachShader(mProgram, fragmentShader);
        Utils.checkGlError("glAttachShader fragmentShader");
        GLES32.glLinkProgram(mProgram);
        int[] linkStatus = new int[1];
        GLES32.glGetProgramiv(mProgram, GLES32.GL_LINK_STATUS, linkStatus, 0);
        if (linkStatus[0] == 0){
            String log = GLES32.glGetProgramInfoLog(mProgram);
            Log.e(TAG, "glGetProgramInfoLog failed: " + log);
            GLES32.glDeleteProgram(mProgram);
            mProgram = 0;
        }
    }

    protected void initShader() {
        mVertexCode = readShader("vertex.glsl");
        mFragmentCode = readShader("fragment_default.glsl");
    }

    protected String readShader(String name) {
        try {
            InputStream input = mContext.getAssets().open(name);

            byte[] bytes = new byte[input.available()];
            input.read(bytes, 0, input.available());
            return new String(bytes, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected int createTexture(int width, int height){
        int[] textures = new int[1];
        GLES32.glGenTextures(1, textures, 0);
        mTextureID = textures[0];
        GLES32.glBindTexture(GLES32.GL_TEXTURE_2D, mTextureID);
        GLES32.glTexParameterf(GLES32.GL_TEXTURE_2D, GLES32.GL_TEXTURE_MIN_FILTER, GLES32.GL_NEAREST);
        GLES32.glTexParameterf(GLES32.GL_TEXTURE_2D, GLES32.GL_TEXTURE_MAG_FILTER, GLES32.GL_LINEAR);
        GLES32.glTexParameteri(GLES32.GL_TEXTURE_2D, GLES32.GL_TEXTURE_WRAP_S, GLES32.GL_CLAMP_TO_EDGE);
        GLES32.glTexParameteri(GLES32.GL_TEXTURE_2D, GLES32.GL_TEXTURE_WRAP_T, GLES32.GL_CLAMP_TO_EDGE);
        GLES32.glTexImage2D(GLES32.GL_TEXTURE_2D, 0, GLES32.GL_RGBA, width, height, 0, GLES32.GL_RGBA, GLES32.GL_UNSIGNED_BYTE, null);
        GLES32.glBindTexture(GLES32.GL_TEXTURE_2D, 0);
        return mTextureID;
    }

    protected int initGLES() {
        GLES32.glGenVertexArrays(1, mVao, 0);
        GLES32.glBindVertexArray(mVao[0]);
        GLES32.glGenBuffers(3, mVbo, 0);
        GLES32.glBindBuffer(GLES32.GL_ARRAY_BUFFER, mVbo[0]);
        GLES32.glBufferData(GLES32.GL_ARRAY_BUFFER, squareCoords.length * 4, vertexBuffer, GLES32.GL_STATIC_DRAW);
        GLES32.glBindBuffer(GLES32.GL_ARRAY_BUFFER, mVbo[1]);
        GLES32.glBufferData(GLES32.GL_ARRAY_BUFFER, uvs.length * 4, uvBuffer, GLES32.GL_STATIC_DRAW);
        GLES32.glBindBuffer(GLES32.GL_ELEMENT_ARRAY_BUFFER, mVbo[2]);
        GLES32.glBufferData(GLES32.GL_ELEMENT_ARRAY_BUFFER, drawOrder.length * 4, drawListBuffer, GLES32.GL_STATIC_DRAW);
        return 0;
    }

    protected int bindGLES(){
        GLES32.glUseProgram(mProgram);
        int loc = GLES32.glGetUniformLocation(mProgram, "sTexture");
        GLES32.glUniform1i(loc, 0);
        GLES32.glBindBuffer(GLES32.GL_ARRAY_BUFFER, mVbo[0]);
        GLES32.glVertexAttribPointer(0, 3, GLES32.GL_FLOAT, false, 12, 0);
        GLES32.glEnableVertexAttribArray(0);
        GLES32.glBindBuffer(GLES32.GL_ARRAY_BUFFER, 0);
        GLES32.glBindBuffer(GLES32.GL_ARRAY_BUFFER, mVbo[1]);
        GLES32.glVertexAttribPointer(1, 2, GLES32.GL_FLOAT, false, 8, 0);
        GLES32.glEnableVertexAttribArray(1);
        GLES32.glBindBuffer(GLES32.GL_ARRAY_BUFFER, 0);
        GLES32.glBindVertexArray(0);
        return 0;
    }

    public void draw(float[] mvpMatrix) {
        GLES32.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES32.glClear(GLES32.GL_DEPTH_BUFFER_BIT | GLES32.GL_COLOR_BUFFER_BIT);
        GLES32.glUseProgram(mProgram);
        GLES32.glBindVertexArray(mVao[0]);
        GLES32.glActiveTexture(GLES32.GL_TEXTURE0);
        GLES32.glBindTexture(GLES32.GL_TEXTURE_2D, mTextureID);
        int mvpMatrixHandle = GLES32.glGetUniformLocation(mProgram, "uMVPMatrix");
        // Pass the projection and view transformation to the shader
        GLES32.glUniformMatrix4fv(mvpMatrixHandle, 1, false, mvpMatrix, 0);
        Utils.checkGlError("glUniformMatrix4fv mvpMatrixHandle");
        GLES32.glDrawElements(GLES32.GL_TRIANGLE_FAN, squareCoords.length, GLES32.GL_UNSIGNED_INT, 0);
        GLES32.glBindVertexArray(0);
        GLES32.glBindTexture(GLES32.GL_TEXTURE_2D, 0);
    }

    public void copy(int width, int height) {
        GLES32.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES32.glClear(GLES32.GL_DEPTH_BUFFER_BIT | GLES32.GL_COLOR_BUFFER_BIT);
        GLES32.glUseProgram(mProgram);
        GLES32.glBindVertexArray(mVao[0]);
        GLES32.glActiveTexture(GLES32.GL_TEXTURE0);
        GLES32.glBindTexture(GLES32.GL_TEXTURE_2D, mTextureID);
        GLES32.glCopyTexSubImage2D(GLES32.GL_TEXTURE_2D, 0,0,0,0,0, width, height);
        GLES32.glBindVertexArray(0);
        GLES32.glBindTexture(GLES32.GL_TEXTURE_2D, 0);
    }

    public int getTextureID() {
        return mTextureID;
    }

    public void setTextureID(int textureID) {
        mTextureID = textureID;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    public void destory() {
        if (mTextureID != 0){
            int[] tmps = new int[1];
            tmps[0] = mTextureID;
            GLES32.glDeleteTextures(1, tmps, 0);
            mTextureID = 0;
        }
    }
}

