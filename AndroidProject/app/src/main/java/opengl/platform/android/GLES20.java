package opengl.platform.android;

import android.util.Log;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class GLES20 {
  public static final int GL_ACTIVE_ATTRIBUTES = 35721;
  public static final int GL_ACTIVE_ATTRIBUTE_MAX_LENGTH = 35722;
  public static final int GL_ACTIVE_TEXTURE = 34016;
  public static final int GL_ACTIVE_UNIFORMS = 35718;
  public static final int GL_ACTIVE_UNIFORM_MAX_LENGTH = 35719;
  public static final int GL_ALIASED_LINE_WIDTH_RANGE = 33902;
  public static final int GL_ALIASED_POINT_SIZE_RANGE = 33901;
  public static final int GL_ALPHA = 6406;
  public static final int GL_ALPHA_BITS = 3413;
  public static final int GL_ALWAYS = 519;
  public static final int GL_ARRAY_BUFFER = 34962;
  public static final int GL_ARRAY_BUFFER_BINDING = 34964;
  public static final int GL_ATTACHED_SHADERS = 35717;
  public static final int GL_BACK = 1029;
  public static final int GL_BLEND = 3042;
  public static final int GL_BLEND_COLOR = 32773;
  public static final int GL_BLEND_DST_ALPHA = 32970;
  public static final int GL_BLEND_DST_RGB = 32968;
  public static final int GL_BLEND_EQUATION = 32777;
  public static final int GL_BLEND_EQUATION_ALPHA = 34877;
  public static final int GL_BLEND_EQUATION_RGB = 32777;
  public static final int GL_BLEND_SRC_ALPHA = 32971;
  public static final int GL_BLEND_SRC_RGB = 32969;
  public static final int GL_BLUE_BITS = 3412;
  public static final int GL_BOOL = 35670;
  public static final int GL_BOOL_VEC2 = 35671;
  public static final int GL_BOOL_VEC3 = 35672;
  public static final int GL_BOOL_VEC4 = 35673;
  public static final int GL_BUFFER_SIZE = 34660;
  public static final int GL_BUFFER_USAGE = 34661;
  public static final int GL_BYTE = 5120;
  public static final int GL_CCW = 2305;
  public static final int GL_CLAMP_TO_EDGE = 33071;
  public static final int GL_COLOR_ATTACHMENT0 = 36064;
  public static final int GL_COLOR_BUFFER_BIT = 16384;
  public static final int GL_COLOR_CLEAR_VALUE = 3106;
  public static final int GL_COLOR_WRITEMASK = 3107;
  public static final int GL_COMPILE_STATUS = 35713;
  public static final int GL_COMPRESSED_TEXTURE_FORMATS = 34467;
  public static final int GL_CONSTANT_ALPHA = 32771;
  public static final int GL_CONSTANT_COLOR = 32769;
  public static final int GL_CULL_FACE = 2884;
  public static final int GL_CULL_FACE_MODE = 2885;
  public static final int GL_CURRENT_PROGRAM = 35725;
  public static final int GL_CURRENT_VERTEX_ATTRIB = 34342;
  public static final int GL_CW = 2304;
  public static final int GL_DECR = 7683;
  public static final int GL_DECR_WRAP = 34056;
  public static final int GL_DELETE_STATUS = 35712;
  public static final int GL_DEPTH_ATTACHMENT = 36096;
  public static final int GL_DEPTH_BITS = 3414;
  public static final int GL_DEPTH_BUFFER_BIT = 256;
  public static final int GL_DEPTH_CLEAR_VALUE = 2931;
  public static final int GL_DEPTH_COMPONENT = 6402;
  public static final int GL_DEPTH_COMPONENT16 = 33189;
  public static final int GL_DEPTH_FUNC = 2932;
  public static final int GL_DEPTH_RANGE = 2928;
  public static final int GL_DEPTH_TEST = 2929;
  public static final int GL_DEPTH_WRITEMASK = 2930;
  public static final int GL_DITHER = 3024;
  public static final int GL_DONT_CARE = 4352;
  public static final int GL_DST_ALPHA = 772;
  public static final int GL_DST_COLOR = 774;
  public static final int GL_DYNAMIC_DRAW = 35048;
  public static final int GL_ELEMENT_ARRAY_BUFFER = 34963;
  public static final int GL_ELEMENT_ARRAY_BUFFER_BINDING = 34965;
  public static final int GL_EQUAL = 514;
  public static final int GL_EXTENSIONS = 7939;
  public static final int GL_FALSE = 0;
  public static final int GL_FASTEST = 4353;
  public static final int GL_FIXED = 5132;
  public static final int GL_FLOAT = 5126;
  public static final int GL_FLOAT_MAT2 = 35674;
  public static final int GL_FLOAT_MAT3 = 35675;
  public static final int GL_FLOAT_MAT4 = 35676;
  public static final int GL_FLOAT_VEC2 = 35664;
  public static final int GL_FLOAT_VEC3 = 35665;
  public static final int GL_FLOAT_VEC4 = 35666;
  public static final int GL_FRAGMENT_SHADER = 35632;
  public static final int GL_FRAMEBUFFER = 36160;
  public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME = 36049;
  public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE = 36048;
  public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE = 36051;
  public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL = 36050;
  public static final int GL_FRAMEBUFFER_BINDING = 36006;
  public static final int GL_FRAMEBUFFER_COMPLETE = 36053;
  public static final int GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT = 36054;
  public static final int GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS = 36057;
  public static final int GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT = 36055;
  public static final int GL_FRAMEBUFFER_UNSUPPORTED = 36061;
  public static final int GL_FRONT = 1028;
  public static final int GL_FRONT_AND_BACK = 1032;
  public static final int GL_FRONT_FACE = 2886;
  public static final int GL_FUNC_ADD = 32774;
  public static final int GL_FUNC_REVERSE_SUBTRACT = 32779;
  public static final int GL_FUNC_SUBTRACT = 32778;
  public static final int GL_GENERATE_MIPMAP_HINT = 33170;
  public static final int GL_GEQUAL = 518;
  public static final int GL_GREATER = 516;
  public static final int GL_GREEN_BITS = 3411;
  public static final int GL_HIGH_FLOAT = 36338;
  public static final int GL_HIGH_INT = 36341;
  public static final int GL_IMPLEMENTATION_COLOR_READ_FORMAT = 35739;
  public static final int GL_IMPLEMENTATION_COLOR_READ_TYPE = 35738;
  public static final int GL_INCR = 7682;
  public static final int GL_INCR_WRAP = 34055;
  public static final int GL_INFO_LOG_LENGTH = 35716;
  public static final int GL_INT = 5124;
  public static final int GL_INT_VEC2 = 35667;
  public static final int GL_INT_VEC3 = 35668;
  public static final int GL_INT_VEC4 = 35669;
  public static final int GL_INVALID_ENUM = 1280;
  public static final int GL_INVALID_FRAMEBUFFER_OPERATION = 1286;
  public static final int GL_INVALID_OPERATION = 1282;
  public static final int GL_INVALID_VALUE = 1281;
  public static final int GL_INVERT = 5386;
  public static final int GL_KEEP = 7680;
  public static final int GL_LEQUAL = 515;
  public static final int GL_LESS = 513;
  public static final int GL_LINEAR = 9729;
  public static final int GL_LINEAR_MIPMAP_LINEAR = 9987;
  public static final int GL_LINEAR_MIPMAP_NEAREST = 9985;
  public static final int GL_LINES = 1;
  public static final int GL_LINE_LOOP = 2;
  public static final int GL_LINE_STRIP = 3;
  public static final int GL_LINE_WIDTH = 2849;
  public static final int GL_LINK_STATUS = 35714;
  public static final int GL_LOW_FLOAT = 36336;
  public static final int GL_LOW_INT = 36339;
  public static final int GL_LUMINANCE = 6409;
  public static final int GL_LUMINANCE_ALPHA = 6410;
  public static final int GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS = 35661;
  public static final int GL_MAX_CUBE_MAP_TEXTURE_SIZE = 34076;
  public static final int GL_MAX_FRAGMENT_UNIFORM_VECTORS = 36349;
  public static final int GL_MAX_RENDERBUFFER_SIZE = 34024;
  public static final int GL_MAX_TEXTURE_IMAGE_UNITS = 34930;
  public static final int GL_MAX_TEXTURE_SIZE = 3379;
  public static final int GL_MAX_VARYING_VECTORS = 36348;
  public static final int GL_MAX_VERTEX_ATTRIBS = 34921;
  public static final int GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS = 35660;
  public static final int GL_MAX_VERTEX_UNIFORM_VECTORS = 36347;
  public static final int GL_MAX_VIEWPORT_DIMS = 3386;
  public static final int GL_MEDIUM_FLOAT = 36337;
  public static final int GL_MEDIUM_INT = 36340;
  public static final int GL_MIRRORED_REPEAT = 33648;
  public static final int GL_NEAREST = 9728;
  public static final int GL_NEAREST_MIPMAP_LINEAR = 9986;
  public static final int GL_NEAREST_MIPMAP_NEAREST = 9984;
  public static final int GL_NEVER = 512;
  public static final int GL_NICEST = 4354;
  public static final int GL_NONE = 0;
  public static final int GL_NOTEQUAL = 517;
  public static final int GL_NO_ERROR = 0;
  public static final int GL_NUM_COMPRESSED_TEXTURE_FORMATS = 34466;
  public static final int GL_NUM_SHADER_BINARY_FORMATS = 36345;
  public static final int GL_ONE = 1;
  public static final int GL_ONE_MINUS_CONSTANT_ALPHA = 32772;
  public static final int GL_ONE_MINUS_CONSTANT_COLOR = 32770;
  public static final int GL_ONE_MINUS_DST_ALPHA = 773;
  public static final int GL_ONE_MINUS_DST_COLOR = 775;
  public static final int GL_ONE_MINUS_SRC_ALPHA = 771;
  public static final int GL_ONE_MINUS_SRC_COLOR = 769;
  public static final int GL_OUT_OF_MEMORY = 1285;
  public static final int GL_PACK_ALIGNMENT = 3333;
  public static final int GL_POINTS = 0;
  public static final int GL_POLYGON_OFFSET_FACTOR = 32824;
  public static final int GL_POLYGON_OFFSET_FILL = 32823;
  public static final int GL_POLYGON_OFFSET_UNITS = 10752;
  public static final int GL_RED_BITS = 3410;
  public static final int GL_RENDERBUFFER = 36161;
  public static final int GL_RENDERBUFFER_ALPHA_SIZE = 36179;
  public static final int GL_RENDERBUFFER_BINDING = 36007;
  public static final int GL_RENDERBUFFER_BLUE_SIZE = 36178;
  public static final int GL_RENDERBUFFER_DEPTH_SIZE = 36180;
  public static final int GL_RENDERBUFFER_GREEN_SIZE = 36177;
  public static final int GL_RENDERBUFFER_HEIGHT = 36163;
  public static final int GL_RENDERBUFFER_INTERNAL_FORMAT = 36164;
  public static final int GL_RENDERBUFFER_RED_SIZE = 36176;
  public static final int GL_RENDERBUFFER_STENCIL_SIZE = 36181;
  public static final int GL_RENDERBUFFER_WIDTH = 36162;
  public static final int GL_RENDERER = 7937;
  public static final int GL_REPEAT = 10497;
  public static final int GL_REPLACE = 7681;
  public static final int GL_RGB = 6407;
  public static final int GL_RGB565 = 36194;
  public static final int GL_RGB5_A1 = 32855;
  public static final int GL_RGBA = 6408;
  public static final int GL_RGBA4 = 32854;
  public static final int GL_SAMPLER_2D = 35678;
  public static final int GL_SAMPLER_CUBE = 35680;
  public static final int GL_SAMPLES = 32937;
  public static final int GL_SAMPLE_ALPHA_TO_COVERAGE = 32926;
  public static final int GL_SAMPLE_BUFFERS = 32936;
  public static final int GL_SAMPLE_COVERAGE = 32928;
  public static final int GL_SAMPLE_COVERAGE_INVERT = 32939;
  public static final int GL_SAMPLE_COVERAGE_VALUE = 32938;
  public static final int GL_SCISSOR_BOX = 3088;
  public static final int GL_SCISSOR_TEST = 3089;
  public static final int GL_SHADER_BINARY_FORMATS = 36344;
  public static final int GL_SHADER_COMPILER = 36346;
  public static final int GL_SHADER_SOURCE_LENGTH = 35720;
  public static final int GL_SHADER_TYPE = 35663;
  public static final int GL_SHADING_LANGUAGE_VERSION = 35724;
  public static final int GL_SHORT = 5122;
  public static final int GL_SRC_ALPHA = 770;
  public static final int GL_SRC_ALPHA_SATURATE = 776;
  public static final int GL_SRC_COLOR = 768;
  public static final int GL_STATIC_DRAW = 35044;
  public static final int GL_STENCIL_ATTACHMENT = 36128;
  public static final int GL_STENCIL_BACK_FAIL = 34817;
  public static final int GL_STENCIL_BACK_FUNC = 34816;
  public static final int GL_STENCIL_BACK_PASS_DEPTH_FAIL = 34818;
  public static final int GL_STENCIL_BACK_PASS_DEPTH_PASS = 34819;
  public static final int GL_STENCIL_BACK_REF = 36003;
  public static final int GL_STENCIL_BACK_VALUE_MASK = 36004;
  public static final int GL_STENCIL_BACK_WRITEMASK = 36005;
  public static final int GL_STENCIL_BITS = 3415;
  public static final int GL_STENCIL_BUFFER_BIT = 1024;
  public static final int GL_STENCIL_CLEAR_VALUE = 2961;
  public static final int GL_STENCIL_FAIL = 2964;
  public static final int GL_STENCIL_FUNC = 2962;
  /** @deprecated */
  @Deprecated
  public static final int GL_STENCIL_INDEX = 6401;
  public static final int GL_STENCIL_INDEX8 = 36168;
  public static final int GL_STENCIL_PASS_DEPTH_FAIL = 2965;
  public static final int GL_STENCIL_PASS_DEPTH_PASS = 2966;
  public static final int GL_STENCIL_REF = 2967;
  public static final int GL_STENCIL_TEST = 2960;
  public static final int GL_STENCIL_VALUE_MASK = 2963;
  public static final int GL_STENCIL_WRITEMASK = 2968;
  public static final int GL_STREAM_DRAW = 35040;
  public static final int GL_SUBPIXEL_BITS = 3408;
  public static final int GL_TEXTURE = 5890;
  public static final int GL_TEXTURE0 = 33984;
  public static final int GL_TEXTURE1 = 33985;
  public static final int GL_TEXTURE10 = 33994;
  public static final int GL_TEXTURE11 = 33995;
  public static final int GL_TEXTURE12 = 33996;
  public static final int GL_TEXTURE13 = 33997;
  public static final int GL_TEXTURE14 = 33998;
  public static final int GL_TEXTURE15 = 33999;
  public static final int GL_TEXTURE16 = 34000;
  public static final int GL_TEXTURE17 = 34001;
  public static final int GL_TEXTURE18 = 34002;
  public static final int GL_TEXTURE19 = 34003;
  public static final int GL_TEXTURE2 = 33986;
  public static final int GL_TEXTURE20 = 34004;
  public static final int GL_TEXTURE21 = 34005;
  public static final int GL_TEXTURE22 = 34006;
  public static final int GL_TEXTURE23 = 34007;
  public static final int GL_TEXTURE24 = 34008;
  public static final int GL_TEXTURE25 = 34009;
  public static final int GL_TEXTURE26 = 34010;
  public static final int GL_TEXTURE27 = 34011;
  public static final int GL_TEXTURE28 = 34012;
  public static final int GL_TEXTURE29 = 34013;
  public static final int GL_TEXTURE3 = 33987;
  public static final int GL_TEXTURE30 = 34014;
  public static final int GL_TEXTURE31 = 34015;
  public static final int GL_TEXTURE4 = 33988;
  public static final int GL_TEXTURE5 = 33989;
  public static final int GL_TEXTURE6 = 33990;
  public static final int GL_TEXTURE7 = 33991;
  public static final int GL_TEXTURE8 = 33992;
  public static final int GL_TEXTURE9 = 33993;
  public static final int GL_TEXTURE_2D = 3553;
  public static final int GL_TEXTURE_BINDING_2D = 32873;
  public static final int GL_TEXTURE_BINDING_CUBE_MAP = 34068;
  public static final int GL_TEXTURE_CUBE_MAP = 34067;
  public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_X = 34070;
  public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_Y = 34072;
  public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_Z = 34074;
  public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_X = 34069;
  public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_Y = 34071;
  public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_Z = 34073;
  public static final int GL_TEXTURE_MAG_FILTER = 10240;
  public static final int GL_TEXTURE_MIN_FILTER = 10241;
  public static final int GL_TEXTURE_WRAP_S = 10242;
  public static final int GL_TEXTURE_WRAP_T = 10243;
  public static final int GL_TRIANGLES = 4;
  public static final int GL_TRIANGLE_FAN = 6;
  public static final int GL_TRIANGLE_STRIP = 5;
  public static final int GL_TRUE = 1;
  public static final int GL_UNPACK_ALIGNMENT = 3317;
  public static final int GL_UNSIGNED_BYTE = 5121;
  public static final int GL_UNSIGNED_INT = 5125;
  public static final int GL_UNSIGNED_SHORT = 5123;
  public static final int GL_UNSIGNED_SHORT_4_4_4_4 = 32819;
  public static final int GL_UNSIGNED_SHORT_5_5_5_1 = 32820;
  public static final int GL_UNSIGNED_SHORT_5_6_5 = 33635;
  public static final int GL_VALIDATE_STATUS = 35715;
  public static final int GL_VENDOR = 7936;
  public static final int GL_VERSION = 7938;
  public static final int GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING = 34975;
  public static final int GL_VERTEX_ATTRIB_ARRAY_ENABLED = 34338;
  public static final int GL_VERTEX_ATTRIB_ARRAY_NORMALIZED = 34922;
  public static final int GL_VERTEX_ATTRIB_ARRAY_POINTER = 34373;
  public static final int GL_VERTEX_ATTRIB_ARRAY_SIZE = 34339;
  public static final int GL_VERTEX_ATTRIB_ARRAY_STRIDE = 34340;
  public static final int GL_VERTEX_ATTRIB_ARRAY_TYPE = 34341;
  public static final int GL_VERTEX_SHADER = 35633;
  public static final int GL_VIEWPORT = 2978;
  public static final int GL_ZERO = 0;
  public static final String TAG = "GLES";
  public static final boolean LOGGABLED1 = true;
  public static final boolean LOGGABLED2 = false;

  public GLES20() {}

  public static String toString(int[] ints){
    StringBuilder sb = new StringBuilder();
    sb.append('[');
    for(int i=0; i<ints.length; i++){
      sb.append(ints[i]);
      if(i!=ints.length-1)
        sb.append(',');
    }
    sb.append(']');
    return sb.toString();
  }
  public static String toString(float[] ints){
    StringBuilder sb = new StringBuilder();
    sb.append('[');
    for(int i=0; i<ints.length; i++){
      sb.append(ints[i]);
      if(i!=ints.length-1)
        sb.append(',');
    }
    sb.append(']');
    return sb.toString();
  }

  public static void glBindBuffer(int glElementArrayBuffer, int i) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glBindBuffer paras:"+glElementArrayBuffer+" paras:"+i);
    android.opengl.GLES20.glBindBuffer(glElementArrayBuffer, i);
  }

  public static boolean glIsBuffer(int bufferHandle) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glIsBuffer paras:"+bufferHandle);
    return android.opengl.GLES20.glIsBuffer(bufferHandle);
  }

  public static void glGenBuffers(int i, int[] buff, int i1) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glGenBuffers paras:"+i+" paras:"+toString(buff) + " paras:" +i1);
    android.opengl.GLES20.glGenBuffers(i,buff, i1);
  }

  public static void glBufferData(int target, int i, Buffer buffer, int usage) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glBufferData paras:"+target+" paras:"+i+" paras:"+buffer + " paras:"+usage);
    android.opengl.GLES20.glBufferData(target, i, buffer,usage);
  }

  public static void glDeleteBuffers(int i, int[] ints, int i1) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glDeleteBuffers paras:"+i+" paras:"+toString(ints) + " paras:"+i1);
    android.opengl.GLES20.glDeleteBuffers(i, ints, i1);
  }

  public static void glBufferSubData(int target, int i, int i1, Buffer newData) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glBufferSubData paras:"+target+" paras:"+i+" paras:"+i1+" paras:"+newData);
    android.opengl.GLES20.glBufferSubData(target,i,i1,newData);
  }

  public static void glBindAttribLocation(int programHandle, int mLivesBufferHandle, String aLifetime) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glBindAttribLocation paras:"+programHandle+" paras:"+mLivesBufferHandle+" paras:"+aLifetime);
    android.opengl.GLES20.glBindAttribLocation(programHandle,mLivesBufferHandle,aLifetime);
  }

  public static void glEnableVertexAttribArray(int maLivesHandle) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glEnableVertexAttribArray paras:"+maLivesHandle);
    android.opengl.GLES20.glEnableVertexAttribArray(maLivesHandle);
  }

  public static void glVertexAttribPointer(int maLivesHandle, int i, int glFloat, boolean b, int i1, int i2) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glVertexAttribPointer paras:"+maLivesHandle+" paras:"+i+" paras:"+glFloat+" paras:"+b+" paras:"+i1+" paras:"+i2);
    android.opengl.GLES20.glVertexAttribPointer(maLivesHandle,i,glFloat, b, i1,i2);
  }

//  public static void glVertexAttribPointer(int indx,
//                                           int size,
//                                           int type,
//                                           boolean normalized,
//                                           int stride,
//                                           java.nio.Buffer ptr) {
//    if(LOGGABLED1) glGetError();
//    if(LOGGABLED2) Log.d(TAG, "glVertexAttribPointer paras:"+indx+" paras:"+size+" paras:"+type+" paras:"+normalized+" paras:"+stride+" paras:"+p);
//    android.opengl.GLES20.glVertexAttribPointer(indx, size, type, normalized, stride, ptr);
//  }

  public static void glDisable(int glCullFace) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glDisable paras:"+glCullFace);
    android.opengl.GLES20.glDisable(glCullFace);
  }

  public static void glEnable(int glCullFace) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glEnable paras:"+glCullFace);
    android.opengl.GLES20.glEnable(glCullFace);
  }

  public static void glCullFace(int glFront) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glCullFace paras:"+glFront);
    android.opengl.GLES20.glCullFace(glFront);
  }

  public static void glFrontFace(int glCcw) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glFrontFace paras:"+glCcw);
    android.opengl.GLES20.glFrontFace(glCcw);
  }

  public static void glBlendFunc(int mBlendFuncSFactor, int mBlendFuncDFactor) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glBlendFunc paras:"+mBlendFuncSFactor+" paras:"+mBlendFuncDFactor);
    android.opengl.GLES20.glBlendFunc(mBlendFuncSFactor,mBlendFuncDFactor);
  }

  public static void glDepthFunc(int glLess) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glDepthFunc paras:"+glLess);
    android.opengl.GLES20.glDepthFunc(glLess);
  }

  public static void glDepthMask(boolean mEnableDepthMask) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glDepthMask paras:"+mEnableDepthMask);
    android.opengl.GLES20.glDepthMask(mEnableDepthMask);
  }

  public static void glDrawElements(int mDrawingMode, int numIndices, int bufferType, int i) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glDrawElements paras:"+mDrawingMode+" paras:"+numIndices+" paras:"+bufferType+" paras:"+i);
    android.opengl.GLES20.glDrawElements(mDrawingMode,numIndices,bufferType,i);
  }

  public static int glGetError() {
    int ret = android.opengl.GLES20.glGetError();
    if (ret != 0) {
      Log.d(TAG, "glGetError retern error = :" + ret);
    }
    return ret;
  }

  public static void glUniform3fv(int muLightColorHandle, int i, float[] color, int i1) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glUniform3fv paras:"+muLightColorHandle+" paras:"+i+" paras:"+toString(color)+" paras:"+i1);
    android.opengl.GLES20.glUniform3fv(muLightColorHandle,i,color,i1);
  }

  public static void glUniform1f(int muLightPowerHandle, float power) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glUniform1f paras:"+muLightPowerHandle+" paras:"+power);
    android.opengl.GLES20.glUniform1f(muLightPowerHandle,power);
  }

  public static void glUniform4fv(int muLightAttenuationHandle, int i, float[] attenuation, int i1) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glUniform4fv paras:"+muLightAttenuationHandle+" paras:"+i+" paras:"+toString(attenuation)+" paras:"+i1);
    android.opengl.GLES20.glUniform4fv(muLightAttenuationHandle,i,attenuation,i1);
  }

  public static void glDeleteShader(int mVShaderHandle) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glDeleteShader paras:"+mVShaderHandle);
    android.opengl.GLES20.glDeleteShader(mVShaderHandle);
  }

  public static void glDeleteProgram(int mProgramHandle) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glDeleteProgram paras:"+mProgramHandle);
    android.opengl.GLES20.glDeleteProgram(mProgramHandle);
  }

  public static int glCreateShader(int shaderType) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glCreateShader paras:"+shaderType);
    return android.opengl.GLES20.glCreateShader(shaderType);
  }

  public static void glShaderSource(int shader, String source) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glShaderSource paras:"+shader+" paras:"+source);
    android.opengl.GLES20.glShaderSource(shader, source);
  }

  public static void glCompileShader(int shader) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glCompileShader paras:"+shader);
    android.opengl.GLES20.glCompileShader(shader);
  }

  public static void glGetShaderiv(int shader, int glCompileStatus, int[] compiled, int i) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glGetShaderiv paras:"+shader+" paras:"+glCompileStatus+" paras:"+toString(compiled)+" paras:"+i);
    android.opengl.GLES20.glGetShaderiv(shader,glCompileStatus,compiled,i);
  }

  public static String glGetShaderInfoLog(int shader) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glGetShaderInfoLog paras:"+shader);
    return android.opengl.GLES20.glGetShaderInfoLog(shader);
  }

  public static int glCreateProgram() {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glCreateProgram paras:");
    return android.opengl.GLES20.glCreateProgram();
  }

  public static void glAttachShader(int program, int mVShaderHandle) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glAttachShader paras:"+program+ " paras:"+mVShaderHandle);
    android.opengl.GLES20.glAttachShader(program,mVShaderHandle);
  }

  public static void glLinkProgram(int program) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glLinkProgram paras:"+program);
    android.opengl.GLES20.glLinkProgram(program);
  }

  public static void glGetProgramiv(int program, int glLinkStatus, int[] linkStatus, int i) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glGetProgramiv paras:"+program+" paras:"+glLinkStatus+" paras:"+toString(linkStatus)+" paras:"+i);
    android.opengl.GLES20.glGetProgramiv(program,glLinkStatus,linkStatus,i);
  }

  public static String glGetProgramInfoLog(int program) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glGetProgramInfoLog paras:"+program);
    return android.opengl.GLES20.glGetProgramInfoLog(program);
  }

  public static void glUseProgram(int mProgramHandle) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glUseProgram paras:"+mProgramHandle);
    android.opengl.GLES20.glUseProgram(mProgramHandle);
  }

  public static int glGetUniformLocation(int mProgramHandle, String textureName) {
    int ret = android.opengl.GLES20.glGetUniformLocation(mProgramHandle, textureName);
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glGetUniformLocation paras:"+mProgramHandle
      + " paras:"+textureName + " return:"+ret);
    return ret;
  }

  public static void glActiveTexture(int i) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glActiveTexture paras:"+i);
    android.opengl.GLES20.glActiveTexture(i);
  }

  public static void glBindTexture(int glTextureType, int textureId) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glBindTexture paras:"+glTextureType+" paras:"+textureId);
    android.opengl.GLES20.glBindTexture(glTextureType,textureId);
  }

  public static void glUniform1i(Integer integer, int i) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glUniform1i paras:"+integer+" paras:"+i);
    android.opengl.GLES20.glUniform1i(integer,i);
  }

  public static void glUniform2fv(int handle, int i, float[] value, int i1) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glUniform2fv paras:"+handle+" paras:"+i+" paras:"+toString(value)+" paras:"+i1);
    android.opengl.GLES20.glUniform2fv(handle,i,value,i1);
  }

  public static int glGetAttribLocation(int programHandle, String name) {
    int ret = android.opengl.GLES20.glGetAttribLocation(programHandle,name);
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glGetAttribLocation paras:"+programHandle+" paras:"+name + " return:"+ret);
    return ret;
  }

  public static void glUniformMatrix4fv(int muBoneMatrixHandle, int i, boolean b, float[] convertDoublesToFloats, int i1) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glUniformMatrix4fv paras:"+muBoneMatrixHandle+" paras:"+i+" paras:"+b+" paras:"+toString(convertDoublesToFloats)+" paras:"+i1);
    android.opengl.GLES20.glUniformMatrix4fv(muBoneMatrixHandle,i,b, convertDoublesToFloats, i1);
  }

  public static void glGenTextures(int i, int[] textures, int i1) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glGenTextures paras:"+i+" paras:"+toString(textures)+" paras:"+i1);
    android.opengl.GLES20.glGenTextures(i,textures,i1);
  }

  public static void glTexParameterf(int glTexture2d, int glTextureMinFilter, float i1) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glTexParameterf paras:"+glTexture2d+" paras:"+glTextureMinFilter+" paras:"+i1);
    android.opengl.GLES20.glTexParameterf(glTexture2d,glTextureMinFilter,i1);
  }

  public static void glTexParameteri(int glTexture2d, int glTextureWrapS, int glRepeat) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glTexParameteri paras:"+glTexture2d+" paras:"+glTextureWrapS+" paras:"+glRepeat);
    android.opengl.GLES20.glTexParameteri(glTexture2d,glTextureWrapS,glRepeat);
  }

  public static void glCompressedTexImage2D(int glTexture2d, int i, int mCompressionFormat, int mWidth, int mHeight, int i1, int i2, Buffer o) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glCompressedTexImage2D paras:"+glTexture2d+" paras:"+i+" paras:"+mCompressionFormat+" paras:"+mWidth+" paras:"+mHeight+" paras:"+i1+" paras:"+i2);
    android.opengl.GLES20.glCompressedTexImage2D(glTexture2d,i,mCompressionFormat,mWidth,mHeight,i1,i2,o);
  }

  public static void glDeleteTextures(int i, int[] ints, int i1) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glDeleteTextures paras:"+i+" paras:"+toString(ints)+" paras:"+i1);
    android.opengl.GLES20.glDeleteTextures(i,ints,i1);
  }

  public static void glCompressedTexSubImage2D(int glTexture2d, int i, int i1, int i2, int w, int h, int mCompressionFormat, int capacity, Buffer mByteBuffer) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glCompressedTexSubImage2D paras:"+glTexture2d+" paras:"+i+" paras:"+i1+" paras:"+i2+" paras:"+w+" paras:"+h+" paras:"+mCompressionFormat+" paras:"+capacity);
    android.opengl.GLES20.glCompressedTexSubImage2D(glTexture2d,i,i1,i2,w,h,mCompressionFormat,capacity,mByteBuffer);
  }

  public static String glGetString(int glVersion) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glGetString paras:"+glVersion);
    return android.opengl.GLES20.glGetString(glVersion);
  }

  public static void glViewport(int i, int i1, int width, int height) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glViewport paras:"+i+" paras:"+i1+" paras:"+width+" paras:"+height);
    android.opengl.GLES20.glViewport(i,i1,width,height);
  }

  public static void glGenFramebuffers(int i, int[] bufferHandles, int i1) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glGenFramebuffers paras:"+i+" paras:"+toString(bufferHandles)+" paras:"+i1);
    android.opengl.GLES20.glGenFramebuffers(i,bufferHandles,i1);
  }

  public static void glBindFramebuffer(int glFramebuffer, int mFrameBufferHandle) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glBindFramebuffer paras:"+glFramebuffer+" paras:"+mFrameBufferHandle);
    android.opengl.GLES20.glBindFramebuffer(glFramebuffer,mFrameBufferHandle);
  }

  public static void glFramebufferTexture2D(int glFramebuffer, int glColorAttachment0, int glTexture2d, int textureId, int i) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glFramebufferTexture2D paras:"+glFramebuffer+" paras:"+glColorAttachment0+" paras:"+glTexture2d+" paras:"+textureId+" paras:"+i);
    android.opengl.GLES20.glFramebufferTexture2D(glFramebuffer,glColorAttachment0,glTexture2d,textureId,i);
  }

  public static void glGenRenderbuffers(int i, int[] bufferHandles, int i1) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glGenRenderbuffers paras:"+i+" paras:"+toString(bufferHandles)+" paras:"+i1);
    android.opengl.GLES20.glGenRenderbuffers(i,bufferHandles,i1);
  }

  public static void glBindRenderbuffer(int glRenderbuffer, int bufferHandle) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glBindRenderbuffer paras:"+glRenderbuffer+" paras:"+bufferHandle);
    android.opengl.GLES20.glBindRenderbuffer(glRenderbuffer,bufferHandle);
  }

  public static void glRenderbufferStorage(int glRenderbuffer, int glDepthComponent16, int mWidth, int mHeight) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glRenderbufferStorage paras:"+glRenderbuffer+" paras:"+glDepthComponent16+" paras:"+mWidth+" paras:"+mHeight);
    android.opengl.GLES20.glRenderbufferStorage(glRenderbuffer,glDepthComponent16,mWidth,mHeight);
  }

  public static void glFramebufferRenderbuffer(int glFramebuffer, int glDepthAttachment, int glRenderbuffer, int bufferHandle) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glFramebufferRenderbuffer paras:"+glFramebuffer+" paras:"+glDepthAttachment+" paras:"+glRenderbuffer+" paras:"+bufferHandle);
    android.opengl.GLES20.glFramebufferRenderbuffer(glFramebuffer,glDepthAttachment,glRenderbuffer,bufferHandle);
  }

  public static int glCheckFramebufferStatus(int glFramebuffer) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glCheckFramebufferStatus paras:"+glFramebuffer);
    return android.opengl.GLES20.glCheckFramebufferStatus(glFramebuffer);
  }

  public static void glDeleteFramebuffers(int i, int[] ints, int i1) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glDeleteFramebuffers paras:"+i+" paras:"+toString(ints)+" paras:"+i1);
    android.opengl.GLES20.glDeleteFramebuffers(i,ints,i1);
  }

  public static void glReadPixels(int x, int i, int i1, int i2, int glRgba, int glUnsignedByte, Buffer pixelBuffer) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glReadPixels paras:"+x+" paras:"+i+" paras:"+i1+" paras:"+i2+" paras:"+glRgba+" paras:"+glUnsignedByte);
    android.opengl.GLES20.glReadPixels(x,i,i1,i2,glRgba,glUnsignedByte, pixelBuffer);
  }

  public static void glClearColor(float mRed, float mGreen, float mBlue, float mAlpha) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glClearColor paras:"+mRed+" paras:"+mGreen+" paras:"+mBlue+" paras:"+mAlpha);
    android.opengl.GLES20.glClearColor(mRed,mGreen,mBlue,mAlpha);
  }

  public static void glClearDepthf(float v) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glClearDepthf paras:"+v);
    android.opengl.GLES20.glClearDepthf(v);
  }

  public static void glClear(int clearMask) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glClear paras:"+clearMask);
    android.opengl.GLES20.glClear(clearMask);
  }

  public static void glTexImage2D(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, Buffer var8) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glTexImage2D paras:"+var0+" paras:"+var1+" paras:"+var2+" paras:"+var3+" paras:"+var4+" paras:"+var5+" paras:"+var6+" paras:"+var7);
    android.opengl.GLES20.glTexImage2D(var0, var1, var2, var3, var4, var5, var6, var7, var8);
  }

  public static void glGenerateMipmap(int glTexture2d) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glGenerateMipmap paras:"+glTexture2d);
    android.opengl.GLES20.glGenerateMipmap(glTexture2d);
  }

  public static void glUniform3f(int muShadowLightDirHandle, float x, float y, float z) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glUniform3f paras:"+muShadowLightDirHandle+" paras:"+x+" paras:"+y+" paras:"+z);
    android.opengl.GLES20.glUniform3f(muShadowLightDirHandle,x,y,z);
  }

  public static void glGetIntegerv(int glMaxVertexTextureImageUnits, int[] maxVertexTextureImageUnits, int i) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glGetIntegerv paras:"+glMaxVertexTextureImageUnits+" paras:"+toString(maxVertexTextureImageUnits)+" paras:"+i);
    android.opengl.GLES20.glGetIntegerv(glMaxVertexTextureImageUnits,maxVertexTextureImageUnits,i);
  }

  public static void glCopyTexImage2D(int glTexture2d, int i, int glRgb, int i1, int i2, int i3, int i4, int i5) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glCopyTexImage2D paras:"+glTexture2d+" paras:"+i+" paras:"+glRgb+" paras:"+i1+" paras:"+i2+" paras:"+i3+" paras:"+i4+" paras:"+i5);
    android.opengl.GLES20.glCopyTexImage2D(glTexture2d,i,glRgb,i1,i2,i3,i4,i5);
  }

  public static void glCopyTexSubImage2D(int glTexture2d, int i, int glRgb, int i1, int i2, int i3, int i4, int i5) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glCopyTexImage2D paras:"+glTexture2d+" paras:"+i+" paras:"+glRgb+" paras:"+i1+" paras:"+i2+" paras:"+i3+" paras:"+i4+" paras:"+i5);
    android.opengl.GLES20.glCopyTexSubImage2D(glTexture2d,i,glRgb,i1,i2,i3,i4,i5);
  }

  public static void glTexSubImage2D(int glTexture2d, int i, int i1, int i2, int mWidth, int mHeight, int mBitmapFormat, int glUnsignedByte, Buffer mByteBuffer) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glTexSubImage2D paras:"+glTexture2d+" paras:"+i+" paras:"+i1+" paras:"+i2+" paras:"+mWidth+" paras:"+mHeight+" paras:"+mBitmapFormat+" paras:"+glUnsignedByte);
    android.opengl.GLES20.glTexSubImage2D(glTexture2d,i,i1,i2,mWidth,mHeight,mBitmapFormat,glUnsignedByte,mByteBuffer);
  }

  public static void glHint(int glGenerateMipmapHint, int glNicest) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glHint paras:"+glGenerateMipmapHint+" paras:"+glNicest);
    android.opengl.GLES20.glHint(glGenerateMipmapHint,glNicest);
  }

  public static void glUniform4f(int muTintHandle, float v, float v1, float v2, float v3) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glUniform4f paras:"+muTintHandle+" paras:"+v+" paras:"+v1+" paras:"+v2+" paras:"+v3);
    android.opengl.GLES20.glUniform4f(muTintHandle,v,v1,v2,v3);
  }

  public static void glLineWidth(float mLineThickness) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glLineWidth paras:"+mLineThickness);
    android.opengl.GLES20.glLineWidth(mLineThickness);
  }

  public static void glStencilFunc(int glNotequal, int i, int i1) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glStencilFunc paras:"+glNotequal+" paras:"+i+" paras:"+i1);
    android.opengl.GLES20.glStencilFunc(glNotequal,i,i1);
  }

  public static void glUniformMatrix3fv(int muNormalMatrixHandle, int i, boolean b, float[] normalMatrix, int i1) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glUniformMatrix3fv paras:"+muNormalMatrixHandle+" paras:"+i+" paras:"+b+" paras:"+toString(normalMatrix)+" paras:"+i1);
    android.opengl.GLES20.glUniformMatrix3fv(muNormalMatrixHandle,i,b,normalMatrix,i1);
  }

  public static void glColorMask(boolean b, boolean b1, boolean b2, boolean b3) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glColorMask paras:"+b+" paras:"+b1+" paras:"+b2+" paras:"+b3);
    android.opengl.GLES20.glColorMask(b,b1,b2,b3);
  }

  public static void glStencilOp(int glReplace, int glReplace1, int glReplace2) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glStencilOp paras:"+glReplace+" paras:"+glReplace1+" paras:"+glReplace2);
    android.opengl.GLES20.glStencilOp(glReplace,glReplace1,glReplace2);
  }

  public static void glClearStencil(int clearValue) {
    if(LOGGABLED1) glGetError();
    if(LOGGABLED2) Log.d(TAG, "glClearStencil paras:"+clearValue);
    android.opengl.GLES20.glClearStencil(clearValue);
  }

  public static void glDrawArrays(int mode,
                                  int first,
                                  int count) {
    if(GLES20.LOGGABLED2) Log.d(TAG, "glDrawArrays paras:"+mode+" paras:"+first+" paras:"+count);

    android.opengl.GLES30.glDrawArrays(mode, first, count);
  }
}
