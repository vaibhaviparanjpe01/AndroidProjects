package android.databinding;

import android.util.SparseArray;
import android.view.View;
import com.newandromo.dev849565.app936843.R;
import com.newandromo.dev849565.app936843.databinding.AboutUsMainBinding;
import com.newandromo.dev849565.app936843.databinding.DataDummyBinding;
import com.newandromo.dev849565.app936843.databinding.GroupListIconDestTitle01Binding;
import com.newandromo.dev849565.app936843.databinding.MaterialDashboardVerticalMainBinding;
import com.newandromo.dev849565.app936843.databinding.WebviewMainBinding;

class DataBinderMapperImpl extends DataBinderMapper {
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        return null;
    }

    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        switch (i) {
            case R.layout.about_us_main /*2131427357*/:
                Object tag = view.getTag();
                if (tag == null) {
                    throw new RuntimeException("view must have a tag");
                } else if ("layout/about_us_main_0".equals(tag)) {
                    return new AboutUsMainBinding(dataBindingComponent, view);
                } else {
                    throw new IllegalArgumentException("The tag for about_us_main is invalid. Received: " + tag);
                }
            case R.layout.data_dummy /*2131427362*/:
                Object tag2 = view.getTag();
                if (tag2 == null) {
                    throw new RuntimeException("view must have a tag");
                } else if ("layout/data_dummy_0".equals(tag2)) {
                    return new DataDummyBinding(dataBindingComponent, view);
                } else {
                    throw new IllegalArgumentException("The tag for data_dummy is invalid. Received: " + tag2);
                }
            case R.layout.group_list_icon_dest_title_01 /*2131427380*/:
                Object tag3 = view.getTag();
                if (tag3 == null) {
                    throw new RuntimeException("view must have a tag");
                } else if ("layout/group_list_icon_dest_title_01_0".equals(tag3)) {
                    return new GroupListIconDestTitle01Binding(dataBindingComponent, view);
                } else {
                    throw new IllegalArgumentException("The tag for group_list_icon_dest_title_01 is invalid. Received: " + tag3);
                }
            case R.layout.material_dashboard_vertical_main /*2131427382*/:
                Object tag4 = view.getTag();
                if (tag4 == null) {
                    throw new RuntimeException("view must have a tag");
                } else if ("layout/material_dashboard_vertical_main_0".equals(tag4)) {
                    return new MaterialDashboardVerticalMainBinding(dataBindingComponent, view);
                } else {
                    throw new IllegalArgumentException("The tag for material_dashboard_vertical_main is invalid. Received: " + tag4);
                }
            case R.layout.webview_main /*2131427432*/:
                Object tag5 = view.getTag();
                if (tag5 == null) {
                    throw new RuntimeException("view must have a tag");
                } else if ("layout/webview_main_0".equals(tag5)) {
                    return new WebviewMainBinding(dataBindingComponent, view);
                } else {
                    throw new IllegalArgumentException("The tag for webview_main is invalid. Received: " + tag5);
                }
            default:
                return null;
        }
    }

    public int getLayoutId(String str) {
        if (str == null) {
            return 0;
        }
        switch (str.hashCode()) {
            case -1323238007:
                if (str.equals("layout/data_dummy_0")) {
                    return R.layout.data_dummy;
                }
                break;
            case 530586549:
                if (str.equals("layout/webview_main_0")) {
                    return R.layout.webview_main;
                }
                break;
            case 1264953429:
                if (str.equals("layout/material_dashboard_vertical_main_0")) {
                    return R.layout.material_dashboard_vertical_main;
                }
                break;
            case 1535007956:
                if (str.equals("layout/about_us_main_0")) {
                    return R.layout.about_us_main;
                }
                break;
            case 1563321676:
                if (str.equals("layout/group_list_icon_dest_title_01_0")) {
                    return R.layout.group_list_icon_dest_title_01;
                }
                break;
        }
        return 0;
    }

    public String convertBrIdToString(int i) {
        return InnerBrLookup.sKeys.get(i);
    }

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys = new SparseArray<>();

        private InnerBrLookup() {
        }

        static {
            sKeys.put(0, "_all");
            sKeys.put(0, "_all");
            sKeys.put(1, "auto");
            sKeys.put(2, "primaryColor");
            sKeys.put(3, "prettyPubDate");
            sKeys.put(4, "toolbarColor");
            sKeys.put(5, "palette");
            sKeys.put(6, "autoBackgroundColor");
            sKeys.put(7, "backgroundType");
            sKeys.put(8, "thumbnail");
            sKeys.put(9, "item");
            sKeys.put(10, "hasDescription");
            sKeys.put(11, "wideImageRatio");
            sKeys.put(12, "pubDate");
            sKeys.put(13, "subtitle");
            sKeys.put(14, "position");
            sKeys.put(15, "autoSecondaryTextColor");
            sKeys.put(16, "autoBodyTextColor");
            sKeys.put(17, "palette0");
            sKeys.put(18, "palette2");
            sKeys.put(19, "palette1");
            sKeys.put(20, "icon");
            sKeys.put(21, "palette4");
            sKeys.put(22, "description");
            sKeys.put(23, "palette3");
            sKeys.put(24, "palette6");
            sKeys.put(25, "autoPrimaryTextColor");
            sKeys.put(26, "palette5");
            sKeys.put(27, "title");
            sKeys.put(28, "palette8");
            sKeys.put(29, "palette7");
            sKeys.put(30, "backgroundIndex");
            sKeys.put(31, "palette9");
            sKeys.put(32, "isDarkTheme");
            sKeys.put(33, "timeAgo");
            sKeys.put(34, "hasSubtitle");
            sKeys.put(35, "defaultLayoutBinding");
            sKeys.put(36, "compactTimeAgo");
            sKeys.put(37, "hasPubDate");
            sKeys.put(38, "imageFallbackColor");
            sKeys.put(39, "backgroundColor");
            sKeys.put(40, "wideImage");
            sKeys.put(41, "auto8");
            sKeys.put(42, "autoTitleTextColor");
            sKeys.put(43, "auto7");
            sKeys.put(44, "auto9");
            sKeys.put(45, "auto4");
            sKeys.put(46, "squareImage");
            sKeys.put(47, "auto3");
            sKeys.put(48, "auto6");
            sKeys.put(49, "holder");
            sKeys.put(50, "auto5");
            sKeys.put(51, "auto0");
            sKeys.put(52, "titleGravity");
            sKeys.put(53, "auto2");
            sKeys.put(54, "auto1");
        }
    }
}
