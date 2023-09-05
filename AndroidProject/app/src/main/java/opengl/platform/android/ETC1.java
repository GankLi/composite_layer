package opengl.platform.android;

import java.nio.ByteBuffer;

public class ETC1 {
  public static final int DECODED_BLOCK_SIZE = 48;
  public static final int ENCODED_BLOCK_SIZE = 8;
  public static final int ETC1_RGB8_OES = 36196;
  public static final int ETC_PKM_HEADER_SIZE = 16;

  public static int getEncodedDataSize(int width, int height) {
    return android.opengl.ETC1.getEncodedDataSize(width,height);
  }

  public static void encodeImage(ByteBuffer uncompressedBuffer, int width, int height, int i, int i1, ByteBuffer compressedBuffer) {
    android.opengl.ETC1.encodeImage(uncompressedBuffer,width,height,i,i1,compressedBuffer);
  }
}
