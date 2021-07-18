package android.support.v4.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CustomDrawerLayout extends DrawerLayout {

    @Retention(RetentionPolicy.SOURCE)
    private @interface EdgeGravity {
    }

    public CustomDrawerLayout(Context context) {
        super(context);
    }

    public CustomDrawerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomDrawerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setDrawerClosed(View view) {
        if (isDrawerView(view)) {
            moveDrawerToOffset(view, 0.0f);
            closeDrawer(view);
            return;
        }
        throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
    }

    public void setDrawerClosed(int i) {
        View findDrawerWithGravity = findDrawerWithGravity(i);
        if (findDrawerWithGravity != null) {
            moveDrawerToOffset(findDrawerWithGravity, 0.0f);
            closeDrawer(findDrawerWithGravity);
            return;
        }
        throw new IllegalArgumentException("No drawer view found with gravity " + gravityToString(i));
    }
}
