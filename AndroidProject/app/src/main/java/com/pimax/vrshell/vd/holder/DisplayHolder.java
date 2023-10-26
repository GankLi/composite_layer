package com.pimax.vrshell.vd.holder;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.app.PxrManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.hardware.display.DisplayManager;
import android.opengl.Matrix;
import android.util.Log;
import android.view.Display;
import android.view.Surface;

import com.pimax.vrshell.vd.opengl.FBO;
import com.pimax.vrshell.vd.opengl.GLTexture2D;
import com.pimax.vrshell.vd.opengl.GLTextureOES;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

import java.util.concurrent.Executor;

import opengl.platform.android.GLES32;

public class DisplayHolder {
    private static final String TAG = DisplayHolder.class.getSimpleName();
    private static final int VD_SCREEN_WIDTH = 2880;//1280;//1080;//1080;//2160;//1280;
    private static final int VD_SCREEN_HEIGHT = 1600;//720;//2160;//2160;//1080;//720;
    private final int Invalid_display_id = -999;

    private SurfaceTexture mSurfaceTexture; //camera preview
    private GLTextureOES mTextureOES;       //GL_TEXTURE_EXTERNAL_OES
    private GLTexture2D mUnityTexture;      //GL_TEXTURE_2D 用于在Unity里显示的贴图
    private FBO mFBO;
    private DisplayManager mDisplayManager;
    public int mVirtualDisplayId = Display.INVALID_DISPLAY;

    private PxrManager mPxrManager = null;

    private boolean mFrameUpdated;  //帧是否更新

    private float[] mMVPMatrix = new float[16];

    private boolean isOesTexture = false;

    public static DisplayHolder instance;

    private SurfaceTexture.OnFrameAvailableListener mCallBack = new SurfaceTexture.OnFrameAvailableListener() {
        @Override
        public void onFrameAvailable(SurfaceTexture surfaceTexture) {
            Log.d(TAG, "onFrameAvailable");
            mFrameUpdated = true;
        }
    };

    public DisplayHolder() {
        Log.d(TAG, "DisplayHolder()");
    }

    /**
     * Call by Unity Awake
     */
    public synchronized static DisplayHolder getInstance() {
        Log.d(TAG, "getInstance()");
        if (instance == null) {
            instance = new DisplayHolder();
        }
        return instance;
    }

    /**
     * Call by Unity Start
     */
    @SuppressLint("WrongConstant")
    public int createVd(boolean isOes, boolean isUnityOes) {
        isOesTexture = isOes|isUnityOes;
        Log.d(TAG, "createVd isOes = " + isOesTexture);
        if (isOesTexture && mTextureOES != null) {
            return mTextureOES.getTextureID();
        }
        Log.d(TAG, "Create OESTexture = " + isOesTexture);
        mTextureOES = new GLTextureOES(UnityPlayer.currentActivity, VD_SCREEN_WIDTH, VD_SCREEN_HEIGHT);
        mSurfaceTexture = new SurfaceTexture(mTextureOES.getTextureID());
        mSurfaceTexture.setOnFrameAvailableListener(mCallBack);
        mSurfaceTexture.setDefaultBufferSize(VD_SCREEN_WIDTH, VD_SCREEN_HEIGHT);

        final ActivityOptions options = ActivityOptions.makeBasic();
        mVirtualDisplayId = Invalid_display_id;
        mDisplayManager = (DisplayManager)(UnityPlayer.currentActivity.getSystemService(Context.DISPLAY_SERVICE));

        Log.d(TAG, "CreateVirtualDisplay service");
        mPxrManager = (PxrManager) (UnityPlayer.currentActivity.getSystemService("pxrmanager")); //Context.DISPLAY_SERVICE
        mPxrManager.bindXrvdDisplay(PxrManager.XRVD_TYPE_INTERNAL, mSurfaceTexture);
        mVirtualDisplayId = mPxrManager.getXrvdDisplayId(PxrManager.XRVD_TYPE_INTERNAL);
        Log.i(TAG, "mVirtualDisplayId =" + mVirtualDisplayId);
        mPxrManager.registerXrvdListener(new PxrManager.PxrStatusListener() {
            @Override
            public void onScreenStateChanged(boolean isScreenOn) {
                Log.i(TAG, "onScreenStateChanged:" + isScreenOn);
            }

            @Override
            public void onXrvdFocusAppChanged(int displayId, String focusedPkgName, String modeType, int vdType) {
                Display display = mDisplayManager.getDisplay(displayId);
                int rotation = display.getRotation();
                Log.i(TAG, "DebugDisplay onXrvdFocusAppChanged:"+displayId+","+focusedPkgName+","+modeType+","+vdType+", rotation:"+rotation);

            }

            @Override
            public void onTopFocusedDisplayChanged(int displayId) {
                Log.i(TAG, "onTopFocusedDisplayChanged, displayId: "+displayId);
            }

            @Override
            public void onPmxPiKeyEvent(boolean var1, boolean var2) {
                Log.i(TAG, "onPmxHomeKeyEvent:"+var1);
            }
        }, new Executor() {
            @Override
            public void execute(Runnable runnable) {
                runnable.run();
            }
        });

        // Unity
        if (isOesTexture) {
            return mTextureOES.getTextureID();
        } else {
            mUnityTexture = new GLTexture2D(UnityPlayer.currentActivity, VD_SCREEN_WIDTH, VD_SCREEN_HEIGHT);
            mFBO = new FBO(mUnityTexture);
            Log.d(TAG, "----- Create 2DTextureID = " + mUnityTexture.getTextureId());
            return mUnityTexture.getTextureId();
        }
    }

    private static int sLogCount = 0;

    /**
     * Call by Unity Update
     */
    public void updateTexture2D() {
        if (sLogCount%100 == 0) Log.d(TAG, "updateTexture2D --> mTextureOES.draw(mMVPMatrix)");
        sLogCount++;
        synchronized (this) {
            if (mFrameUpdated) {
                mFrameUpdated = false;
            } else {
                return ;
            }

            mSurfaceTexture.updateTexImage();
            float[] transform = new float[16];
            mSurfaceTexture.getTransformMatrix(transform);

            if(isOesTexture) {
                return;
            }

            Matrix.setIdentityM(mMVPMatrix, 0);
            int currentRotation = Surface.ROTATION_0;
            switch (currentRotation) {
                case Surface.ROTATION_0:
                    Matrix.setRotateM(mMVPMatrix, 0, 270, 0, 0, 1);
                    break;

                case Surface.ROTATION_90:
                    Matrix.setIdentityM(mMVPMatrix, 0);
                    break;
                case Surface.ROTATION_180:
                    Matrix.setRotateM(mMVPMatrix, 0, 90, 0, 0, 1);
                    break;
                case Surface.ROTATION_270:
                    Matrix.setRotateM(mMVPMatrix, 0, 180, 0, 0, 1);
                    break;
            }
        }

        mFBO.FBOBegin();
        GLES32.glViewport(0, 0, VD_SCREEN_WIDTH, VD_SCREEN_HEIGHT);
        mTextureOES.draw(mMVPMatrix);
        mFBO.FBOEnd();
    }

    private void startQQ() {
        Log.d(TAG, "------------startQQ");
        final ActivityOptions options = ActivityOptions.makeBasic();
        options.setLaunchDisplayId(mVirtualDisplayId);
        final Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.setComponent(new ComponentName("com.tencent.qqmusic", "com.tencent.qqmusic.activity.AppStarterActivity"));
        UnityPlayer.currentActivity.startActivity(intent,options.toBundle());
    }

}
