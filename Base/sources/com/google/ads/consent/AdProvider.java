package com.google.ads.consent;

import com.google.gson.annotations.SerializedName;

public final class AdProvider {
    @SerializedName("company_id")
    private String id;
    @SerializedName("company_name")
    private String name;
    @SerializedName("policy_url")
    private String privacyPolicyUrlString;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getPrivacyPolicyUrlString() {
        return this.privacyPolicyUrlString;
    }

    public void setPrivacyPolicyUrlString(String str) {
        this.privacyPolicyUrlString = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.id.equals(((AdProvider) obj).id);
    }

    public int hashCode() {
        return this.id.hashCode();
    }
}
