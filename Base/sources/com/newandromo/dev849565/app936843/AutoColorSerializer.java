package com.newandromo.dev849565.app936843;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class AutoColorSerializer implements Serializable {
    private AutoColor autoColor;

    public AutoColorSerializer(AutoColor autoColor2) {
        this.autoColor = autoColor2;
    }

    public AutoColor getAutoColor() {
        return this.autoColor;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.autoColor.getBackgroundColor());
        objectOutputStream.writeInt(this.autoColor.getTitleTextColor());
        objectOutputStream.writeInt(this.autoColor.getBodyTextColor());
        objectOutputStream.writeInt(this.autoColor.getPrimaryTextColor());
        objectOutputStream.writeInt(this.autoColor.getSecondaryTextColor());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        this.autoColor = new AutoColor(objectInputStream.readInt(), objectInputStream.readInt(), objectInputStream.readInt(), objectInputStream.readInt(), objectInputStream.readInt());
    }
}
