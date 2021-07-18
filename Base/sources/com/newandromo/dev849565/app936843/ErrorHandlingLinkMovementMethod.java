package com.newandromo.dev849565.app936843;

import android.content.ActivityNotFoundException;
import android.text.Layout;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

class ErrorHandlingLinkMovementMethod extends LinkMovementMethod {
    private static final String TAG = "ErrorHandlingLinkMovementMethod";
    private static String errorMessage;
    private static ErrorHandlingLinkMovementMethod instance;

    public void onLinkError(String str) {
    }

    ErrorHandlingLinkMovementMethod() {
    }

    public static ErrorHandlingLinkMovementMethod getInstance() {
        if (instance == null) {
            instance = new ErrorHandlingLinkMovementMethod();
        } else {
            errorMessage = null;
        }
        return instance;
    }

    public static ErrorHandlingLinkMovementMethod withErrorMessage(String str) {
        errorMessage = str;
        return instance;
    }

    public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent motionEvent) {
        try {
            return super.onTouchEvent(textView, spannable, motionEvent);
        } catch (ActivityNotFoundException unused) {
            if (motionEvent.getAction() == 1) {
                int x = ((int) motionEvent.getX()) - textView.getTotalPaddingLeft();
                int y = ((int) motionEvent.getY()) - textView.getTotalPaddingTop();
                int scrollX = x + textView.getScrollX();
                int scrollY = y + textView.getScrollY();
                Layout layout = textView.getLayout();
                int offsetForHorizontal = layout.getOffsetForHorizontal(layout.getLineForVertical(scrollY), (float) scrollX);
                URLSpan[] uRLSpanArr = (URLSpan[]) spannable.getSpans(offsetForHorizontal, offsetForHorizontal, URLSpan.class);
                if (uRLSpanArr.length != 0) {
                    String url = uRLSpanArr[0].getURL();
                    onLinkError(url);
                    if (!(errorMessage == null || url == null)) {
                        Toast.makeText(textView.getContext(), String.format(errorMessage, new Object[]{url}), 0).show();
                    }
                }
            }
            return true;
        }
    }
}
