package android.support.v4.widget;

import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Support4Widget {

    @Retention(RetentionPolicy.SOURCE)
    private @interface EdgeGravity {
    }

    public static void setDrawerClosed(DrawerLayout drawerLayout, int i) {
        View findDrawerWithGravity = drawerLayout.findDrawerWithGravity(i);
        if (findDrawerWithGravity != null) {
            drawerLayout.moveDrawerToOffset(findDrawerWithGravity, 0.0f);
            drawerLayout.closeDrawer(findDrawerWithGravity);
            return;
        }
        throw new IllegalArgumentException("No drawer view found with gravity " + DrawerLayout.gravityToString(i));
    }
}
