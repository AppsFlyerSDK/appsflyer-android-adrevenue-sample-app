package com.appsflyer.af_adrevenue.enums

enum class AdType(val str: String) {
    BANNER("Banner"),
    INTERSTITIAL("Interstitial"),
    INTERSTITIAL_VIDEO("Interstitial video"),
    REWARDED("Rewarded"),
    REWARDED_INTERSTITIAL("Rewarded interstitial"),
    NATIVE_AD("Native ad");

    companion object {
       fun parseFromString(str:String): AdType{
          return AdType.values().first { it.str == str }
       }
    }
}