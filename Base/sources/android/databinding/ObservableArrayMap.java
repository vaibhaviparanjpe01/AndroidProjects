package android.databinding;

import android.databinding.ObservableMap;
import android.support.v4.util.ArrayMap;
import java.util.Collection;

public class ObservableArrayMap<K, V> extends ArrayMap<K, V> implements ObservableMap<K, V> {
    private transient MapChangeRegistry mListeners;

    public void addOnMapChangedCallback(ObservableMap.OnMapChangedCallback<? extends ObservableMap<K, V>, K, V> onMapChangedCallback) {
        if (this.mListeners == null) {
            this.mListeners = new MapChangeRegistry();
        }
        this.mListeners.add(onMapChangedCallback);
    }

    public void removeOnMapChangedCallback(ObservableMap.OnMapChangedCallback<? extends ObservableMap<K, V>, K, V> onMapChangedCallback) {
        if (this.mListeners != null) {
            this.mListeners.remove(onMapChangedCallback);
        }
    }

    public void clear() {
        if (!isEmpty()) {
            super.clear();
            notifyChange((Object) null);
        }
    }

    public V put(K k, V v) {
        super.put(k, v);
        notifyChange(k);
        return v;
    }

    public boolean removeAll(Collection<?> collection) {
        boolean z = false;
        for (Object indexOfKey : collection) {
            int indexOfKey2 = indexOfKey(indexOfKey);
            if (indexOfKey2 >= 0) {
                z = true;
                removeAt(indexOfKey2);
            }
        }
        return z;
    }

    public boolean retainAll(Collection<?> collection) {
        boolean z = false;
        for (int size = size() - 1; size >= 0; size--) {
            if (!collection.contains(keyAt(size))) {
                removeAt(size);
                z = true;
            }
        }
        return z;
    }

    public V removeAt(int i) {
        Object keyAt = keyAt(i);
        V removeAt = super.removeAt(i);
        if (removeAt != null) {
            notifyChange(keyAt);
        }
        return removeAt;
    }

    public V setValueAt(int i, V v) {
        Object keyAt = keyAt(i);
        V valueAt = super.setValueAt(i, v);
        notifyChange(keyAt);
        return valueAt;
    }

    private void notifyChange(Object obj) {
        if (this.mListeners != null) {
            this.mListeners.notifyCallbacks(this, 0, obj);
        }
    }
}
