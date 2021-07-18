package android.support.v7.preference;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceGroup;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

final class CollapsiblePreferenceGroupController implements PreferenceGroup.PreferenceInstanceStateCallback {
    private final Context mContext;
    /* access modifiers changed from: private */
    public int mMaxPreferenceToShow;
    /* access modifiers changed from: private */
    public final PreferenceGroupAdapter mPreferenceGroupAdapter;

    CollapsiblePreferenceGroupController(PreferenceGroup preferenceGroup, PreferenceGroupAdapter preferenceGroupAdapter) {
        this.mPreferenceGroupAdapter = preferenceGroupAdapter;
        this.mMaxPreferenceToShow = preferenceGroup.getInitialExpandedChildrenCount();
        this.mContext = preferenceGroup.getContext();
        preferenceGroup.setPreferenceInstanceStateCallback(this);
    }

    public List<Preference> createVisiblePreferencesList(List<Preference> list) {
        ArrayList arrayList = new ArrayList(list.size());
        int i = 0;
        for (Preference next : list) {
            if (next.isVisible()) {
                if (i < this.mMaxPreferenceToShow) {
                    arrayList.add(next);
                }
                if (!(next instanceof PreferenceGroup)) {
                    i++;
                }
            }
        }
        if (showLimitedChildren() && i > this.mMaxPreferenceToShow) {
            arrayList.add(createExpandButton(arrayList, list));
        }
        return arrayList;
    }

    public boolean onPreferenceVisibilityChange(Preference preference) {
        if (!showLimitedChildren()) {
            return false;
        }
        this.mPreferenceGroupAdapter.onPreferenceHierarchyChange(preference);
        return true;
    }

    public Parcelable saveInstanceState(Parcelable parcelable) {
        SavedState savedState = new SavedState(parcelable);
        savedState.mMaxPreferenceToShow = this.mMaxPreferenceToShow;
        return savedState;
    }

    public Parcelable restoreInstanceState(Parcelable parcelable) {
        if (parcelable == null || !parcelable.getClass().equals(SavedState.class)) {
            return parcelable;
        }
        SavedState savedState = (SavedState) parcelable;
        int i = savedState.mMaxPreferenceToShow;
        if (this.mMaxPreferenceToShow != i) {
            this.mMaxPreferenceToShow = i;
            this.mPreferenceGroupAdapter.onPreferenceHierarchyChange((Preference) null);
        }
        return savedState.getSuperState();
    }

    private ExpandButton createExpandButton(List<Preference> list, List<Preference> list2) {
        ExpandButton expandButton = new ExpandButton(this.mContext, list, list2);
        expandButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                int unused = CollapsiblePreferenceGroupController.this.mMaxPreferenceToShow = Integer.MAX_VALUE;
                CollapsiblePreferenceGroupController.this.mPreferenceGroupAdapter.onPreferenceHierarchyChange(preference);
                return true;
            }
        });
        return expandButton;
    }

    private boolean showLimitedChildren() {
        return this.mMaxPreferenceToShow != Integer.MAX_VALUE;
    }

    static class ExpandButton extends Preference {
        ExpandButton(Context context, List<Preference> list, List<Preference> list2) {
            super(context);
            initLayout();
            setSummary(list, list2);
        }

        private void initLayout() {
            setLayoutResource(R.layout.expand_button);
            setIcon(R.drawable.ic_arrow_down_24dp);
            setTitle(R.string.expand_button_title);
            setOrder(999);
        }

        private void setSummary(List<Preference> list, List<Preference> list2) {
            CharSequence charSequence = null;
            for (int indexOf = list2.indexOf(list.get(list.size() - 1)) + 1; indexOf < list2.size(); indexOf++) {
                Preference preference = list2.get(indexOf);
                if (!(preference instanceof PreferenceGroup) && preference.isVisible()) {
                    CharSequence title = preference.getTitle();
                    if (!TextUtils.isEmpty(title)) {
                        if (charSequence == null) {
                            charSequence = title;
                        } else {
                            charSequence = getContext().getString(R.string.summary_collapsed_preference_list, new Object[]{charSequence, title});
                        }
                    }
                }
            }
            setSummary(charSequence);
        }

        public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
            super.onBindViewHolder(preferenceViewHolder);
            preferenceViewHolder.setDividerAllowedAbove(false);
        }
    }

    static class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int mMaxPreferenceToShow;

        SavedState(Parcel parcel) {
            super(parcel);
            this.mMaxPreferenceToShow = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.mMaxPreferenceToShow);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }
}
