package com.bumptech.glide.gifdecoder;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import com.flurry.android.Constants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;

public class GifDecoder {
    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    private static final int DISPOSAL_BACKGROUND = 2;
    private static final int DISPOSAL_NONE = 1;
    private static final int DISPOSAL_PREVIOUS = 3;
    private static final int DISPOSAL_UNSPECIFIED = 0;
    private static final int INITIAL_FRAME_POINTER = -1;
    private static final int MAX_STACK_SIZE = 4096;
    private static final int NULL_CODE = -1;
    public static final int STATUS_FORMAT_ERROR = 1;
    public static final int STATUS_OK = 0;
    public static final int STATUS_OPEN_ERROR = 2;
    public static final int STATUS_PARTIAL_DECODE = 3;
    private static final String TAG = "GifDecoder";
    public static final int TOTAL_ITERATION_COUNT_FOREVER = 0;
    private int[] act;
    private BitmapProvider bitmapProvider;
    private final byte[] block = new byte[256];
    private byte[] data;
    private int framePointer;
    private GifHeader header;
    private byte[] mainPixels;
    private int[] mainScratch;
    private GifHeaderParser parser;
    private final int[] pct = new int[256];
    private byte[] pixelStack;
    private short[] prefix;
    private Bitmap previousImage;
    private ByteBuffer rawData;
    private boolean savePrevious;
    private int status;
    private byte[] suffix;

    public interface BitmapProvider {
        Bitmap obtain(int i, int i2, Bitmap.Config config);

        void release(Bitmap bitmap);
    }

    public GifDecoder(BitmapProvider bitmapProvider2) {
        this.bitmapProvider = bitmapProvider2;
        this.header = new GifHeader();
    }

    public int getWidth() {
        return this.header.width;
    }

    public int getHeight() {
        return this.header.height;
    }

    public byte[] getData() {
        return this.data;
    }

    public int getStatus() {
        return this.status;
    }

    public void advance() {
        this.framePointer = (this.framePointer + 1) % this.header.frameCount;
    }

    public int getDelay(int i) {
        if (i < 0 || i >= this.header.frameCount) {
            return -1;
        }
        return this.header.frames.get(i).delay;
    }

    public int getNextDelay() {
        if (this.header.frameCount <= 0 || this.framePointer < 0) {
            return -1;
        }
        return getDelay(this.framePointer);
    }

    public int getFrameCount() {
        return this.header.frameCount;
    }

    public int getCurrentFrameIndex() {
        return this.framePointer;
    }

    public void resetFrameIndex() {
        this.framePointer = -1;
    }

    @Deprecated
    public int getLoopCount() {
        if (this.header.loopCount == -1) {
            return 1;
        }
        return this.header.loopCount;
    }

    public int getNetscapeLoopCount() {
        return this.header.loopCount;
    }

    public int getTotalIterationCount() {
        if (this.header.loopCount == -1) {
            return 1;
        }
        if (this.header.loopCount == 0) {
            return 0;
        }
        return this.header.loopCount + 1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00c9, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized android.graphics.Bitmap getNextFrame() {
        /*
            r7 = this;
            monitor-enter(r7)
            com.bumptech.glide.gifdecoder.GifHeader r0 = r7.header     // Catch:{ all -> 0x00ca }
            int r0 = r0.frameCount     // Catch:{ all -> 0x00ca }
            r1 = 3
            r2 = 1
            if (r0 <= 0) goto L_0x000d
            int r0 = r7.framePointer     // Catch:{ all -> 0x00ca }
            if (r0 >= 0) goto L_0x003b
        L_0x000d:
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00ca }
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00ca }
            if (r0 == 0) goto L_0x0039
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00ca }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ca }
            r3.<init>()     // Catch:{ all -> 0x00ca }
            java.lang.String r4 = "unable to decode frame, frameCount="
            r3.append(r4)     // Catch:{ all -> 0x00ca }
            com.bumptech.glide.gifdecoder.GifHeader r4 = r7.header     // Catch:{ all -> 0x00ca }
            int r4 = r4.frameCount     // Catch:{ all -> 0x00ca }
            r3.append(r4)     // Catch:{ all -> 0x00ca }
            java.lang.String r4 = " framePointer="
            r3.append(r4)     // Catch:{ all -> 0x00ca }
            int r4 = r7.framePointer     // Catch:{ all -> 0x00ca }
            r3.append(r4)     // Catch:{ all -> 0x00ca }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x00ca }
            android.util.Log.d(r0, r3)     // Catch:{ all -> 0x00ca }
        L_0x0039:
            r7.status = r2     // Catch:{ all -> 0x00ca }
        L_0x003b:
            int r0 = r7.status     // Catch:{ all -> 0x00ca }
            r3 = 0
            if (r0 == r2) goto L_0x00a8
            int r0 = r7.status     // Catch:{ all -> 0x00ca }
            r4 = 2
            if (r0 != r4) goto L_0x0046
            goto L_0x00a8
        L_0x0046:
            r0 = 0
            r7.status = r0     // Catch:{ all -> 0x00ca }
            com.bumptech.glide.gifdecoder.GifHeader r4 = r7.header     // Catch:{ all -> 0x00ca }
            java.util.List<com.bumptech.glide.gifdecoder.GifFrame> r4 = r4.frames     // Catch:{ all -> 0x00ca }
            int r5 = r7.framePointer     // Catch:{ all -> 0x00ca }
            java.lang.Object r4 = r4.get(r5)     // Catch:{ all -> 0x00ca }
            com.bumptech.glide.gifdecoder.GifFrame r4 = (com.bumptech.glide.gifdecoder.GifFrame) r4     // Catch:{ all -> 0x00ca }
            int r5 = r7.framePointer     // Catch:{ all -> 0x00ca }
            int r5 = r5 - r2
            if (r5 < 0) goto L_0x0065
            com.bumptech.glide.gifdecoder.GifHeader r6 = r7.header     // Catch:{ all -> 0x00ca }
            java.util.List<com.bumptech.glide.gifdecoder.GifFrame> r6 = r6.frames     // Catch:{ all -> 0x00ca }
            java.lang.Object r5 = r6.get(r5)     // Catch:{ all -> 0x00ca }
            com.bumptech.glide.gifdecoder.GifFrame r5 = (com.bumptech.glide.gifdecoder.GifFrame) r5     // Catch:{ all -> 0x00ca }
            goto L_0x0066
        L_0x0065:
            r5 = r3
        L_0x0066:
            int[] r6 = r4.lct     // Catch:{ all -> 0x00ca }
            if (r6 == 0) goto L_0x006d
            int[] r6 = r4.lct     // Catch:{ all -> 0x00ca }
            goto L_0x0071
        L_0x006d:
            com.bumptech.glide.gifdecoder.GifHeader r6 = r7.header     // Catch:{ all -> 0x00ca }
            int[] r6 = r6.gct     // Catch:{ all -> 0x00ca }
        L_0x0071:
            r7.act = r6     // Catch:{ all -> 0x00ca }
            int[] r6 = r7.act     // Catch:{ all -> 0x00ca }
            if (r6 != 0) goto L_0x008a
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00ca }
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00ca }
            if (r0 == 0) goto L_0x0086
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00ca }
            java.lang.String r1 = "No Valid Color Table"
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x00ca }
        L_0x0086:
            r7.status = r2     // Catch:{ all -> 0x00ca }
            monitor-exit(r7)
            return r3
        L_0x008a:
            boolean r1 = r4.transparency     // Catch:{ all -> 0x00ca }
            if (r1 == 0) goto L_0x00a2
            int[] r1 = r7.act     // Catch:{ all -> 0x00ca }
            int[] r2 = r7.pct     // Catch:{ all -> 0x00ca }
            int[] r3 = r7.act     // Catch:{ all -> 0x00ca }
            int r3 = r3.length     // Catch:{ all -> 0x00ca }
            java.lang.System.arraycopy(r1, r0, r2, r0, r3)     // Catch:{ all -> 0x00ca }
            int[] r1 = r7.pct     // Catch:{ all -> 0x00ca }
            r7.act = r1     // Catch:{ all -> 0x00ca }
            int[] r1 = r7.act     // Catch:{ all -> 0x00ca }
            int r2 = r4.transIndex     // Catch:{ all -> 0x00ca }
            r1[r2] = r0     // Catch:{ all -> 0x00ca }
        L_0x00a2:
            android.graphics.Bitmap r0 = r7.setPixels(r4, r5)     // Catch:{ all -> 0x00ca }
            monitor-exit(r7)
            return r0
        L_0x00a8:
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00ca }
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00ca }
            if (r0 == 0) goto L_0x00c8
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00ca }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ca }
            r1.<init>()     // Catch:{ all -> 0x00ca }
            java.lang.String r2 = "Unable to decode frame, status="
            r1.append(r2)     // Catch:{ all -> 0x00ca }
            int r2 = r7.status     // Catch:{ all -> 0x00ca }
            r1.append(r2)     // Catch:{ all -> 0x00ca }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00ca }
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x00ca }
        L_0x00c8:
            monitor-exit(r7)
            return r3
        L_0x00ca:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifdecoder.GifDecoder.getNextFrame():android.graphics.Bitmap");
    }

    public int read(InputStream inputStream, int i) {
        if (inputStream != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i > 0 ? i + 4096 : 16384);
                byte[] bArr = new byte[16384];
                while (true) {
                    int read = inputStream.read(bArr, 0, bArr.length);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byteArrayOutputStream.flush();
                read(byteArrayOutputStream.toByteArray());
            } catch (IOException e) {
                Log.w(TAG, "Error reading data from stream", e);
            }
        } else {
            this.status = 2;
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e2) {
                Log.w(TAG, "Error closing stream", e2);
            }
        }
        return this.status;
    }

    public void clear() {
        this.header = null;
        this.data = null;
        this.mainPixels = null;
        this.mainScratch = null;
        if (this.previousImage != null) {
            this.bitmapProvider.release(this.previousImage);
        }
        this.previousImage = null;
        this.rawData = null;
    }

    public void setData(GifHeader gifHeader, byte[] bArr) {
        this.header = gifHeader;
        this.data = bArr;
        this.status = 0;
        this.framePointer = -1;
        this.rawData = ByteBuffer.wrap(bArr);
        this.rawData.rewind();
        this.rawData.order(ByteOrder.LITTLE_ENDIAN);
        this.savePrevious = false;
        Iterator<GifFrame> it = gifHeader.frames.iterator();
        while (true) {
            if (it.hasNext()) {
                if (it.next().dispose == 3) {
                    this.savePrevious = true;
                    break;
                }
            } else {
                break;
            }
        }
        this.mainPixels = new byte[(gifHeader.width * gifHeader.height)];
        this.mainScratch = new int[(gifHeader.width * gifHeader.height)];
    }

    private GifHeaderParser getHeaderParser() {
        if (this.parser == null) {
            this.parser = new GifHeaderParser();
        }
        return this.parser;
    }

    public int read(byte[] bArr) {
        this.data = bArr;
        this.header = getHeaderParser().setData(bArr).parseHeader();
        if (bArr != null) {
            this.rawData = ByteBuffer.wrap(bArr);
            this.rawData.rewind();
            this.rawData.order(ByteOrder.LITTLE_ENDIAN);
            this.mainPixels = new byte[(this.header.width * this.header.height)];
            this.mainScratch = new int[(this.header.width * this.header.height)];
            this.savePrevious = false;
            Iterator<GifFrame> it = this.header.frames.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().dispose == 3) {
                        this.savePrevious = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return this.status;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002d, code lost:
        if (r13.header.bgIndex == r14.transIndex) goto L_0x002f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Bitmap setPixels(com.bumptech.glide.gifdecoder.GifFrame r14, com.bumptech.glide.gifdecoder.GifFrame r15) {
        /*
            r13 = this;
            com.bumptech.glide.gifdecoder.GifHeader r0 = r13.header
            int r0 = r0.width
            com.bumptech.glide.gifdecoder.GifHeader r1 = r13.header
            int r9 = r1.height
            int[] r10 = r13.mainScratch
            r11 = 0
            if (r15 != 0) goto L_0x0010
            java.util.Arrays.fill(r10, r11)
        L_0x0010:
            r12 = 2
            if (r15 == 0) goto L_0x0060
            int r1 = r15.dispose
            if (r1 <= 0) goto L_0x0060
            int r1 = r15.dispose
            if (r1 != r12) goto L_0x004b
            boolean r1 = r14.transparency
            if (r1 != 0) goto L_0x002f
            com.bumptech.glide.gifdecoder.GifHeader r1 = r13.header
            int r1 = r1.bgColor
            int[] r2 = r14.lct
            if (r2 == 0) goto L_0x0030
            com.bumptech.glide.gifdecoder.GifHeader r2 = r13.header
            int r2 = r2.bgIndex
            int r3 = r14.transIndex
            if (r2 != r3) goto L_0x0030
        L_0x002f:
            r1 = 0
        L_0x0030:
            int r2 = r15.iy
            int r2 = r2 * r0
            int r3 = r15.ix
            int r2 = r2 + r3
            int r3 = r15.ih
            int r3 = r3 * r0
            int r3 = r3 + r2
        L_0x003c:
            if (r2 >= r3) goto L_0x0060
            int r4 = r15.iw
            int r4 = r4 + r2
            r5 = r2
        L_0x0042:
            if (r5 >= r4) goto L_0x0049
            r10[r5] = r1
            int r5 = r5 + 1
            goto L_0x0042
        L_0x0049:
            int r2 = r2 + r0
            goto L_0x003c
        L_0x004b:
            int r15 = r15.dispose
            r1 = 3
            if (r15 != r1) goto L_0x0060
            android.graphics.Bitmap r15 = r13.previousImage
            if (r15 == 0) goto L_0x0060
            android.graphics.Bitmap r1 = r13.previousImage
            r3 = 0
            r5 = 0
            r6 = 0
            r2 = r10
            r4 = r0
            r7 = r0
            r8 = r9
            r1.getPixels(r2, r3, r4, r5, r6, r7, r8)
        L_0x0060:
            r13.decodeBitmapData(r14)
            r15 = 8
            r1 = 1
            r15 = 0
            r2 = 1
            r3 = 8
        L_0x006a:
            int r4 = r14.ih
            if (r11 >= r4) goto L_0x00c8
            boolean r4 = r14.interlace
            if (r4 == 0) goto L_0x0087
            int r4 = r14.ih
            r5 = 4
            if (r15 < r4) goto L_0x0084
            int r2 = r2 + 1
            switch(r2) {
                case 2: goto L_0x0083;
                case 3: goto L_0x0080;
                case 4: goto L_0x007d;
                default: goto L_0x007c;
            }
        L_0x007c:
            goto L_0x0084
        L_0x007d:
            r15 = 1
            r3 = 2
            goto L_0x0084
        L_0x0080:
            r15 = 2
            r3 = 4
            goto L_0x0084
        L_0x0083:
            r15 = 4
        L_0x0084:
            int r4 = r15 + r3
            goto L_0x0089
        L_0x0087:
            r4 = r15
            r15 = r11
        L_0x0089:
            int r5 = r14.iy
            int r15 = r15 + r5
            com.bumptech.glide.gifdecoder.GifHeader r5 = r13.header
            int r5 = r5.height
            if (r15 >= r5) goto L_0x00c4
            com.bumptech.glide.gifdecoder.GifHeader r5 = r13.header
            int r5 = r5.width
            int r15 = r15 * r5
            int r5 = r14.ix
            int r5 = r5 + r15
            int r6 = r14.iw
            int r6 = r6 + r5
            com.bumptech.glide.gifdecoder.GifHeader r7 = r13.header
            int r7 = r7.width
            int r7 = r7 + r15
            if (r7 >= r6) goto L_0x00aa
            com.bumptech.glide.gifdecoder.GifHeader r6 = r13.header
            int r6 = r6.width
            int r6 = r6 + r15
        L_0x00aa:
            int r15 = r14.iw
            int r15 = r15 * r11
        L_0x00ae:
            if (r5 >= r6) goto L_0x00c4
            byte[] r7 = r13.mainPixels
            int r8 = r15 + 1
            byte r15 = r7[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int[] r7 = r13.act
            r15 = r7[r15]
            if (r15 == 0) goto L_0x00c0
            r10[r5] = r15
        L_0x00c0:
            int r5 = r5 + 1
            r15 = r8
            goto L_0x00ae
        L_0x00c4:
            int r11 = r11 + 1
            r15 = r4
            goto L_0x006a
        L_0x00c8:
            boolean r15 = r13.savePrevious
            if (r15 == 0) goto L_0x00ea
            int r15 = r14.dispose
            if (r15 == 0) goto L_0x00d4
            int r14 = r14.dispose
            if (r14 != r1) goto L_0x00ea
        L_0x00d4:
            android.graphics.Bitmap r14 = r13.previousImage
            if (r14 != 0) goto L_0x00de
            android.graphics.Bitmap r14 = r13.getNextBitmap()
            r13.previousImage = r14
        L_0x00de:
            android.graphics.Bitmap r1 = r13.previousImage
            r3 = 0
            r5 = 0
            r6 = 0
            r2 = r10
            r4 = r0
            r7 = r0
            r8 = r9
            r1.setPixels(r2, r3, r4, r5, r6, r7, r8)
        L_0x00ea:
            android.graphics.Bitmap r14 = r13.getNextBitmap()
            r3 = 0
            r5 = 0
            r6 = 0
            r1 = r14
            r2 = r10
            r4 = r0
            r7 = r0
            r8 = r9
            r1.setPixels(r2, r3, r4, r5, r6, r7, r8)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifdecoder.GifDecoder.setPixels(com.bumptech.glide.gifdecoder.GifFrame, com.bumptech.glide.gifdecoder.GifFrame):android.graphics.Bitmap");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v7, resolved type: byte} */
    /* JADX WARNING: Incorrect type for immutable var: ssa=short, code=int, for r2v17, types: [short] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void decodeBitmapData(com.bumptech.glide.gifdecoder.GifFrame r28) {
        /*
            r27 = this;
            r0 = r27
            r1 = r28
            if (r1 == 0) goto L_0x000d
            java.nio.ByteBuffer r2 = r0.rawData
            int r3 = r1.bufferFrameStart
            r2.position(r3)
        L_0x000d:
            if (r1 != 0) goto L_0x001a
            com.bumptech.glide.gifdecoder.GifHeader r1 = r0.header
            int r1 = r1.width
            com.bumptech.glide.gifdecoder.GifHeader r2 = r0.header
            int r2 = r2.height
            int r1 = r1 * r2
            goto L_0x0020
        L_0x001a:
            int r2 = r1.iw
            int r1 = r1.ih
            int r1 = r1 * r2
        L_0x0020:
            byte[] r2 = r0.mainPixels
            if (r2 == 0) goto L_0x0029
            byte[] r2 = r0.mainPixels
            int r2 = r2.length
            if (r2 >= r1) goto L_0x002d
        L_0x0029:
            byte[] r2 = new byte[r1]
            r0.mainPixels = r2
        L_0x002d:
            short[] r2 = r0.prefix
            r3 = 4096(0x1000, float:5.74E-42)
            if (r2 != 0) goto L_0x0037
            short[] r2 = new short[r3]
            r0.prefix = r2
        L_0x0037:
            byte[] r2 = r0.suffix
            if (r2 != 0) goto L_0x003f
            byte[] r2 = new byte[r3]
            r0.suffix = r2
        L_0x003f:
            byte[] r2 = r0.pixelStack
            if (r2 != 0) goto L_0x0049
            r2 = 4097(0x1001, float:5.741E-42)
            byte[] r2 = new byte[r2]
            r0.pixelStack = r2
        L_0x0049:
            int r2 = r27.read()
            r4 = 1
            int r5 = r4 << r2
            int r6 = r5 + 1
            int r7 = r5 + 2
            int r2 = r2 + r4
            int r8 = r4 << r2
            int r8 = r8 - r4
            r9 = 0
            r10 = 0
        L_0x005a:
            if (r10 >= r5) goto L_0x0068
            short[] r11 = r0.prefix
            r11[r10] = r9
            byte[] r11 = r0.suffix
            byte r12 = (byte) r10
            r11[r10] = r12
            int r10 = r10 + 1
            goto L_0x005a
        L_0x0068:
            r10 = -1
            r21 = r2
            r19 = r7
            r20 = r8
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r22 = -1
        L_0x007c:
            if (r11 >= r1) goto L_0x0179
            r9 = 3
            if (r12 != 0) goto L_0x008c
            int r12 = r27.readBlock()
            if (r12 > 0) goto L_0x008b
            r0.status = r9
            goto L_0x0179
        L_0x008b:
            r15 = 0
        L_0x008c:
            byte[] r3 = r0.block
            byte r3 = r3[r15]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << r16
            int r14 = r14 + r3
            int r16 = r16 + 8
            int r15 = r15 + r4
            int r12 = r12 + r10
            r3 = r16
            r23 = r17
            r4 = r22
            r16 = r11
            r17 = r13
            r13 = r19
            r11 = r21
        L_0x00a7:
            if (r3 < r11) goto L_0x015d
            r10 = r14 & r20
            int r14 = r14 >> r11
            int r3 = r3 - r11
            if (r10 != r5) goto L_0x00b6
            r11 = r2
            r13 = r7
            r20 = r8
            r4 = -1
        L_0x00b4:
            r10 = -1
            goto L_0x00a7
        L_0x00b6:
            if (r10 <= r13) goto L_0x00bb
            r0.status = r9
            goto L_0x00bd
        L_0x00bb:
            if (r10 != r6) goto L_0x00d0
        L_0x00bd:
            r22 = r4
            r21 = r11
            r19 = r13
            r11 = r16
            r13 = r17
            r17 = r23
            r4 = 1
            r9 = 0
            r10 = -1
            r16 = r3
            goto L_0x0175
        L_0x00d0:
            r9 = -1
            if (r4 != r9) goto L_0x00e4
            byte[] r4 = r0.pixelStack
            int r19 = r18 + 1
            byte[] r9 = r0.suffix
            byte r9 = r9[r10]
            r4[r18] = r9
            r4 = r10
            r23 = r4
            r18 = r19
        L_0x00e2:
            r9 = 3
            goto L_0x00b4
        L_0x00e4:
            if (r10 < r13) goto L_0x00f5
            byte[] r9 = r0.pixelStack
            int r19 = r18 + 1
            r24 = r2
            r2 = r23
            byte r2 = (byte) r2
            r9[r18] = r2
            r2 = r4
            r18 = r19
            goto L_0x00f8
        L_0x00f5:
            r24 = r2
            r2 = r10
        L_0x00f8:
            if (r2 < r5) goto L_0x010f
            byte[] r9 = r0.pixelStack
            int r19 = r18 + 1
            r25 = r3
            byte[] r3 = r0.suffix
            byte r3 = r3[r2]
            r9[r18] = r3
            short[] r3 = r0.prefix
            short r2 = r3[r2]
            r18 = r19
            r3 = r25
            goto L_0x00f8
        L_0x010f:
            r25 = r3
            byte[] r3 = r0.suffix
            byte r2 = r3[r2]
            r2 = r2 & 255(0xff, float:3.57E-43)
            byte[] r3 = r0.pixelStack
            int r9 = r18 + 1
            r26 = r5
            byte r5 = (byte) r2
            r3[r18] = r5
            r3 = 4096(0x1000, float:5.74E-42)
            if (r13 >= r3) goto L_0x013e
            short[] r3 = r0.prefix
            short r4 = (short) r4
            r3[r13] = r4
            byte[] r3 = r0.suffix
            r3[r13] = r5
            int r13 = r13 + 1
            r3 = r13 & r20
            if (r3 != 0) goto L_0x013c
            r3 = 4096(0x1000, float:5.74E-42)
            if (r13 >= r3) goto L_0x013e
            int r11 = r11 + 1
            int r20 = r20 + r13
            goto L_0x013e
        L_0x013c:
            r3 = 4096(0x1000, float:5.74E-42)
        L_0x013e:
            r18 = r9
        L_0x0140:
            if (r18 <= 0) goto L_0x0153
            int r18 = r18 + -1
            byte[] r4 = r0.mainPixels
            int r5 = r17 + 1
            byte[] r9 = r0.pixelStack
            byte r9 = r9[r18]
            r4[r17] = r9
            int r16 = r16 + 1
            r17 = r5
            goto L_0x0140
        L_0x0153:
            r23 = r2
            r4 = r10
            r2 = r24
            r3 = r25
            r5 = r26
            goto L_0x00e2
        L_0x015d:
            r24 = r2
            r26 = r5
            r2 = r23
            r22 = r4
            r21 = r11
            r19 = r13
            r11 = r16
            r13 = r17
            r4 = 1
            r9 = 0
            r17 = r2
            r16 = r3
            r2 = r24
        L_0x0175:
            r3 = 4096(0x1000, float:5.74E-42)
            goto L_0x007c
        L_0x0179:
            if (r13 >= r1) goto L_0x0183
            byte[] r2 = r0.mainPixels
            r3 = 0
            r2[r13] = r3
            int r13 = r13 + 1
            goto L_0x0179
        L_0x0183:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifdecoder.GifDecoder.decodeBitmapData(com.bumptech.glide.gifdecoder.GifFrame):void");
    }

    private int read() {
        try {
            return this.rawData.get() & Constants.UNKNOWN;
        } catch (Exception unused) {
            this.status = 1;
            return 0;
        }
    }

    private int readBlock() {
        int read = read();
        int i = 0;
        if (read > 0) {
            while (i < read) {
                int i2 = read - i;
                try {
                    this.rawData.get(this.block, i, i2);
                    i += i2;
                } catch (Exception e) {
                    Log.w(TAG, "Error Reading Block", e);
                    this.status = 1;
                }
            }
        }
        return i;
    }

    private Bitmap getNextBitmap() {
        Bitmap obtain = this.bitmapProvider.obtain(this.header.width, this.header.height, BITMAP_CONFIG);
        if (obtain == null) {
            obtain = Bitmap.createBitmap(this.header.width, this.header.height, BITMAP_CONFIG);
        }
        setAlpha(obtain);
        return obtain;
    }

    @TargetApi(12)
    private static void setAlpha(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 12) {
            bitmap.setHasAlpha(true);
        }
    }
}
