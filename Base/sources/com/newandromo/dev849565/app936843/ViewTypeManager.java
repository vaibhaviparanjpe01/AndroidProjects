package com.newandromo.dev849565.app936843;

import java.util.concurrent.atomic.AtomicInteger;

public final class ViewTypeManager {
    private static AtomicInteger nextViewType = new AtomicInteger(0);

    public static int getUniqueViewTypeId() {
        return nextViewType.getAndIncrement();
    }

    public static int getBlockOfViewTypeIds(int i) {
        if (i > 0) {
            return nextViewType.getAndAdd(i);
        }
        throw new IllegalArgumentException(i + " is invalid. numTypes must be > 0.");
    }
}
