package opengl.platform.android;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;

public class ETC1Util {
  public ETC1Util() {
  }

  public static void loadTexture(int target, int level, int border, int fallbackFormat, int fallbackType, InputStream input) throws IOException {
    android.opengl.ETC1Util.loadTexture(target, level, border, fallbackFormat, fallbackType, input);
  }

  public static void loadTexture(int target, int level, int border, int fallbackFormat, int fallbackType, android.opengl.ETC1Util.ETC1Texture texture) {
    android.opengl.ETC1Util.loadTexture(target, level, border, fallbackFormat, fallbackType, texture);
  }

  public static boolean isETC1Supported() {
    return android.opengl.ETC1Util.isETC1Supported();
  }

  public static android.opengl.ETC1Util.ETC1Texture createTexture(InputStream input) throws IOException {
    return android.opengl.ETC1Util.createTexture(input);
  }

  public static android.opengl.ETC1Util.ETC1Texture compressTexture(Buffer input, int width, int height, int pixelSize, int stride) {
    return android.opengl.ETC1Util.compressTexture(input, width, height, pixelSize, stride);
  }

  public static void writeTexture(android.opengl.ETC1Util.ETC1Texture texture, OutputStream output) throws IOException {
    android.opengl.ETC1Util.writeTexture(texture, output);
  }

}
