package com.newandromo.dev849565.app936843;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public final class CollectionUtils {
    private static final String TAG = "CollectionUtils";

    public static <T> boolean comparatorEquals(Collection<T> collection, Collection<T> collection2, Comparator<T> comparator) {
        if (collection == null) {
            return collection2 == null;
        }
        if (collection2 == null || collection.size() != collection2.size()) {
            return false;
        }
        Iterator<T> it = collection.iterator();
        Iterator<T> it2 = collection2.iterator();
        while (it.hasNext() && it2.hasNext()) {
            if (comparator.compare(it.next(), it2.next()) != 0) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean areEqual(List<T> list, List<T> list2) {
        if (list == null) {
            return list2 == null;
        }
        return list.equals(list2);
    }

    public static <T> boolean areEqual(List<T> list, List<T> list2, Comparator<? super T> comparator) {
        if (list == null) {
            return list2 == null;
        }
        if (list2 == null || list.size() != list2.size()) {
            return false;
        }
        Iterator<T> it = list2.iterator();
        for (T compare : list) {
            if (comparator.compare(compare, it.next()) != 0) {
                return false;
            }
        }
        return true;
    }

    public static <T extends Comparable<? super T>> boolean areEqualIgnoringOrder(List<T> list, List<T> list2) {
        if (list == null) {
            return list2 == null;
        }
        if (list2 == null || list.size() != list2.size()) {
            return false;
        }
        ArrayList arrayList = new ArrayList(list);
        ArrayList arrayList2 = new ArrayList(list2);
        Collections.sort(arrayList);
        Collections.sort(arrayList2);
        return arrayList.equals(arrayList2);
    }

    public static <T> boolean areEqualIgnoringOrder(List<T> list, List<T> list2, Comparator<? super T> comparator) {
        if (list.size() != list2.size()) {
            return false;
        }
        ArrayList<Object> arrayList = new ArrayList<>(list);
        ArrayList arrayList2 = new ArrayList(list2);
        Collections.sort(arrayList, comparator);
        Collections.sort(arrayList2, comparator);
        Iterator it = arrayList2.iterator();
        for (Object compare : arrayList) {
            if (comparator.compare(compare, it.next()) != 0) {
                return false;
            }
        }
        return true;
    }
}
