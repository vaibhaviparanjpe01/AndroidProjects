package com.flurry.android;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.flurry.sdk.db;
import com.flurry.sdk.x;
import java.util.HashMap;
import java.util.Map;

public final class FlurryGamingAgent {
    private static final String a = "FlurryGamingAgent";

    public static FlurryEventRecordStatus logLevelStart(@NonNull String str) {
        return logLevelStart(str, (Map<String, String>) null);
    }

    public static FlurryEventRecordStatus logLevelStart(@NonNull String str, Map<String, String> map) {
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (TextUtils.isEmpty(str)) {
            db.b(a, "String levelId passed to logLevelStart was null or empty.");
            return flurryEventRecordStatus;
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("fl.LevelID", str);
        int size = hashMap.size();
        if (map != null && map.size() > 0) {
            hashMap.putAll(map);
        }
        try {
            return x.a().a("Flurry.LevelStart", (Map<String, String>) hashMap, size);
        } catch (Throwable th) {
            db.a(a, "Failed to log event: Flurry.LevelStart", th);
            return flurryEventRecordStatus;
        }
    }

    public static FlurryEventRecordStatus logLevelEnd(@NonNull String str, boolean z) {
        return logLevelEnd(str, z, (Double) null);
    }

    public static FlurryEventRecordStatus logLevelEnd(@NonNull String str, boolean z, Double d) {
        return logLevelEnd(str, z, d, (Map<String, String>) null);
    }

    public static FlurryEventRecordStatus logLevelEnd(@NonNull String str, boolean z, Double d, Map<String, String> map) {
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (TextUtils.isEmpty(str)) {
            db.b(a, "String levelId passed to logLevelEnd was null or empty.");
            return flurryEventRecordStatus;
        }
        HashMap hashMap = new HashMap(3);
        hashMap.put("fl.LevelID", str);
        hashMap.put("fl.Success", z ? "True" : "False");
        if (d != null) {
            hashMap.put("fl.Score", Double.toString(d.doubleValue()));
        }
        int size = hashMap.size();
        if (map != null && map.size() > 0) {
            hashMap.putAll(map);
        }
        try {
            return x.a().a("Flurry.LevelEnd", (Map<String, String>) hashMap, size);
        } catch (Throwable th) {
            db.a(a, "Failed to log event: Flurry.LevelEnd", th);
            return flurryEventRecordStatus;
        }
    }

    public static FlurryEventRecordStatus logPlayerLevel(double d) {
        return logPlayerLevel(d, (Map<String, String>) null);
    }

    public static FlurryEventRecordStatus logPlayerLevel(double d, Map<String, String> map) {
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        HashMap hashMap = new HashMap(1);
        hashMap.put("fl.Level", Double.toString(d));
        int size = hashMap.size();
        if (map != null && map.size() > 0) {
            hashMap.putAll(map);
        }
        try {
            return x.a().a("Flurry.PlayerLevel", (Map<String, String>) hashMap, size);
        } catch (Throwable th) {
            db.a(a, "Failed to log event: Flurry.PlayerLevel", th);
            return flurryEventRecordStatus;
        }
    }

    public static FlurryEventRecordStatus logAchievement(@NonNull String str) {
        return logAchievement(str, (Map<String, String>) null);
    }

    public static FlurryEventRecordStatus logAchievement(@NonNull String str, Map<String, String> map) {
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (TextUtils.isEmpty(str)) {
            db.b(a, "String achievementId passed to logAchievement was null or empty.");
            return flurryEventRecordStatus;
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("fl.AchievementID", str);
        int size = hashMap.size();
        if (map != null && map.size() > 0) {
            hashMap.putAll(map);
        }
        try {
            return x.a().a("Flurry.Achievement", (Map<String, String>) hashMap, size);
        } catch (Throwable th) {
            db.a(a, "Failed to log event: Flurry.Achievement", th);
            return flurryEventRecordStatus;
        }
    }

    public static FlurryEventRecordStatus logCharacterLevel(@NonNull String str, double d) {
        return logCharacterLevel(str, d, (Map<String, String>) null);
    }

    public static FlurryEventRecordStatus logCharacterLevel(@NonNull String str, double d, Map<String, String> map) {
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (TextUtils.isEmpty(str)) {
            db.b(a, "String characterId passed to logCharacterLevel was null or empty.");
            return flurryEventRecordStatus;
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("fl.CharacterID", str);
        hashMap.put("fl.Level", Double.toString(d));
        int size = hashMap.size();
        if (map != null && map.size() > 0) {
            hashMap.putAll(map);
        }
        try {
            return x.a().a("Flurry.CharacterLevel", (Map<String, String>) hashMap, size);
        } catch (Throwable th) {
            db.a(a, "Failed to log event Flurry.CharacterLevel", th);
            return flurryEventRecordStatus;
        }
    }

    public static FlurryEventRecordStatus logCharacterDeath(@NonNull String str) {
        return logCharacterDeath(str, (Map<String, String>) null);
    }

    public static FlurryEventRecordStatus logCharacterDeath(@NonNull String str, Map<String, String> map) {
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (TextUtils.isEmpty(str)) {
            db.b(a, "String characterId passed to logCharacterDeath was null or empty.");
            return flurryEventRecordStatus;
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("fl.CharacterID", str);
        int size = hashMap.size();
        if (map != null && map.size() > 0) {
            hashMap.putAll(map);
        }
        try {
            return x.a().a("Flurry.CharacterDeath", (Map<String, String>) hashMap, size);
        } catch (Throwable th) {
            db.a(a, "Failed to log event: Flurry.CharacterDeath", th);
            return flurryEventRecordStatus;
        }
    }

    public static FlurryEventRecordStatus logCurrencyAcquired(double d, @NonNull String str, @NonNull AcquireReason acquireReason) {
        return logCurrencyAcquired(d, str, acquireReason, (Double) null);
    }

    public static FlurryEventRecordStatus logCurrencyAcquired(double d, @NonNull String str, @NonNull AcquireReason acquireReason, Double d2) {
        return logCurrencyAcquired(d, str, acquireReason, d2, (Map<String, String>) null);
    }

    public static FlurryEventRecordStatus logCurrencyAcquired(double d, @NonNull String str, @NonNull AcquireReason acquireReason, Double d2, Map<String, String> map) {
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (TextUtils.isEmpty(str)) {
            db.b(a, "String currencyType passed to logCurrencyAcquired was null or empty.");
            return flurryEventRecordStatus;
        }
        HashMap hashMap = new HashMap(4);
        hashMap.put("fl.Amount", Double.toString(d));
        hashMap.put("fl.CurType", str);
        hashMap.put("fl.Reason", acquireReason.getValue());
        if (d2 != null) {
            hashMap.put("fl.Balance", Double.toString(d2.doubleValue()));
        }
        int size = hashMap.size();
        if (map != null && map.size() > 0) {
            hashMap.putAll(map);
        }
        try {
            return x.a().a("Flurry.VCAcquired", (Map<String, String>) hashMap, size);
        } catch (Throwable th) {
            db.a(a, "Failed to log event: Flurry.VCAcquired", th);
            return flurryEventRecordStatus;
        }
    }

    public static FlurryEventRecordStatus logCurrencySpent(double d, @NonNull String str) {
        return logCurrencySpent(d, str, (Double) null);
    }

    public static FlurryEventRecordStatus logCurrencySpent(double d, @NonNull String str, Double d2) {
        return logCurrencySpent(d, str, d2, (Map<String, String>) null);
    }

    public static FlurryEventRecordStatus logCurrencySpent(double d, @NonNull String str, Double d2, Map<String, String> map) {
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (TextUtils.isEmpty(str)) {
            db.b(a, "String currencyType passed to logCurrencySpent was null or empty.");
            return flurryEventRecordStatus;
        }
        HashMap hashMap = new HashMap(3);
        hashMap.put("fl.Amount", Double.toString(d));
        hashMap.put("fl.CurType", str);
        if (d2 != null) {
            hashMap.put("fl.Balance", Double.toString(d2.doubleValue()));
        }
        int size = hashMap.size();
        if (map != null && map.size() > 0) {
            hashMap.putAll(map);
        }
        try {
            return x.a().a("Flurry.VCSpent", (Map<String, String>) hashMap, size);
        } catch (Throwable th) {
            db.a(a, "Failed to log event: Flurry.VCSpent", th);
            return flurryEventRecordStatus;
        }
    }

    public static FlurryEventRecordStatus logItemAcquired(@NonNull String str, double d) {
        return logItemAcquired(str, d, (Double) null);
    }

    public static FlurryEventRecordStatus logItemAcquired(@NonNull String str, double d, Double d2) {
        return logItemAcquired(str, d, d2, (Double) null, (String) null);
    }

    public static FlurryEventRecordStatus logItemAcquired(@NonNull String str, double d, Double d2, String str2) {
        return logItemAcquired(str, d, (Double) null, d2, str2);
    }

    public static FlurryEventRecordStatus logItemAcquired(@NonNull String str, double d, Double d2, Double d3, String str2) {
        return logItemAcquired(str, d, d2, d3, str2, (Map<String, String>) null);
    }

    public static FlurryEventRecordStatus logItemAcquired(@NonNull String str, double d, Double d2, Double d3, String str2, Map<String, String> map) {
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (TextUtils.isEmpty(str)) {
            db.b(a, "String itemId passed to logItemAcquired was null or empty.");
            return flurryEventRecordStatus;
        }
        HashMap hashMap = new HashMap(5);
        hashMap.put("fl.ItemID", str);
        hashMap.put("fl.Amount", Double.toString(d));
        if (d2 != null) {
            hashMap.put("fl.Balance", Double.toString(d2.doubleValue()));
        }
        if (d3 != null) {
            hashMap.put("fl.Cost", Double.toString(d3.doubleValue()));
        }
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("fl.CurType", str2);
        }
        int size = hashMap.size();
        if (map != null && map.size() > 0) {
            hashMap.putAll(map);
        }
        try {
            return x.a().a("Flurry.ItemAcquired", (Map<String, String>) hashMap, size);
        } catch (Throwable th) {
            db.a(a, "Failed to log event: Flurry.ItemAcquired", th);
            return flurryEventRecordStatus;
        }
    }

    public static FlurryEventRecordStatus logItemDisposed(@NonNull String str, double d) {
        return logItemDisposed(str, d, (Double) null);
    }

    public static FlurryEventRecordStatus logItemDisposed(@NonNull String str, double d, Double d2) {
        return logItemDisposed(str, d, d2, (Double) null, (String) null);
    }

    public static FlurryEventRecordStatus logItemDisposed(@NonNull String str, double d, Double d2, String str2) {
        return logItemDisposed(str, d, (Double) null, d2, str2);
    }

    public static FlurryEventRecordStatus logItemDisposed(@NonNull String str, double d, Double d2, Double d3, String str2) {
        return logItemDisposed(str, d, d2, d3, str2, (Map<String, String>) null);
    }

    public static FlurryEventRecordStatus logItemDisposed(@NonNull String str, double d, Double d2, Double d3, String str2, Map<String, String> map) {
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (TextUtils.isEmpty(str)) {
            db.b(a, "String itemId passed to logItemDisposed was null or empty.");
            return flurryEventRecordStatus;
        }
        HashMap hashMap = new HashMap(5);
        hashMap.put("fl.ItemID", str);
        hashMap.put("fl.Amount", Double.toString(d));
        if (d2 != null) {
            hashMap.put("fl.Balance", Double.toString(d2.doubleValue()));
        }
        if (d3 != null) {
            hashMap.put("fl.Cost", Double.toString(d3.doubleValue()));
        }
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("fl.CurType", str2);
        }
        int size = hashMap.size();
        if (map != null && map.size() > 0) {
            hashMap.putAll(map);
        }
        try {
            return x.a().a("Flurry.ItemDisposed", (Map<String, String>) hashMap, size);
        } catch (Throwable th) {
            db.a(a, "Failed to log event: Flurry.ItemDisposed", th);
            return flurryEventRecordStatus;
        }
    }

    public enum AcquireReason {
        EARN("Earn"),
        TRADE("Trade"),
        BOUGHT("Bought"),
        AD_REWARDED("Ad Rewarded"),
        OTHER("Other");
        
        public final String value;

        private AcquireReason(String str) {
            this.value = str;
        }

        public final String getValue() {
            return this.value;
        }

        public final String toString() {
            return this.value;
        }
    }
}
