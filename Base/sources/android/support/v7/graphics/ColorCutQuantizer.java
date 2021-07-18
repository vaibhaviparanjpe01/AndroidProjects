package android.support.v7.graphics;

import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.graphics.Palette;
import android.util.TimingLogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

final class ColorCutQuantizer {
    static final int COMPONENT_BLUE = -1;
    static final int COMPONENT_GREEN = -2;
    static final int COMPONENT_RED = -3;
    private static final String LOG_TAG = "ColorCutQuantizer";
    private static final boolean LOG_TIMINGS = false;
    private static final int QUANTIZE_WORD_MASK = 31;
    private static final int QUANTIZE_WORD_WIDTH = 5;
    private static final Comparator<Vbox> VBOX_COMPARATOR_VOLUME = new Comparator<Vbox>() {
        public int compare(Vbox vbox, Vbox vbox2) {
            return vbox2.getVolume() - vbox.getVolume();
        }
    };
    final int[] mColors;
    final Palette.Filter[] mFilters;
    final int[] mHistogram;
    final List<Palette.Swatch> mQuantizedColors;
    private final float[] mTempHsl = new float[3];
    final TimingLogger mTimingLogger = null;

    private static int modifyWordWidth(int i, int i2, int i3) {
        return (i3 > i2 ? i << (i3 - i2) : i >> (i2 - i3)) & ((1 << i3) - 1);
    }

    static int quantizedBlue(int i) {
        return i & 31;
    }

    static int quantizedGreen(int i) {
        return (i >> 5) & 31;
    }

    static int quantizedRed(int i) {
        return (i >> 10) & 31;
    }

    ColorCutQuantizer(int[] iArr, int i, Palette.Filter[] filterArr) {
        this.mFilters = filterArr;
        int[] iArr2 = new int[32768];
        this.mHistogram = iArr2;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int quantizeFromRgb888 = quantizeFromRgb888(iArr[i2]);
            iArr[i2] = quantizeFromRgb888;
            iArr2[quantizeFromRgb888] = iArr2[quantizeFromRgb888] + 1;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < iArr2.length; i4++) {
            if (iArr2[i4] > 0 && shouldIgnoreColor(i4)) {
                iArr2[i4] = 0;
            }
            if (iArr2[i4] > 0) {
                i3++;
            }
        }
        int[] iArr3 = new int[i3];
        this.mColors = iArr3;
        int i5 = 0;
        for (int i6 = 0; i6 < iArr2.length; i6++) {
            if (iArr2[i6] > 0) {
                iArr3[i5] = i6;
                i5++;
            }
        }
        if (i3 <= i) {
            this.mQuantizedColors = new ArrayList();
            for (int i7 : iArr3) {
                this.mQuantizedColors.add(new Palette.Swatch(approximateToRgb888(i7), iArr2[i7]));
            }
            return;
        }
        this.mQuantizedColors = quantizePixels(i);
    }

    /* access modifiers changed from: package-private */
    public List<Palette.Swatch> getQuantizedColors() {
        return this.mQuantizedColors;
    }

    private List<Palette.Swatch> quantizePixels(int i) {
        PriorityQueue priorityQueue = new PriorityQueue(i, VBOX_COMPARATOR_VOLUME);
        priorityQueue.offer(new Vbox(0, this.mColors.length - 1));
        splitBoxes(priorityQueue, i);
        return generateAverageColors(priorityQueue);
    }

    private void splitBoxes(PriorityQueue<Vbox> priorityQueue, int i) {
        Vbox poll;
        while (priorityQueue.size() < i && (poll = priorityQueue.poll()) != null && poll.canSplit()) {
            priorityQueue.offer(poll.splitBox());
            priorityQueue.offer(poll);
        }
    }

    private List<Palette.Swatch> generateAverageColors(Collection<Vbox> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        for (Vbox averageColor : collection) {
            Palette.Swatch averageColor2 = averageColor.getAverageColor();
            if (!shouldIgnoreColor(averageColor2)) {
                arrayList.add(averageColor2);
            }
        }
        return arrayList;
    }

    private class Vbox {
        private int mLowerIndex;
        private int mMaxBlue;
        private int mMaxGreen;
        private int mMaxRed;
        private int mMinBlue;
        private int mMinGreen;
        private int mMinRed;
        private int mPopulation;
        private int mUpperIndex;

        Vbox(int i, int i2) {
            this.mLowerIndex = i;
            this.mUpperIndex = i2;
            fitBox();
        }

        /* access modifiers changed from: package-private */
        public final int getVolume() {
            return ((this.mMaxRed - this.mMinRed) + 1) * ((this.mMaxGreen - this.mMinGreen) + 1) * ((this.mMaxBlue - this.mMinBlue) + 1);
        }

        /* access modifiers changed from: package-private */
        public final boolean canSplit() {
            return getColorCount() > 1;
        }

        /* access modifiers changed from: package-private */
        public final int getColorCount() {
            return (this.mUpperIndex + 1) - this.mLowerIndex;
        }

        /* access modifiers changed from: package-private */
        public final void fitBox() {
            int[] iArr = ColorCutQuantizer.this.mColors;
            int[] iArr2 = ColorCutQuantizer.this.mHistogram;
            int i = Integer.MAX_VALUE;
            int i2 = Integer.MIN_VALUE;
            int i3 = 0;
            int i4 = Integer.MAX_VALUE;
            int i5 = Integer.MIN_VALUE;
            int i6 = Integer.MAX_VALUE;
            int i7 = Integer.MIN_VALUE;
            for (int i8 = this.mLowerIndex; i8 <= this.mUpperIndex; i8++) {
                int i9 = iArr[i8];
                i3 += iArr2[i9];
                int quantizedRed = ColorCutQuantizer.quantizedRed(i9);
                int quantizedGreen = ColorCutQuantizer.quantizedGreen(i9);
                int quantizedBlue = ColorCutQuantizer.quantizedBlue(i9);
                if (quantizedRed > i2) {
                    i2 = quantizedRed;
                }
                if (quantizedRed < i) {
                    i = quantizedRed;
                }
                if (quantizedGreen > i5) {
                    i5 = quantizedGreen;
                }
                if (quantizedGreen < i4) {
                    i4 = quantizedGreen;
                }
                if (quantizedBlue > i7) {
                    i7 = quantizedBlue;
                }
                if (quantizedBlue < i6) {
                    i6 = quantizedBlue;
                }
            }
            this.mMinRed = i;
            this.mMaxRed = i2;
            this.mMinGreen = i4;
            this.mMaxGreen = i5;
            this.mMinBlue = i6;
            this.mMaxBlue = i7;
            this.mPopulation = i3;
        }

        /* access modifiers changed from: package-private */
        public final Vbox splitBox() {
            if (canSplit()) {
                int findSplitPoint = findSplitPoint();
                Vbox vbox = new Vbox(findSplitPoint + 1, this.mUpperIndex);
                this.mUpperIndex = findSplitPoint;
                fitBox();
                return vbox;
            }
            throw new IllegalStateException("Can not split a box with only 1 color");
        }

        /* access modifiers changed from: package-private */
        public final int getLongestColorDimension() {
            int i = this.mMaxRed - this.mMinRed;
            int i2 = this.mMaxGreen - this.mMinGreen;
            int i3 = this.mMaxBlue - this.mMinBlue;
            if (i < i2 || i < i3) {
                return (i2 < i || i2 < i3) ? -1 : -2;
            }
            return -3;
        }

        /* access modifiers changed from: package-private */
        public final int findSplitPoint() {
            int longestColorDimension = getLongestColorDimension();
            int[] iArr = ColorCutQuantizer.this.mColors;
            int[] iArr2 = ColorCutQuantizer.this.mHistogram;
            ColorCutQuantizer.modifySignificantOctet(iArr, longestColorDimension, this.mLowerIndex, this.mUpperIndex);
            Arrays.sort(iArr, this.mLowerIndex, this.mUpperIndex + 1);
            ColorCutQuantizer.modifySignificantOctet(iArr, longestColorDimension, this.mLowerIndex, this.mUpperIndex);
            int i = this.mPopulation / 2;
            int i2 = 0;
            for (int i3 = this.mLowerIndex; i3 <= this.mUpperIndex; i3++) {
                i2 += iArr2[iArr[i3]];
                if (i2 >= i) {
                    return Math.min(this.mUpperIndex - 1, i3);
                }
            }
            return this.mLowerIndex;
        }

        /* access modifiers changed from: package-private */
        public final Palette.Swatch getAverageColor() {
            int[] iArr = ColorCutQuantizer.this.mColors;
            int[] iArr2 = ColorCutQuantizer.this.mHistogram;
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            for (int i5 = this.mLowerIndex; i5 <= this.mUpperIndex; i5++) {
                int i6 = iArr[i5];
                int i7 = iArr2[i6];
                i += i7;
                i2 += ColorCutQuantizer.quantizedRed(i6) * i7;
                i3 += ColorCutQuantizer.quantizedGreen(i6) * i7;
                i4 += i7 * ColorCutQuantizer.quantizedBlue(i6);
            }
            float f = (float) i;
            return new Palette.Swatch(ColorCutQuantizer.approximateToRgb888(Math.round(((float) i2) / f), Math.round(((float) i3) / f), Math.round(((float) i4) / f)), i);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0004, code lost:
        if (r4 > r5) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r3 = r2[r4];
        r2[r4] = quantizedRed(r3) | ((quantizedBlue(r3) << 10) | (quantizedGreen(r3) << 5));
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x001f, code lost:
        if (r4 > r5) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0021, code lost:
        r3 = r2[r4];
        r2[r4] = quantizedBlue(r3) | ((quantizedGreen(r3) << 10) | (quantizedRed(r3) << 5));
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void modifySignificantOctet(int[] r2, int r3, int r4, int r5) {
        /*
            switch(r3) {
                case -3: goto L_0x003a;
                case -2: goto L_0x001f;
                case -1: goto L_0x0004;
                default: goto L_0x0003;
            }
        L_0x0003:
            goto L_0x003a
        L_0x0004:
            if (r4 > r5) goto L_0x003a
            r3 = r2[r4]
            int r0 = quantizedBlue(r3)
            int r0 = r0 << 10
            int r1 = quantizedGreen(r3)
            int r1 = r1 << 5
            r0 = r0 | r1
            int r3 = quantizedRed(r3)
            r3 = r3 | r0
            r2[r4] = r3
            int r4 = r4 + 1
            goto L_0x0004
        L_0x001f:
            if (r4 > r5) goto L_0x003a
            r3 = r2[r4]
            int r0 = quantizedGreen(r3)
            int r0 = r0 << 10
            int r1 = quantizedRed(r3)
            int r1 = r1 << 5
            r0 = r0 | r1
            int r3 = quantizedBlue(r3)
            r3 = r3 | r0
            r2[r4] = r3
            int r4 = r4 + 1
            goto L_0x001f
        L_0x003a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.graphics.ColorCutQuantizer.modifySignificantOctet(int[], int, int, int):void");
    }

    private boolean shouldIgnoreColor(int i) {
        int approximateToRgb888 = approximateToRgb888(i);
        ColorUtils.colorToHSL(approximateToRgb888, this.mTempHsl);
        return shouldIgnoreColor(approximateToRgb888, this.mTempHsl);
    }

    private boolean shouldIgnoreColor(Palette.Swatch swatch) {
        return shouldIgnoreColor(swatch.getRgb(), swatch.getHsl());
    }

    private boolean shouldIgnoreColor(int i, float[] fArr) {
        if (this.mFilters != null && this.mFilters.length > 0) {
            for (Palette.Filter isAllowed : this.mFilters) {
                if (!isAllowed.isAllowed(i, fArr)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int quantizeFromRgb888(int i) {
        return modifyWordWidth(Color.blue(i), 8, 5) | (modifyWordWidth(Color.red(i), 8, 5) << 10) | (modifyWordWidth(Color.green(i), 8, 5) << 5);
    }

    static int approximateToRgb888(int i, int i2, int i3) {
        return Color.rgb(modifyWordWidth(i, 5, 8), modifyWordWidth(i2, 5, 8), modifyWordWidth(i3, 5, 8));
    }

    private static int approximateToRgb888(int i) {
        return approximateToRgb888(quantizedRed(i), quantizedGreen(i), quantizedBlue(i));
    }
}
