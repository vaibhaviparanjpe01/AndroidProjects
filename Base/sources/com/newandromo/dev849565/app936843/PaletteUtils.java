package com.newandromo.dev849565.app936843;

import android.support.annotation.NonNull;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.os.EnvironmentCompat;
import android.support.v4.view.InputDeviceCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.graphics.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PaletteUtils {
    private static final int ANY_COLOR = 0;
    public static final Target DARK = new Target.Builder().setMinimumLightness(0.0f).setTargetLightness(0.26f).setMaximumLightness(0.5f).setMinimumSaturation(0.1f).setTargetSaturation(0.6f).setMaximumSaturation(1.0f).setPopulationWeight(0.18f).setSaturationWeight(0.22f).setLightnessWeight(0.6f).setExclusive(false).build();
    private static final boolean DEBUG = true;
    public static final Target DOMINANT = new Target.Builder().setPopulationWeight(1.0f).setSaturationWeight(0.0f).setLightnessWeight(0.0f).setExclusive(false).build();
    public static final Target DOMINANT_DARK = new Target.Builder().setMinimumLightness(0.0f).setTargetLightness(0.26f).setMaximumLightness(0.45f).setMinimumSaturation(0.3f).setTargetSaturation(0.7f).setMaximumSaturation(1.0f).setPopulationWeight(0.45f).setSaturationWeight(0.15f).setLightnessWeight(0.4f).setExclusive(false).build();
    public static final Target DOMINANT_LIGHT = new Target.Builder().setMinimumLightness(0.5f).setTargetLightness(0.74f).setMaximumLightness(1.0f).setMinimumSaturation(0.3f).setTargetSaturation(0.7f).setMaximumSaturation(1.0f).setPopulationWeight(0.45f).setSaturationWeight(0.15f).setLightnessWeight(0.4f).setExclusive(false).build();
    public static final Target DOMINANT_MUTED = new Target.Builder().setMinimumLightness(0.3f).setTargetLightness(0.5f).setMaximumLightness(0.7f).setMinimumSaturation(0.0f).setTargetSaturation(0.3f).setMaximumSaturation(0.4f).setPopulationWeight(0.4f).setSaturationWeight(0.4f).setLightnessWeight(0.2f).setExclusive(false).build();
    public static final Target DOMINANT_VIBRANT = new Target.Builder().setMinimumLightness(0.3f).setTargetLightness(0.5f).setMaximumLightness(0.7f).setMinimumSaturation(0.35f).setTargetSaturation(1.0f).setMaximumSaturation(1.0f).setPopulationWeight(0.4f).setSaturationWeight(0.4f).setLightnessWeight(0.2f).setExclusive(false).build();
    private static final int ENSURE_DARK = 1;
    private static final int ENSURE_LIGHT = -1;
    public static final Palette.Filter FILTER = new Palette.Filter() {
        public boolean isAllowed(int i, float[] fArr) {
            return isNearRedILine(fArr) ^ PaletteUtils.DEBUG;
        }

        private boolean isNearRedILine(float[] fArr) {
            if (fArr[0] < 10.0f || fArr[0] > 37.0f || fArr[1] > 0.82f) {
                return false;
            }
            return PaletteUtils.DEBUG;
        }
    };
    public static final Target LIGHT = new Target.Builder().setMinimumLightness(0.5f).setTargetLightness(0.74f).setMaximumLightness(1.0f).setMinimumSaturation(0.1f).setTargetSaturation(0.7f).setMaximumSaturation(1.0f).setPopulationWeight(0.18f).setSaturationWeight(0.22f).setLightnessWeight(0.6f).setExclusive(false).build();
    public static final Target NEUTRAL = new Target.Builder().setMinimumLightness(0.2f).setTargetLightness(0.5f).setMaximumLightness(0.8f).setMinimumSaturation(0.1f).setTargetSaturation(0.6f).setMaximumSaturation(1.0f).setPopulationWeight(0.18f).setSaturationWeight(0.22f).setLightnessWeight(0.6f).setExclusive(false).build();
    public static final String SWATCH_RULES_ANY_COLOR = "neutral, vibrant, dark vibrant, dark, muted, dark muted, light, light muted, light vibrant, if(0.7-1.0:dark dominant), if(0.0-0.2:light dominant), dominant";
    public static final String SWATCH_RULES_AUTOMATIC = "if(0.7-1.0,dark:dark, dark vibrant, neutral, dark dominant, dark muted),if(0.0-0.1,light:neutral, light, light muted, light vibrant, light dominant),neutral, vibrant, dark vibrant, dark, muted, dominant";
    public static final String SWATCH_RULES_DOMINANT = "if(0.7-1.0:dark dominant),if(0.0-0.1:light dominant),dominant";
    private static final String TAG = "PaletteUtils";
    static boolean bMaterial = false;
    private static double[] materialLabAccentColors;
    private static double[] materialLabColors;
    private static double[] materialLabPrimaryColors;
    static int n = 0;

    private static void logSwatch(Palette.Swatch swatch, String str) {
    }

    public static void logSwatches(Palette palette) {
        if (!palette.getSwatches().isEmpty()) {
            palette.getVibrantSwatch();
            palette.getDarkVibrantSwatch();
            palette.getLightVibrantSwatch();
            palette.getMutedSwatch();
            palette.getDarkMutedSwatch();
            palette.getLightMutedSwatch();
            getDominantSwatch(palette);
            palette.getSwatchForTarget(DOMINANT);
            palette.getSwatchForTarget(DOMINANT_DARK);
            palette.getSwatchForTarget(DOMINANT_LIGHT);
            palette.getSwatchForTarget(DOMINANT_VIBRANT);
            palette.getSwatchForTarget(DOMINANT_MUTED);
            palette.getSwatchForTarget(DARK);
            palette.getSwatchForTarget(NEUTRAL);
            palette.getSwatchForTarget(LIGHT);
        }
        ArrayList<Palette.Swatch> arrayList = new ArrayList<>(palette.getSwatches());
        Collections.sort(arrayList, new Comparator<Palette.Swatch>() {
            public int compare(Palette.Swatch swatch, Palette.Swatch swatch2) {
                return Integer.compare(swatch2.getPopulation(), swatch.getPopulation());
            }
        });
        for (Palette.Swatch swatch : arrayList) {
        }
        isPaletteDark(palette);
        isPaletteDark_Unweighted(palette);
        getPaletteDarkness(palette);
        getPaletteDarknessByPopulation(palette);
    }

    public static Palette.Swatch getDominantSwatch(Palette palette) {
        if (palette.getSwatches().isEmpty()) {
            return null;
        }
        return (Palette.Swatch) Collections.max(palette.getSwatches(), new Comparator<Palette.Swatch>() {
            public int compare(Palette.Swatch swatch, Palette.Swatch swatch2) {
                return Integer.compare(swatch.getPopulation(), swatch2.getPopulation());
            }
        });
    }

    public static Palette.Swatch getDominantDarkSwatch(Palette palette) {
        return palette.getSwatchForTarget(DOMINANT_DARK);
    }

    public static boolean isPaletteDark_Unweighted(Palette palette) {
        int i = 0;
        int i2 = 0;
        for (Palette.Swatch rgb : palette.getSwatches()) {
            if (ColorUtils.isDark(rgb.getRgb())) {
                i++;
            } else {
                i2++;
            }
        }
        if (i >= i2) {
            return DEBUG;
        }
        return false;
    }

    public static boolean isPaletteDark(Palette palette) {
        int i = 0;
        int i2 = 0;
        for (Palette.Swatch next : palette.getSwatches()) {
            if (ColorUtils.isDark(next.getRgb())) {
                i += next.getPopulation();
            } else {
                i2 += next.getPopulation();
            }
        }
        if (i >= i2) {
            return DEBUG;
        }
        return false;
    }

    public static boolean haveSameSwatches(Palette palette, Palette palette2) {
        if (palette != null && palette2 != null) {
            return CollectionUtils.areEqual(palette.getSwatches(), palette2.getSwatches());
        }
        if (palette == palette2) {
            return DEBUG;
        }
        return false;
    }

    public static boolean haveSameSwatchesInAnyOrder(Palette palette, Palette palette2) {
        if (palette != null && palette2 != null) {
            return haveSameSwatchColorsInAnyOrder(palette.getSwatches(), palette2.getSwatches());
        }
        if (palette == palette2) {
            return DEBUG;
        }
        return false;
    }

    public static boolean haveSameSwatchColors(Palette palette, Palette palette2) {
        if (palette != null && palette2 != null) {
            return haveSameSwatchColors(palette.getSwatches(), palette2.getSwatches());
        }
        if (palette == palette2) {
            return DEBUG;
        }
        return false;
    }

    public static boolean haveSameSwatchColors(List<Palette.Swatch> list, List<Palette.Swatch> list2) {
        if (list != null && list2 != null) {
            return CollectionUtils.areEqual(list, list2, new Comparator<Palette.Swatch>() {
                public int compare(Palette.Swatch swatch, Palette.Swatch swatch2) {
                    return Integer.compare(swatch.getRgb(), swatch2.getRgb());
                }
            });
        }
        if (list == list2) {
            return DEBUG;
        }
        return false;
    }

    public static boolean haveSameSwatchColorsInAnyOrder(List<Palette.Swatch> list, List<Palette.Swatch> list2) {
        if (list != null && list2 != null) {
            return CollectionUtils.areEqualIgnoringOrder(list, list2, new Comparator<Palette.Swatch>() {
                public int compare(Palette.Swatch swatch, Palette.Swatch swatch2) {
                    return Integer.compare(swatch.getRgb(), swatch2.getRgb());
                }
            });
        }
        if (list == list2) {
            return DEBUG;
        }
        return false;
    }

    public static float getPaletteDarknessBySwatchCount(Palette palette) {
        int i = 0;
        int i2 = 0;
        for (Palette.Swatch rgb : palette.getSwatches()) {
            if (ColorUtils.isDark(rgb.getRgb())) {
                i++;
            } else {
                i2++;
            }
        }
        int i3 = i2 + i;
        if (i3 > 0) {
            return ((float) i) / ((float) i3);
        }
        return 0.0f;
    }

    public static float getPaletteDarknessByPopulation(Palette palette) {
        if (palette == null) {
            return -1.0f;
        }
        int i = 0;
        int i2 = 0;
        for (Palette.Swatch next : palette.getSwatches()) {
            if (ColorUtils.isDark(next.getRgb())) {
                i += next.getPopulation();
            } else {
                i2 += next.getPopulation();
            }
        }
        int i3 = i2 + i;
        if (i3 > 0) {
            return ((float) i) / ((float) i3);
        }
        return 0.0f;
    }

    public static float getPaletteDarkness(Palette palette) {
        return getPaletteDarknessByPopulation(palette);
    }

    public static List<Palette.Swatch> getSwatchesSortedByPopulation(Palette palette) {
        ArrayList arrayList = new ArrayList(palette.getSwatches());
        Collections.sort(arrayList, new Comparator<Palette.Swatch>() {
            public int compare(Palette.Swatch swatch, Palette.Swatch swatch2) {
                return Integer.compare(swatch2.getPopulation(), swatch.getPopulation());
            }
        });
        return arrayList;
    }

    public static List<Palette.Swatch> getSwatchesSortedByLightnessThenPopulation(Palette palette) {
        ArrayList arrayList = new ArrayList(palette.getSwatches());
        Collections.sort(arrayList, new Comparator<Palette.Swatch>() {
            public int compare(Palette.Swatch swatch, Palette.Swatch swatch2) {
                int compare = Float.compare(swatch2.getHsl()[2], swatch.getHsl()[2]);
                if (compare != 0) {
                    return compare;
                }
                return Integer.compare(swatch2.getPopulation(), swatch.getPopulation());
            }
        });
        return arrayList;
    }

    public static List<Palette.Swatch> getSwatchesSortedByPopulationThenLightness(Palette palette) {
        ArrayList arrayList = new ArrayList(palette.getSwatches());
        Collections.sort(arrayList, new Comparator<Palette.Swatch>() {
            public int compare(Palette.Swatch swatch, Palette.Swatch swatch2) {
                int compare = Integer.compare(swatch2.getPopulation(), swatch.getPopulation());
                if (compare != 0) {
                    return compare;
                }
                return Float.compare(swatch2.getHsl()[2], swatch.getHsl()[2]);
            }
        });
        return arrayList;
    }

    public static List<Palette.Swatch> getSwatchesSortedByPopulationThenDarkness(Palette palette) {
        ArrayList arrayList = new ArrayList(palette.getSwatches());
        Collections.sort(arrayList, new Comparator<Palette.Swatch>() {
            public int compare(Palette.Swatch swatch, Palette.Swatch swatch2) {
                int compare = Integer.compare(swatch2.getPopulation(), swatch.getPopulation());
                if (compare != 0) {
                    return compare;
                }
                return Float.compare(swatch.getHsl()[2], swatch2.getHsl()[2]);
            }
        });
        return arrayList;
    }

    public static List<Palette.Swatch> getSwatchesSortedByDarknessThenPopulation(Palette palette) {
        ArrayList arrayList = new ArrayList(palette.getSwatches());
        Collections.sort(arrayList, new Comparator<Palette.Swatch>() {
            public int compare(Palette.Swatch swatch, Palette.Swatch swatch2) {
                int compare = Float.compare(swatch.getHsl()[2], swatch2.getHsl()[2]);
                if (compare != 0) {
                    return compare;
                }
                return Integer.compare(swatch2.getPopulation(), swatch.getPopulation());
            }
        });
        return arrayList;
    }

    public static List<Palette.Swatch> getSwatchesSortedBySaturationThenPopulation(Palette palette) {
        ArrayList arrayList = new ArrayList(palette.getSwatches());
        Collections.sort(arrayList, new Comparator<Palette.Swatch>() {
            public int compare(Palette.Swatch swatch, Palette.Swatch swatch2) {
                int compare = Float.compare(swatch2.getHsl()[1], swatch.getHsl()[1]);
                if (compare != 0) {
                    return compare;
                }
                return Integer.compare(swatch2.getPopulation(), swatch.getPopulation());
            }
        });
        return arrayList;
    }

    public static Palette.Swatch getDarkSwatch(Palette palette) {
        Palette.Swatch swatch;
        Palette.Swatch swatchForTarget = palette.getSwatchForTarget(DARK);
        if (swatchForTarget == null) {
            swatchForTarget = palette.getSwatchForTarget(Target.DARK_VIBRANT);
        }
        if (swatchForTarget == null && (swatchForTarget = palette.getSwatchForTarget(NEUTRAL)) != null && !ColorUtils.isDark(swatchForTarget.getRgb())) {
            swatchForTarget = null;
        }
        if (swatchForTarget == null) {
            swatchForTarget = palette.getSwatchForTarget(DOMINANT_DARK);
        }
        if (swatchForTarget == null) {
            swatchForTarget = palette.getSwatchForTarget(Target.DARK_MUTED);
        }
        if (swatchForTarget == null) {
            swatch = palette.getSwatchForTarget(DOMINANT);
            if (swatch != null && !ColorUtils.isDark(swatch.getRgb())) {
                swatch = null;
            }
        } else {
            swatch = swatchForTarget;
        }
        return applyColorConstraint(swatch, 1);
    }

    public static Palette.Swatch getLightSwatch(Palette palette) {
        Palette.Swatch swatch;
        Palette.Swatch swatchForTarget = palette.getSwatchForTarget(NEUTRAL);
        if (swatchForTarget != null && ColorUtils.isDark(swatchForTarget.getRgb())) {
            swatchForTarget = null;
        }
        if (swatchForTarget == null) {
            swatchForTarget = palette.getSwatchForTarget(LIGHT);
        }
        if (swatchForTarget == null) {
            swatchForTarget = palette.getSwatchForTarget(Target.LIGHT_MUTED);
        }
        if (swatchForTarget == null) {
            swatchForTarget = palette.getSwatchForTarget(Target.LIGHT_VIBRANT);
        }
        if (swatchForTarget == null) {
            swatchForTarget = palette.getSwatchForTarget(DOMINANT_LIGHT);
        }
        if (swatchForTarget == null) {
            swatch = palette.getSwatchForTarget(DOMINANT);
            if (swatch != null && ColorUtils.isDark(swatch.getRgb())) {
                swatch = null;
            }
        } else {
            swatch = swatchForTarget;
        }
        return applyColorConstraint(swatch, -1);
    }

    public static Palette.Swatch getVibrantSwatch(Palette palette) {
        Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
        if (vibrantSwatch == null) {
            vibrantSwatch = palette.getDarkVibrantSwatch();
        }
        return vibrantSwatch == null ? palette.getLightVibrantSwatch() : vibrantSwatch;
    }

    public static Palette.Swatch getMutedSwatch(Palette palette) {
        Palette.Swatch mutedSwatch = palette.getMutedSwatch();
        if (mutedSwatch == null) {
            mutedSwatch = palette.getDarkMutedSwatch();
        }
        return mutedSwatch == null ? palette.getLightMutedSwatch() : mutedSwatch;
    }

    public static Palette.Swatch getAutomaticSwatch(Palette palette) {
        return getAutomaticSwatch(palette, getPaletteDarknessByPopulation(palette));
    }

    public static Palette.Swatch getNextSwatch(Palette palette) {
        Palette.Swatch swatch = null;
        if (palette != null) {
            if (n < 11) {
                n = 11;
            }
            switch (n) {
                case 0:
                    swatch = palette.getSwatchForTarget(DOMINANT_VIBRANT);
                    logSwatch(swatch, "DOMINANT_VIBRANT");
                    break;
                case 1:
                    swatch = palette.getSwatchForTarget(DOMINANT_DARK);
                    logSwatch(swatch, "DOMINANT_DARK");
                    break;
                case 2:
                    swatch = palette.getSwatchForTarget(DOMINANT_MUTED);
                    logSwatch(swatch, "DOMINANT_MUTED");
                    break;
                case 3:
                    swatch = palette.getSwatchForTarget(DOMINANT_LIGHT);
                    logSwatch(swatch, "DOMINANT_LIGHT");
                    break;
                case 4:
                    swatch = palette.getSwatchForTarget(DOMINANT);
                    logSwatch(swatch, "DOMINANT");
                    break;
                case 5:
                    swatch = palette.getVibrantSwatch();
                    logSwatch(swatch, "Vibrant");
                    break;
                case 6:
                    swatch = palette.getDarkVibrantSwatch();
                    logSwatch(swatch, "DarkVibrant");
                    break;
                case 7:
                    swatch = palette.getLightVibrantSwatch();
                    logSwatch(swatch, "LightVibrant");
                    break;
                case 8:
                    swatch = palette.getMutedSwatch();
                    logSwatch(swatch, "Muted");
                    break;
                case 9:
                    swatch = palette.getDarkMutedSwatch();
                    logSwatch(swatch, "DarkMuted");
                    break;
                case 10:
                    swatch = palette.getLightMutedSwatch();
                    logSwatch(swatch, "LightMuted");
                    break;
                case 11:
                    swatch = palette.getSwatchForTarget(DARK);
                    logSwatch(swatch, "DARK");
                    break;
                case 12:
                    swatch = palette.getSwatchForTarget(NEUTRAL);
                    logSwatch(swatch, "NEUTRAL");
                    break;
                case 13:
                    swatch = palette.getSwatchForTarget(LIGHT);
                    logSwatch(swatch, "LIGHT");
                    break;
            }
            if (swatch != null || n > 13) {
                boolean z = bMaterial;
                boolean z2 = bMaterial;
                int i = n + 1;
                n = i;
                if (i > 13) {
                    n = 11;
                }
                bMaterial ^= DEBUG;
            } else {
                n++;
                bMaterial = false;
                return getNextSwatch(palette);
            }
        }
        return swatch;
    }

    public static Palette.Swatch getSwatch(Palette palette, String str, float f) {
        int i;
        String[] split = str.split("[, ]*if[ ]*\\([ ]*|[ ]*\\)[ ]*,[ ]*");
        int length = split.length;
        int i2 = 0;
        Palette.Swatch swatch = null;
        int i3 = 0;
        while (i3 < length) {
            String str2 = split[i3];
            if (str2.length() > 0) {
                float f2 = 0.0f;
                float f3 = 1.0f;
                int indexOf = str2.indexOf(58);
                if (indexOf >= 0) {
                    float f4 = 0.0f;
                    float f5 = 1.0f;
                    i = 0;
                    for (String str3 : str2.substring(i2, indexOf).split("[ ]*,[ ]*")) {
                        if (str3.indexOf(45) > -1) {
                            String[] split2 = str3.split("[ ]*-[ ]*");
                            switch (split2.length) {
                                case 1:
                                    f4 = Float.parseFloat(split2[0]);
                                    break;
                                case 2:
                                    f4 = Float.parseFloat(split2[0]);
                                    f5 = Float.parseFloat(split2[1]);
                                    break;
                            }
                        } else if (str3.contains("dark")) {
                            i = 1;
                        } else if (str3.contains("light")) {
                            i = -1;
                        }
                    }
                    str2 = str2.substring(indexOf + 1);
                    f2 = f4;
                    f3 = f5;
                } else {
                    i = 0;
                }
                if (f >= f2 && f <= f3) {
                    Palette.Swatch swatch2 = swatch;
                    for (String swatchForName : str2.toLowerCase().split("[ ]*,[ ]*")) {
                        swatch2 = getSwatchForName(palette, swatchForName);
                        if (i != 0) {
                            swatch2 = applyColorConstraint(swatch2, i);
                        }
                        if (swatch2 != null) {
                            return swatch2;
                        }
                    }
                    Palette palette2 = palette;
                    swatch = swatch2;
                    i3++;
                    i2 = 0;
                }
            }
            Palette palette3 = palette;
            i3++;
            i2 = 0;
        }
        return swatch;
    }

    private static Palette.Swatch applyColorConstraint(Palette.Swatch swatch, int i) {
        if (!(swatch == null || i == 0)) {
            boolean isDark = ColorUtils.isDark(swatch.getRgb());
            if (i != -1) {
                if (i == 1 && !isDark) {
                    return null;
                }
            } else if (isDark) {
                return null;
            }
        }
        return swatch;
    }

    public static Palette.Swatch getSwatchForName(Palette palette, String str) {
        if (palette != null) {
            if (str.contains("neutral")) {
                Palette.Swatch swatchForTarget = palette.getSwatchForTarget(NEUTRAL);
                logSwatch(swatchForTarget, "NEUTRAL");
                return swatchForTarget;
            }
            boolean contains = str.contains("vibrant");
            boolean contains2 = str.contains("muted");
            boolean contains3 = str.contains("dominant");
            if (str.contains("dark")) {
                if (contains) {
                    Palette.Swatch swatchForTarget2 = palette.getSwatchForTarget(Target.DARK_VIBRANT);
                    logSwatch(swatchForTarget2, "DARK_VIBRANT");
                    return swatchForTarget2;
                } else if (contains2) {
                    Palette.Swatch swatchForTarget3 = palette.getSwatchForTarget(Target.DARK_MUTED);
                    logSwatch(swatchForTarget3, "DARK_MUTED");
                    return swatchForTarget3;
                } else if (contains3) {
                    Palette.Swatch swatchForTarget4 = palette.getSwatchForTarget(DOMINANT_DARK);
                    logSwatch(swatchForTarget4, "DOMINANT_DARK");
                    return swatchForTarget4;
                } else {
                    Palette.Swatch swatchForTarget5 = palette.getSwatchForTarget(DARK);
                    logSwatch(swatchForTarget5, "DARK");
                    return swatchForTarget5;
                }
            } else if (str.contains("light")) {
                if (contains) {
                    Palette.Swatch swatchForTarget6 = palette.getSwatchForTarget(Target.LIGHT_VIBRANT);
                    logSwatch(swatchForTarget6, "LIGHT_VIBRANT");
                    return swatchForTarget6;
                } else if (contains2) {
                    Palette.Swatch swatchForTarget7 = palette.getSwatchForTarget(Target.LIGHT_MUTED);
                    logSwatch(swatchForTarget7, "LIGHT_MUTED");
                    return swatchForTarget7;
                } else if (contains3) {
                    Palette.Swatch swatchForTarget8 = palette.getSwatchForTarget(DOMINANT_LIGHT);
                    logSwatch(swatchForTarget8, "DOMINANT_LIGHT");
                    return swatchForTarget8;
                } else {
                    Palette.Swatch swatchForTarget9 = palette.getSwatchForTarget(LIGHT);
                    logSwatch(swatchForTarget9, "LIGHT");
                    return swatchForTarget9;
                }
            } else if (contains) {
                Palette.Swatch swatchForTarget10 = palette.getSwatchForTarget(Target.VIBRANT);
                logSwatch(swatchForTarget10, "VIBRANT");
                return swatchForTarget10;
            } else if (contains2) {
                Palette.Swatch swatchForTarget11 = palette.getSwatchForTarget(Target.MUTED);
                logSwatch(swatchForTarget11, "MUTED");
                return swatchForTarget11;
            } else if (contains3) {
                Palette.Swatch swatchForTarget12 = palette.getSwatchForTarget(DOMINANT);
                logSwatch(swatchForTarget12, "DOMINANT");
                return swatchForTarget12;
            }
        }
        return null;
    }

    public static Palette.Swatch getAutomaticSwatch(Palette palette, float f) {
        return getAutomaticSwatch(palette, f, 0.1f, 0.7f);
    }

    public static Palette.Swatch getAutomaticSwatch(Palette palette, float f, float f2, float f3) {
        Palette.Swatch swatch;
        if (f >= f3) {
            swatch = getDarkSwatch(palette);
        } else {
            swatch = f <= f2 ? getLightSwatch(palette) : null;
        }
        if (swatch == null) {
            swatch = palette.getSwatchForTarget(NEUTRAL);
        }
        if (swatch == null) {
            swatch = palette.getSwatchForTarget(Target.VIBRANT);
        }
        if (swatch == null) {
            swatch = palette.getSwatchForTarget(Target.DARK_VIBRANT);
        }
        if (swatch == null) {
            swatch = palette.getSwatchForTarget(DARK);
        }
        if (swatch == null) {
            swatch = palette.getSwatchForTarget(Target.MUTED);
        }
        return swatch == null ? palette.getSwatchForTarget(DOMINANT) : swatch;
    }

    public static Palette.Swatch getNearestMaterialSwatch(Palette.Swatch swatch) {
        return getNearestMaterialSwatch(swatch, false);
    }

    public static Palette.Swatch getNearestMaterialSwatch(Palette.Swatch swatch, boolean z) {
        if (swatch != null) {
            return new Palette.Swatch(getNearestMaterialColor(swatch.getRgb(), z), swatch.getPopulation());
        }
        return null;
    }

    public static double distanceEuclideanSquared(@NonNull double[] dArr, @NonNull double[] dArr2) {
        return Math.pow(dArr[0] - dArr2[0], 2.0d) + Math.pow(dArr[1] - dArr2[1], 2.0d) + Math.pow(dArr[2] - dArr2[2], 2.0d);
    }

    public static double distanceEuclidean(@NonNull double[] dArr, @NonNull double[] dArr2) {
        return Math.sqrt(Math.pow(dArr[0] - dArr2[0], 2.0d) + Math.pow(dArr[1] - dArr2[1], 2.0d) + Math.pow(dArr[2] - dArr2[2], 2.0d));
    }

    private static double[] getMaterialLabColors(boolean z) {
        if (z) {
            if (materialLabPrimaryColors == null) {
                materialLabPrimaryColors = generateMaterialLabColors(DEBUG);
            }
            return materialLabPrimaryColors;
        }
        if (materialLabColors == null) {
            materialLabColors = generateMaterialLabColors(false);
        }
        return materialLabColors;
    }

    public static int getNearestMaterialColor(int i, boolean z) {
        double[] materialLabColors2 = getMaterialLabColors(z);
        double[] dArr = new double[3];
        double[] dArr2 = new double[3];
        ColorUtils.colorToLAB(i, dArr);
        double d = Double.MAX_VALUE;
        double d2 = Double.MAX_VALUE;
        int i2 = -1;
        for (int i3 = 0; i3 < materialLabColors2.length; i3 += 3) {
            dArr2[0] = materialLabColors2[i3 + 0];
            dArr2[1] = materialLabColors2[i3 + 1];
            dArr2[2] = materialLabColors2[i3 + 2];
            double distanceEuclideanSquared = distanceEuclideanSquared(dArr, dArr2);
            if (distanceEuclideanSquared < d2) {
                i2 = i3;
                d2 = distanceEuclideanSquared;
            }
        }
        double d3 = Double.MAX_VALUE;
        int i4 = -1;
        for (int i5 = 0; i5 < materialLabColors2.length; i5 += 3) {
            dArr2[0] = materialLabColors2[i5 + 0];
            dArr2[1] = materialLabColors2[i5 + 1];
            dArr2[2] = materialLabColors2[i5 + 2];
            double distanceEuclidean = distanceEuclidean(dArr, dArr2);
            if (distanceEuclidean < d3) {
                i4 = i5;
                d3 = distanceEuclidean;
            }
        }
        if (i2 == i4) {
            int i6 = -1;
            for (int i7 = 0; i7 < materialLabColors2.length; i7 += 3) {
                dArr2[0] = materialLabColors2[i7 + 0];
                dArr2[1] = materialLabColors2[i7 + 1];
                dArr2[2] = materialLabColors2[i7 + 2];
                double calculateDeltaE = ColorUtils.calculateDeltaE(dArr, dArr2);
                if (calculateDeltaE < d) {
                    i6 = i7;
                    d = calculateDeltaE;
                }
            }
            if (i2 != i6) {
                ColorUtils.LABToColor(materialLabColors2[i6 + 0], materialLabColors2[i6 + 1], materialLabColors2[i6 + 2]);
            } else {
                i6 = i2;
            }
            return ColorUtils.LABToColor(materialLabColors2[i6], materialLabColors2[i6 + 1], materialLabColors2[i6 + 2]);
        }
        throw new RuntimeException("squared distance not working?");
    }

    private static double[] generateMaterialLabColors(boolean z) {
        int[] generateMaterialColors = generateMaterialColors(z);
        double[] dArr = new double[(generateMaterialColors.length * 3)];
        double[] dArr2 = new double[3];
        for (int i = 0; i < generateMaterialColors.length; i++) {
            ColorUtils.colorToLAB(generateMaterialColors[i], dArr2);
            int i2 = i * 3;
            dArr[i2 + 0] = dArr2[0];
            dArr[i2 + 1] = dArr2[1];
            dArr[i2 + 2] = dArr2[2];
        }
        for (int i3 = 0; i3 < generateMaterialColors.length; i3++) {
        }
        for (int i4 = 0; i4 < dArr.length; i4 += 3) {
        }
        return dArr;
    }

    private static int[] generateMaterialColors(boolean z) {
        if (z) {
            return generateMaterialPrimaryColors();
        }
        return generateMaterialColors();
    }

    private static int[] generateMaterialColors() {
        return new int[]{-5138, -12846, -1074534, -1739917, -1092784, -769226, -1754827, -2937041, -3790808, -4776932, -1185802, -3029783, -5005861, -6982195, -8497214, -10011977, -10603087, -11457112, -12245088, -13558894, -1968642, -4987396, -8268550, -11549705, -14043402, -16537100, -16540699, -16611119, -16615491, -16689253, -1509911, -3610935, -5908825, -8271996, -10044566, -11751600, -12345273, -13070788, -13730510, -14983648, -537, -1596, -2659, -3722, -4520, -5317, -141259, -278483, -415707, -688361, -267801, -13124, -21615, -30107, -36797, -43230, -765666, -1684967, -2604267, -4246004, -1249295, -3155748, -5194043, -7297874, -8875876, -10453621, -11243910, -12232092, -13154481, -14273992, -203540, -476208, -749647, -1023342, -1294214, -1499549, -2614432, -4056997, -5434281, -7860657, -1512714, -3814679, -6313766, -8812853, -10720320, -12627531, -13022805, -13615201, -14142061, -15064194, -2033670, -5051406, -8331542, -11677471, -14235942, -16728876, -16732991, -16738393, -16743537, -16752540, -919319, -2298424, -3808859, -5319295, -6501275, -7617718, -8604862, -9920712, -11171025, -13407970, -1823, -4941, -8062, -10929, -13784, -16121, -19712, -24576, -28928, -37120, -1053719, -2634552, -4412764, -6190977, -7508381, -8825528, -9614271, -10665929, -11652050, -12703965, -793099, -1982745, -3238952, -4560696, -5552196, -6543440, -7461718, -8708190, -9823334, -11922292, -1838339, -4464901, -7288071, -10177034, -12409355, -14575885, -14776091, -15108398, -15374912, -15906911, -2034959, -5054501, -8336444, -11684180, -14244198, -16738680, -16742021, -16746133, -16750244, -16757440, -394265, -985917, -1642852, -2300043, -2825897, -3285959, -4142541, -5262293, -6382300, -8227049, -3104, -8014, -13184, -18611, -22746, -26624, -291840, -689152, -1086464, -1683200, -328966, -657931, -1118482, -2039584, -4342339, -6381922, -9079435, -10395295, -12434878, -14606047};
    }

    private static int[] generateMaterialPrimaryColors() {
        return new int[]{-12846, -769226, -2937041, -3029783, -10011977, -11457112, -4987396, -16537100, -16611119, -3610935, -11751600, -13070788, -1596, -5317, -278483, -13124, -43230, -1684967, -3155748, -10453621, -12232092, -476208, -1499549, -4056997, -3814679, -12627531, -13615201, -5051406, -16728876, -16738393, -2298424, -7617718, -9920712, -4941, -16121, -24576, -2634552, -8825528, -10665929, -1982745, -6543440, -8708190, -4464901, -14575885, -15108398, -5054501, -16738680, -16746133, -985917, -3285959, -5262293, -8014, -26624, -689152, -657931, -6381922, -10395295};
    }

    private static int[] generateMaterialAccentColors() {
        return new int[]{-30080, -44462, -59580, -2818048, -5011201, -8630785, -10149889, -10354454, -8333057, -12532481, -16731905, -16739862, -4589878, -9834322, -16718218, -16725933, -115, InputDeviceCompat.SOURCE_ANY, -5632, -10752, -24960, -37312, -49920, -2282496, -32597, -49023, -720809, -3862174, -7561473, -11309570, -12756226, -13611010, -8060929, -15138817, -16718337, -16729900, -3342448, -5046439, -8978685, -10167017, -6785, -10432, -15360, -21760, -1408772, -2080517, -2817799, -5635841, -8211969, -12285185, -14059009, -14064897, -5767189, -10158118, -14816842, -16728155, -721023, -1114303, -3735808, -5314048, -11904, -21696, -28416, -37632};
    }

    private static Palette.Swatch getSwatch(Palette palette, Target target) {
        getNameForTarget(target);
        return palette.getSwatchForTarget(target);
    }

    private static Palette.Swatch getSwatch(Palette palette, Target target, String str) {
        return palette.getSwatchForTarget(target);
    }

    private static String getNameForTarget(Target target) {
        if (target == Target.LIGHT_VIBRANT) {
            return "LIGHT_VIBRANT";
        }
        if (target == Target.VIBRANT) {
            return "VIBRANT";
        }
        if (target == Target.DARK_VIBRANT) {
            return "DARK_VIBRANT";
        }
        if (target == Target.LIGHT_MUTED) {
            return "LIGHT_MUTED";
        }
        if (target == Target.MUTED) {
            return "MUTED";
        }
        if (target == Target.DARK_MUTED) {
            return "DARK_MUTED";
        }
        if (target == DARK) {
            return "DARK";
        }
        if (target == NEUTRAL) {
            return "NEUTRAL";
        }
        if (target == LIGHT) {
            return "LIGHT";
        }
        if (target == DOMINANT) {
            return "DOMINANT";
        }
        if (target == DOMINANT_DARK) {
            return "DOMINANT_DARK";
        }
        if (target == DOMINANT_LIGHT) {
            return "DOMINANT_LIGHT";
        }
        if (target == DOMINANT_VIBRANT) {
            return "DOMINANT_VIBRANT";
        }
        return target == DOMINANT_MUTED ? "DOMINANT_MUTED" : EnvironmentCompat.MEDIA_UNKNOWN;
    }
}
