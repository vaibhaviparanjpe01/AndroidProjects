package com.newandromo.dev849565.app936843;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class CharRingBuffer {
    private static final int MIN_CAPACITY = 4;
    private static final String TAG = "CharRingBuffer";
    private final char[] buffer;
    private int count;
    private int head;
    private int mask;

    public CharRingBuffer(int i) {
        int i2 = 4;
        if (i >= 4) {
            int i3 = i - 1;
            int i4 = i3 | (i3 >>> 1);
            int i5 = i4 | (i4 >>> 2);
            int i6 = i5 | (i5 >>> 4);
            int i7 = i6 | (i6 >>> 8);
            i2 = (i7 | (i7 >>> 16)) + 1;
        }
        this.buffer = new char[i2];
        this.mask = this.buffer.length - 1;
    }

    public CharRingBuffer(CharRingBuffer charRingBuffer) {
        this.buffer = new char[charRingBuffer.buffer.length];
        this.mask = this.buffer.length - 1;
        this.head = 0;
        this.count = charRingBuffer.count;
        charRingBuffer.copyElements(this.buffer);
    }

    public char peek(int i) {
        if (i < 0 || i >= size()) {
            return 0;
        }
        return this.buffer[this.mask & (this.head + i)];
    }

    public char get(int i) {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return this.buffer[this.mask & (this.head + i)];
    }

    public char set(int i, char c) {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException();
        }
        int i2 = this.mask & (this.head + i);
        char c2 = this.buffer[i2];
        this.buffer[i2] = c;
        return c2;
    }

    public int capacityLeft() {
        return this.buffer.length - this.count;
    }

    public boolean endsWith(char[] cArr) {
        if (this.count < cArr.length) {
            return false;
        }
        int length = cArr.length;
        int length2 = this.head + (this.count - cArr.length);
        for (int i = 0; i < length; i++) {
            if (this.buffer[(length2 + i) & this.mask] != cArr[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean endsWith(CharSequence charSequence) {
        if (this.count < charSequence.length()) {
            return false;
        }
        int length = charSequence.length();
        int length2 = this.head + (this.count - charSequence.length());
        for (int i = 0; i < length; i++) {
            if (this.buffer[(length2 + i) & this.mask] != charSequence.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public void clear() {
        this.count = 0;
        this.head = 0;
    }

    public boolean isFull() {
        return this.count == this.buffer.length;
    }

    public void dropFirst() {
        if (this.count != 0) {
            this.head = (this.head + 1) & this.mask;
            this.count--;
            return;
        }
        throw new NoSuchElementException();
    }

    public void dropLast() {
        if (this.count != 0) {
            this.count--;
            return;
        }
        throw new NoSuchElementException();
    }

    public void addFirst(char c) {
        char[] cArr = this.buffer;
        int i = (this.head - 1) & this.mask;
        this.head = i;
        cArr[i] = c;
        if (this.count != this.buffer.length) {
            this.count++;
        }
    }

    public void addLast(char c) {
        this.buffer[(this.head + this.count) & this.mask] = c;
        if (this.count == this.buffer.length) {
            this.head = (this.head + 1) & this.mask;
        } else {
            this.count++;
        }
    }

    private char[] copyElements(char[] cArr) {
        if (this.count > 0) {
            int i = (this.head + this.count) & this.mask;
            if (this.head < i) {
                System.arraycopy(this.buffer, this.head, cArr, 0, size());
            } else if (this.head == 0) {
                System.arraycopy(this.buffer, 0, cArr, 0, this.count);
            } else if (this.head >= i) {
                int length = this.buffer.length - this.head;
                System.arraycopy(this.buffer, this.head, cArr, 0, length);
                System.arraycopy(this.buffer, 0, cArr, length, i);
            }
        }
        return cArr;
    }

    public boolean offerFirst(char c) {
        addFirst(c);
        return true;
    }

    public boolean offerLast(char c) {
        addLast(c);
        return true;
    }

    public char removeFirst() {
        if (this.count != 0) {
            char c = this.buffer[this.head];
            this.head = (this.head + 1) & this.mask;
            this.count--;
            return c;
        }
        throw new NoSuchElementException();
    }

    public char removeLast() {
        if (this.count != 0) {
            this.count--;
            return this.buffer[(this.head + this.count) & this.mask];
        }
        throw new NoSuchElementException();
    }

    public char pollFirst() {
        if (this.count == 0) {
            return 0;
        }
        char c = this.buffer[this.head];
        this.head = (this.head + 1) & this.mask;
        this.count--;
        return c;
    }

    public char pollLast() {
        if (this.count == 0) {
            return 0;
        }
        this.count--;
        return this.buffer[(this.head + this.count) & this.mask];
    }

    public char getFirst() {
        if (this.count != 0) {
            return this.buffer[this.head];
        }
        throw new NoSuchElementException();
    }

    public char getLast() {
        if (this.count != 0) {
            return this.buffer[((this.head + this.count) - 1) & this.mask];
        }
        throw new NoSuchElementException();
    }

    public char peekFirst() {
        if (this.count == 0) {
            return 0;
        }
        return this.buffer[this.head];
    }

    public char peekLast() {
        if (this.count == 0) {
            return 0;
        }
        return this.buffer[((this.head + this.count) - 1) & this.mask];
    }

    public boolean removeFirstOccurrence(char c) {
        int i = this.head;
        int i2 = (this.head + this.count) & this.mask;
        while (i != i2) {
            if (this.buffer[i] == c) {
                delete(i);
                return true;
            }
            i = (i + 1) & this.mask;
        }
        return false;
    }

    public boolean removeLastOccurrence(char c) {
        for (int i = this.count - 1; i >= 0; i--) {
            int i2 = (this.head + i) & this.mask;
            if (this.buffer[i2] == c) {
                delete(i2);
                return true;
            }
        }
        return false;
    }

    public boolean add(char c) {
        addLast(c);
        return true;
    }

    public boolean offer(char c) {
        return offerLast(c);
    }

    public char remove() {
        return removeFirst();
    }

    public char poll() {
        return pollFirst();
    }

    public char element() {
        return getFirst();
    }

    public char peek() {
        return peekFirst();
    }

    public void push(char c) {
        addFirst(c);
    }

    public char pop() {
        return removeFirst();
    }

    public char removeAt(int i) {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException();
        }
        int i2 = this.mask & (this.head + i);
        char c = this.buffer[i2];
        delete(i2);
        return c;
    }

    private boolean delete(int i) {
        int i2 = (this.head + this.count) & this.mask;
        if (i == this.head) {
            this.head = (this.head + 1) & this.mask;
            this.count--;
            return false;
        } else if (i == i2) {
            this.count--;
            return true;
        } else {
            int i3 = this.head;
            int i4 = (i - i3) & this.mask;
            int i5 = (i2 - i) & this.mask;
            if (i4 > this.count) {
                throw new ConcurrentModificationException();
            } else if (i4 < i5) {
                if (i3 <= i) {
                    System.arraycopy(this.buffer, i3, this.buffer, i3 + 1, i4);
                } else {
                    System.arraycopy(this.buffer, 0, this.buffer, 1, i);
                    this.buffer[0] = this.buffer[this.mask];
                    System.arraycopy(this.buffer, i3, this.buffer, i3 + 1, this.mask - i3);
                }
                this.head = this.mask & (i3 + 1);
                this.count--;
                return false;
            } else {
                if (i < i2) {
                    System.arraycopy(this.buffer, i + 1, this.buffer, i, i5);
                } else {
                    System.arraycopy(this.buffer, i + 1, this.buffer, i, this.mask - i);
                    this.buffer[this.mask] = this.buffer[0];
                    System.arraycopy(this.buffer, 1, this.buffer, 0, i2);
                }
                this.count--;
                return true;
            }
        }
    }

    public int size() {
        return this.count;
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    public boolean contains(char c) {
        for (int i = 0; i < this.count; i++) {
            if (c == this.buffer[(this.head + i) & this.mask]) {
                return true;
            }
        }
        return false;
    }

    public char[] toArray() {
        return copyElements(new char[size()]);
    }

    public String toString() {
        return new String(toArray());
    }
}
