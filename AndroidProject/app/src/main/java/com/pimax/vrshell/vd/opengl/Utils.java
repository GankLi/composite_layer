package com.pimax.vrshell.vd.opengl;

//import platform.opengl.android.GLES32;
import android.opengl.GLES32;
import android.util.Log;

public class Utils {
    public static final String TAG = "GLES";

    public static void checkGlError(String op) {
        int error;
        while ((error = GLES32.glGetError()) != GLES32.GL_NO_ERROR) {
            Log.e(TAG, op + ": glError " + error);
            throw new RuntimeException(op + ": glError " + error);
        }
    }

    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES32.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES32.glShaderSource(shader, shaderCode);
        GLES32.glCompileShader(shader);
        int[] compileStatus = new int[1];
        GLES32.glGetShaderiv(shader, GLES32.GL_COMPILE_STATUS, compileStatus, 0);
        if (compileStatus[0] == 0) {
            String log = GLES32.glGetShaderInfoLog(shader);
            Log.e(TAG, "glGetShaderiv failed: " + log);
            GLES32.glDeleteShader(shader);
            throw new RuntimeException("glError: " + log);
        }
        return shader;
    }
}
