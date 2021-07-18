package com.newandromo.dev849565.app936843;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class EntityFixingReader extends FilterReader {
    private static final String TAG = "EntityFixingReader";
    private CharRingBuffer lookback = new CharRingBuffer(32);
    private State mState = State.NORMAL;
    private CharRingBuffer pending = new CharRingBuffer(32);

    private enum State {
        NORMAL {
            public boolean inAmp() {
                return false;
            }

            public boolean needsMoreData() {
                return false;
            }

            public State process(char c, CharRingBuffer charRingBuffer, CharRingBuffer charRingBuffer2) {
                State state;
                if (c == '[') {
                    if (charRingBuffer.endsWith(State.cdataBytes)) {
                        state = IN_CDATA;
                        charRingBuffer.add(c);
                        return state;
                    }
                } else if (c == '-') {
                    if (charRingBuffer.endsWith(State.commentBytes)) {
                        state = IN_COMMENT;
                        charRingBuffer.add(c);
                        return state;
                    }
                } else if (c == '?') {
                    charRingBuffer.peek(charRingBuffer.size() - 1);
                    if (charRingBuffer.peekLast() == '<') {
                        state = IN_PROCESSING_INSTRUCTION;
                        charRingBuffer.add(c);
                        return state;
                    }
                } else if (c == '&') {
                    State state2 = IN_AMP;
                    charRingBuffer2.add(c);
                    state = state2;
                    charRingBuffer.add(c);
                    return state;
                }
                state = this;
                charRingBuffer.add(c);
                return state;
            }
        },
        IN_PROCESSING_INSTRUCTION {
            public boolean inAmp() {
                return false;
            }

            public boolean needsMoreData() {
                return false;
            }

            public State process(char c, CharRingBuffer charRingBuffer, CharRingBuffer charRingBuffer2) {
                State state = (c == '>' && charRingBuffer.peekLast() == '?') ? NORMAL : this;
                charRingBuffer.add(c);
                return state;
            }
        },
        IN_COMMENT {
            public boolean inAmp() {
                return false;
            }

            public boolean needsMoreData() {
                return false;
            }

            public State process(char c, CharRingBuffer charRingBuffer, CharRingBuffer charRingBuffer2) {
                State state = (c != '>' || !charRingBuffer.endsWith((CharSequence) "--")) ? this : NORMAL;
                charRingBuffer.add(c);
                return state;
            }
        },
        IN_CDATA {
            public boolean inAmp() {
                return false;
            }

            public boolean needsMoreData() {
                return false;
            }

            public State process(char c, CharRingBuffer charRingBuffer, CharRingBuffer charRingBuffer2) {
                State state = (c != '>' || !charRingBuffer.endsWith((CharSequence) "]]")) ? this : NORMAL;
                charRingBuffer.add(c);
                return state;
            }
        },
        IN_AMP {
            public boolean inAmp() {
                return true;
            }

            public boolean needsMoreData() {
                return true;
            }

            public State process(char c, CharRingBuffer charRingBuffer, CharRingBuffer charRingBuffer2) {
                State state;
                if (charRingBuffer2.capacityLeft() <= 5) {
                    State.expandAmp(charRingBuffer2);
                    state = FLUSHING;
                } else if (c == '&') {
                    State.expandAmp(charRingBuffer2);
                    state = FLUSHING_WITHIN_AMP;
                } else {
                    if (!Character.isLetter(c)) {
                        if (c == '#') {
                            if (charRingBuffer2.size() == 1) {
                                state = IN_NUMERIC_AMP;
                            } else {
                                State.expandAmp(charRingBuffer2);
                                state = FLUSHING;
                            }
                        } else if (c != ';') {
                            State.expandAmp(charRingBuffer2);
                            state = FLUSHING;
                        } else if (c == ';') {
                            state = FLUSHING;
                        }
                    }
                    state = this;
                }
                charRingBuffer2.add(c);
                charRingBuffer.add(c);
                return state;
            }
        },
        IN_HEXADECIMAL_AMP {
            public boolean inAmp() {
                return true;
            }

            public boolean needsMoreData() {
                return true;
            }

            public State process(char c, CharRingBuffer charRingBuffer, CharRingBuffer charRingBuffer2) {
                State state;
                if (charRingBuffer2.capacityLeft() <= 5) {
                    State.expandAmp(charRingBuffer2);
                    state = FLUSHING;
                } else if (c == '&') {
                    State.expandAmp(charRingBuffer2);
                    state = FLUSHING_WITHIN_AMP;
                } else {
                    if (!(Character.isDigit(c) || c == 'a' || c == 'A' || c == 'b' || c == 'B' || c == 'c' || c == 'C' || c == 'd' || c == 'D' || c == 'e' || c == 'E' || c == 'f' || c == 'F')) {
                        if (c != ';') {
                            State.expandAmp(charRingBuffer2);
                            state = FLUSHING;
                        } else if (c == ';') {
                            state = FLUSHING;
                        }
                    }
                    state = this;
                }
                charRingBuffer2.add(c);
                charRingBuffer.add(c);
                return state;
            }
        },
        IN_NUMERIC_AMP {
            public boolean inAmp() {
                return true;
            }

            public boolean needsMoreData() {
                return true;
            }

            public State process(char c, CharRingBuffer charRingBuffer, CharRingBuffer charRingBuffer2) {
                State state;
                if (charRingBuffer2.capacityLeft() <= 5) {
                    State.expandAmp(charRingBuffer2);
                    state = FLUSHING;
                } else if (c == '&') {
                    State.expandAmp(charRingBuffer2);
                    state = FLUSHING_WITHIN_AMP;
                } else {
                    if (!Character.isDigit(c)) {
                        if (c == 'x') {
                            if (charRingBuffer2.size() == 2) {
                                state = IN_HEXADECIMAL_AMP;
                            } else {
                                State.expandAmp(charRingBuffer2);
                                state = FLUSHING;
                            }
                        } else if (c != ';') {
                            State.expandAmp(charRingBuffer2);
                            state = FLUSHING;
                        } else if (c == ';') {
                            state = FLUSHING;
                        }
                    }
                    state = this;
                }
                charRingBuffer2.add(c);
                charRingBuffer.add(c);
                return state;
            }
        },
        FLUSHING_WITHIN_AMP {
            public boolean inAmp() {
                return true;
            }

            public boolean needsMoreData() {
                return false;
            }

            public State process(char c, CharRingBuffer charRingBuffer, CharRingBuffer charRingBuffer2) {
                return this;
            }
        },
        FLUSHING {
            public boolean inAmp() {
                return false;
            }

            public boolean needsMoreData() {
                return false;
            }

            public State process(char c, CharRingBuffer charRingBuffer, CharRingBuffer charRingBuffer2) {
                return this;
            }
        };
        
        /* access modifiers changed from: private */
        public static final char[] cdataBytes = null;
        /* access modifiers changed from: private */
        public static final char[] commentBytes = null;

        /* access modifiers changed from: package-private */
        public abstract boolean inAmp();

        /* access modifiers changed from: package-private */
        public abstract boolean needsMoreData();

        /* access modifiers changed from: package-private */
        public abstract State process(char c, CharRingBuffer charRingBuffer, CharRingBuffer charRingBuffer2);

        static {
            cdataBytes = "<![CDATA".toCharArray();
            commentBytes = "<!-".toCharArray();
        }

        /* access modifiers changed from: private */
        public static void expandAmp(CharRingBuffer charRingBuffer) {
            if (charRingBuffer.peekFirst() == '&') {
                charRingBuffer.removeFirst();
            }
            charRingBuffer.addFirst(';');
            charRingBuffer.addFirst('p');
            charRingBuffer.addFirst('m');
            charRingBuffer.addFirst('a');
            charRingBuffer.addFirst('&');
        }
    }

    public EntityFixingReader(Reader reader) {
        super(reader);
    }

    public int read() throws IOException {
        while (true) {
            if (this.mState == State.FLUSHING_WITHIN_AMP) {
                if (this.pending.size() == 1) {
                    if (this.pending.peekFirst() != '&') {
                        return this.pending.removeFirst();
                    }
                    this.mState = State.IN_AMP;
                } else if (this.pending.size() > 0) {
                    return this.pending.removeFirst();
                } else {
                    this.mState = State.NORMAL;
                }
            } else if (this.mState == State.FLUSHING) {
                if (this.pending.size() > 0) {
                    return this.pending.removeFirst();
                }
                this.mState = State.NORMAL;
            }
            int read = this.in.read();
            if (read != -1) {
                this.mState = this.mState.process((char) read, this.lookback, this.pending);
            } else {
                if (this.mState.inAmp()) {
                    State.expandAmp(this.pending);
                }
                this.mState = this.pending.size() > 0 ? State.FLUSHING : State.NORMAL;
            }
            if (this.mState != State.FLUSHING && this.mState != State.FLUSHING_WITHIN_AMP && !this.mState.needsMoreData()) {
                return read;
            }
        }
    }

    public int read(char[] cArr, int i, int i2) throws IOException {
        int read;
        int i3 = i2 + i;
        int i4 = i;
        while (i4 < i3 && (read = read()) != -1) {
            cArr[i4] = (char) read;
            i4++;
        }
        int i5 = i4 - i;
        if (i5 > 0) {
            return i5;
        }
        return -1;
    }
}
