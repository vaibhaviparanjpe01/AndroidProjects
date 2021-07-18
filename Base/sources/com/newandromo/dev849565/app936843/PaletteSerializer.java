package com.newandromo.dev849565.app936843;

import android.support.v7.graphics.Palette;
import android.support.v7.graphics.Target;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PaletteSerializer implements Serializable {
    private static final Map<String, Target> SPECIAL_TARGETS = new HashMap();
    private Palette palette;

    public PaletteSerializer(Palette palette2) {
        this.palette = palette2;
    }

    public Palette getPalette() {
        return this.palette;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        List<Palette.Swatch> swatches = this.palette.getSwatches();
        objectOutputStream.writeInt(swatches.size());
        for (Palette.Swatch next : swatches) {
            objectOutputStream.writeInt(next.getRgb());
            objectOutputStream.writeInt(next.getPopulation());
        }
        List<Target> targets = this.palette.getTargets();
        objectOutputStream.writeInt(targets.size());
        for (Target next2 : targets) {
            objectOutputStream.writeBoolean(next2.isExclusive());
            objectOutputStream.writeFloat(next2.getPopulationWeight());
            objectOutputStream.writeFloat(next2.getLightnessWeight());
            objectOutputStream.writeFloat(next2.getMinimumLightness());
            objectOutputStream.writeFloat(next2.getTargetLightness());
            objectOutputStream.writeFloat(next2.getMaximumLightness());
            objectOutputStream.writeFloat(next2.getSaturationWeight());
            objectOutputStream.writeFloat(next2.getMinimumSaturation());
            objectOutputStream.writeFloat(next2.getTargetSaturation());
            objectOutputStream.writeFloat(next2.getMaximumSaturation());
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        int readInt = objectInputStream.readInt();
        ArrayList arrayList = new ArrayList(readInt);
        for (int i = 0; i < readInt; i++) {
            arrayList.add(new Palette.Swatch(objectInputStream.readInt(), objectInputStream.readInt()));
        }
        Palette.Builder builder = new Palette.Builder((List<Palette.Swatch>) arrayList);
        int readInt2 = objectInputStream.readInt();
        for (int i2 = 0; i2 < readInt2; i2++) {
            builder.addTarget(resolveSpecialTarget(new Target.Builder().setExclusive(objectInputStream.readBoolean()).setPopulationWeight(objectInputStream.readFloat()).setLightnessWeight(objectInputStream.readFloat()).setMinimumLightness(objectInputStream.readFloat()).setTargetLightness(objectInputStream.readFloat()).setMaximumLightness(objectInputStream.readFloat()).setSaturationWeight(objectInputStream.readFloat()).setMinimumSaturation(objectInputStream.readFloat()).setTargetSaturation(objectInputStream.readFloat()).setMaximumSaturation(objectInputStream.readFloat()).build()));
        }
        this.palette = builder.generate();
    }

    static {
        SPECIAL_TARGETS.put(key(Target.VIBRANT), Target.VIBRANT);
        SPECIAL_TARGETS.put(key(Target.LIGHT_VIBRANT), Target.LIGHT_VIBRANT);
        SPECIAL_TARGETS.put(key(Target.DARK_VIBRANT), Target.DARK_VIBRANT);
        SPECIAL_TARGETS.put(key(Target.MUTED), Target.MUTED);
        SPECIAL_TARGETS.put(key(Target.DARK_MUTED), Target.DARK_MUTED);
        SPECIAL_TARGETS.put(key(Target.LIGHT_MUTED), Target.LIGHT_MUTED);
        SPECIAL_TARGETS.put(key(PaletteUtils.DOMINANT), PaletteUtils.DOMINANT);
        SPECIAL_TARGETS.put(key(PaletteUtils.DOMINANT_DARK), PaletteUtils.DOMINANT_DARK);
        SPECIAL_TARGETS.put(key(PaletteUtils.DOMINANT_LIGHT), PaletteUtils.DOMINANT_LIGHT);
        SPECIAL_TARGETS.put(key(PaletteUtils.DOMINANT_VIBRANT), PaletteUtils.DOMINANT_VIBRANT);
        SPECIAL_TARGETS.put(key(PaletteUtils.DOMINANT_MUTED), PaletteUtils.DOMINANT_MUTED);
        SPECIAL_TARGETS.put(key(PaletteUtils.DARK), PaletteUtils.DARK);
        SPECIAL_TARGETS.put(key(PaletteUtils.LIGHT), PaletteUtils.LIGHT);
        SPECIAL_TARGETS.put(key(PaletteUtils.NEUTRAL), PaletteUtils.NEUTRAL);
    }

    private Target resolveSpecialTarget(Target target) {
        Target target2 = SPECIAL_TARGETS.get(key(target));
        return target2 != null ? target2 : target;
    }

    private static String key(Target target) {
        return target.isExclusive() + "_" + target.getPopulationWeight() + "_" + target.getLightnessWeight() + "_" + target.getMinimumLightness() + "_" + target.getTargetLightness() + "_" + target.getMaximumLightness() + "_" + target.getSaturationWeight() + "_" + target.getMinimumSaturation() + "_" + target.getTargetSaturation() + "_" + target.getMaximumSaturation();
    }

    public static void dump(Palette palette2) {
        for (int i = 0; i < palette2.getSwatches().size(); i++) {
        }
        List<Target> targets = palette2.getTargets();
        for (int i2 = 0; i2 < targets.size(); i2++) {
            Target target = targets.get(i2);
        }
    }
}
