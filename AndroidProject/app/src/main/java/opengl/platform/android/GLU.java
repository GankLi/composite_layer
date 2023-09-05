package opengl.platform.android;

import android.util.Log;

public class GLU {
  public static String gluErrorString(int error) {
    Log.d("GLU", "gluErrorString paras:"+error);
    return android.opengl.GLU.gluErrorString(error);
  }
}
