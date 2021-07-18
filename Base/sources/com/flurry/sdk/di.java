package com.flurry.sdk;

import com.flurry.sdk.dk;
import java.io.InputStream;
import java.io.OutputStream;

public final class di<RequestObjectType, ResponseObjectType> extends dk {
    public a<RequestObjectType, ResponseObjectType> a;
    public RequestObjectType b;
    public dw<RequestObjectType> c;
    public dw<ResponseObjectType> d;
    /* access modifiers changed from: private */
    public ResponseObjectType v;

    public interface a<RequestObjectType, ResponseObjectType> {
        void a(di<RequestObjectType, ResponseObjectType> diVar, ResponseObjectType responseobjecttype);
    }

    public final void a() {
        this.l = new dk.b() {
            public final void a(OutputStream outputStream) throws Exception {
                if (di.this.b != null && di.this.c != null) {
                    di.this.c.a(outputStream, di.this.b);
                }
            }

            public final void a(dk dkVar, InputStream inputStream) throws Exception {
                if (dkVar.d() && di.this.d != null) {
                    Object unused = di.this.v = di.this.d.a(inputStream);
                }
            }

            public final void a() {
                di.d(di.this);
            }
        };
        super.a();
    }

    static /* synthetic */ void d(di diVar) {
        if (diVar.a != null && !diVar.g()) {
            diVar.a.a(diVar, diVar.v);
        }
    }
}
