package opengl.platform.android;

import android.graphics.Bitmap;
import android.util.Log;

public class GLUtils {
  public static void texImage2D(int glTexture2d, int i, int mBitmapFormat, Bitmap mBitmap, int i1) {
    Log.d("GLUtils", "texImage2D paras:"+glTexture2d+" paras:"+i+" paras:"+mBitmapFormat+" paras:"+i1);
    android.opengl.GLUtils.texImage2D(glTexture2d,i,mBitmapFormat,mBitmap,i1);
  }

  public static void texSubImage2D(int glTexture2d, int i, int i1, int i2, Bitmap mBitmap, int mBitmapFormat, int glUnsignedByte) {
    Log.d("GLUtils", "texSubImage2D paras:"+glTexture2d+" paras:"+i+" paras:"+i1+" paras:"+i2+" paras:"+mBitmapFormat+" paras:"+glUnsignedByte);
    android.opengl.GLUtils.texSubImage2D(glTexture2d,i, i1, i2, mBitmap, mBitmapFormat, glUnsignedByte);
  }

  public static void texImage2D(int cube_face, int i, Bitmap mBitmap, int i1) {
    Log.d("GLUtils", "texImage2D paras:"+cube_face+" paras:"+i+" paras:"+i1);
    android.opengl.GLUtils.texImage2D(cube_face,i,mBitmap,i1);
  }
}
