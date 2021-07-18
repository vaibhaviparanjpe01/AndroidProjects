package com.newandromo.dev849565.app936843;

import android.text.method.LinkMovementMethod;

abstract class CustomLinkMovementMethod extends LinkMovementMethod {
    private static final String TAG = "CustomLinkMovementMethod";

    public abstract void onLinkClicked(String str);

    public abstract void onLinkError(String str);

    CustomLinkMovementMethod() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x004c A[Catch:{ ActivityNotFoundException -> 0x0050 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.widget.TextView r5, android.text.Spannable r6, android.view.MotionEvent r7) {
        /*
            r4 = this;
            int r0 = r7.getAction()
            r1 = 1
            if (r0 != r1) goto L_0x0045
            float r0 = r7.getX()
            int r0 = (int) r0
            float r2 = r7.getY()
            int r2 = (int) r2
            int r3 = r5.getTotalPaddingLeft()
            int r0 = r0 - r3
            int r3 = r5.getTotalPaddingTop()
            int r2 = r2 - r3
            int r3 = r5.getScrollX()
            int r0 = r0 + r3
            int r3 = r5.getScrollY()
            int r2 = r2 + r3
            android.text.Layout r3 = r5.getLayout()
            int r2 = r3.getLineForVertical(r2)
            float r0 = (float) r0
            int r0 = r3.getOffsetForHorizontal(r2, r0)
            java.lang.Class<android.text.style.URLSpan> r2 = android.text.style.URLSpan.class
            java.lang.Object[] r0 = r6.getSpans(r0, r0, r2)
            android.text.style.URLSpan[] r0 = (android.text.style.URLSpan[]) r0
            int r2 = r0.length
            if (r2 == 0) goto L_0x0045
            r2 = 0
            r0 = r0[r2]
            java.lang.String r0 = r0.getURL()
            goto L_0x0046
        L_0x0045:
            r0 = 0
        L_0x0046:
            boolean r5 = super.onTouchEvent(r5, r6, r7)     // Catch:{ ActivityNotFoundException -> 0x0050 }
            if (r0 == 0) goto L_0x004f
            r4.onLinkClicked(r0)     // Catch:{ ActivityNotFoundException -> 0x0050 }
        L_0x004f:
            return r5
        L_0x0050:
            r4.onLinkError(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newandromo.dev849565.app936843.CustomLinkMovementMethod.onTouchEvent(android.widget.TextView, android.text.Spannable, android.view.MotionEvent):boolean");
    }
}
