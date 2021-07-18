package com.newandromo.dev849565.app936843;

import android.annotation.TargetApi;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.os.Build;

public final class TextureSize {
    private static final String TAG = "TextureSize";
    private static int maxTextureSize;

    private TextureSize() {
    }

    public static int getMaxTextureSize() {
        if (maxTextureSize == 0) {
            try {
                if (Build.VERSION.SDK_INT >= 17) {
                    maxTextureSize = getMaxTextureSizeEGL14();
                } else {
                    maxTextureSize = TextureSizeEGL10.getMaxTextureSizeEGL10();
                }
            } catch (Exception unused) {
            }
        }
        return maxTextureSize;
    }

    public static boolean isMaxTextureSizeKnown() {
        return maxTextureSize > 0;
    }

    @TargetApi(17)
    private static int getMaxTextureSizeEGL14() {
        EGLDisplay eglGetDisplay = EGL14.eglGetDisplay(0);
        if (eglGetDisplay != EGL14.EGL_NO_DISPLAY) {
            int[] iArr = new int[2];
            if (EGL14.eglInitialize(eglGetDisplay, iArr, 0, iArr, 1)) {
                EGLConfig[] eGLConfigArr = new EGLConfig[1];
                int[] iArr2 = new int[1];
                EGL14.eglChooseConfig(eglGetDisplay, new int[]{12351, 12430, 12329, 0, 12352, 4, 12339, 1, 12344}, 0, eGLConfigArr, 0, 1, iArr2, 0);
                if (iArr2[0] != 0) {
                    EGLConfig eGLConfig = eGLConfigArr[0];
                    EGLSurface eglCreatePbufferSurface = EGL14.eglCreatePbufferSurface(eglGetDisplay, eGLConfig, new int[]{12375, 4, 12374, 4, 12344}, 0);
                    if (eglCreatePbufferSurface != EGL14.EGL_NO_SURFACE) {
                        EGLContext eglCreateContext = EGL14.eglCreateContext(eglGetDisplay, eGLConfig, EGL14.EGL_NO_CONTEXT, new int[]{12440, 2, 12344}, 0);
                        checkEglError("eglCreateContext");
                        if (eglCreateContext == EGL14.EGL_NO_CONTEXT || eglCreateContext == null) {
                            throw new RuntimeException("eglCreateContext failed");
                        } else if (EGL14.eglMakeCurrent(eglGetDisplay, eglCreatePbufferSurface, eglCreatePbufferSurface, eglCreateContext)) {
                            int[] iArr3 = new int[1];
                            GLES20.glGetIntegerv(3379, iArr3, 0);
                            EGL14.eglMakeCurrent(eglGetDisplay, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
                            EGL14.eglDestroySurface(eglGetDisplay, eglCreatePbufferSurface);
                            EGL14.eglDestroyContext(eglGetDisplay, eglCreateContext);
                            EGL14.eglTerminate(eglGetDisplay);
                            return iArr3[0];
                        } else {
                            throw new RuntimeException("eglMakeCurrent failed");
                        }
                    } else {
                        throw new RuntimeException("eglCreatePbufferSurface failed");
                    }
                } else {
                    throw new RuntimeException("chooseConfig failed");
                }
            } else {
                throw new RuntimeException("eglInitialize failed");
            }
        } else {
            throw new RuntimeException("eglGetDisplay failed");
        }
    }

    private static void checkEglError(String str) {
        int eglGetError = EGL14.eglGetError();
        if (eglGetError != 12288) {
            throw new RuntimeException(str + ": EGL error: 0x" + Integer.toHexString(eglGetError));
        }
    }
}
