package com.newandromo.dev849565.app936843;

import android.opengl.GLES10;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

public final class TextureSizeEGL10 {
    private TextureSizeEGL10() {
    }

    static int getMaxTextureSizeEGL10() {
        EGL10 egl10 = (EGL10) EGLContext.getEGL();
        EGLDisplay eglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        if (eglGetDisplay == EGL10.EGL_NO_DISPLAY) {
            throw new RuntimeException("eglGetDisplay failed");
        } else if (egl10.eglInitialize(eglGetDisplay, new int[2])) {
            EGLConfig[] eGLConfigArr = new EGLConfig[1];
            int[] iArr = new int[1];
            egl10.eglChooseConfig(eglGetDisplay, new int[]{12351, 12430, 12329, 0, 12339, 1, 12344}, eGLConfigArr, 1, iArr);
            if (iArr[0] != 0) {
                EGLConfig eGLConfig = eGLConfigArr[0];
                EGLSurface eglCreatePbufferSurface = egl10.eglCreatePbufferSurface(eglGetDisplay, eGLConfig, new int[]{12375, 4, 12374, 4, 12344});
                if (eglCreatePbufferSurface != EGL10.EGL_NO_SURFACE) {
                    EGLContext eglCreateContext = egl10.eglCreateContext(eglGetDisplay, eGLConfig, EGL10.EGL_NO_CONTEXT, new int[]{12440, 1, 12344});
                    checkEglError(egl10, "eglCreateContext");
                    if (eglCreateContext == EGL10.EGL_NO_CONTEXT || eglCreateContext == null) {
                        throw new RuntimeException("eglCreateContext failed");
                    } else if (egl10.eglMakeCurrent(eglGetDisplay, eglCreatePbufferSurface, eglCreatePbufferSurface, eglCreateContext)) {
                        int[] iArr2 = new int[1];
                        GLES10.glGetIntegerv(3379, iArr2, 0);
                        egl10.eglMakeCurrent(eglGetDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                        egl10.eglDestroySurface(eglGetDisplay, eglCreatePbufferSurface);
                        egl10.eglDestroyContext(eglGetDisplay, eglCreateContext);
                        egl10.eglTerminate(eglGetDisplay);
                        return iArr2[0];
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
    }

    private static void checkEglError(EGL10 egl10, String str) {
        int eglGetError = egl10.eglGetError();
        if (eglGetError != 12288) {
            throw new RuntimeException(str + ": EGL error: 0x" + Integer.toHexString(eglGetError));
        }
    }
}
