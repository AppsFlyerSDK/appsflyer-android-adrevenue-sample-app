package com.appsflyer.af_adrevenue.fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.appsflyer.adrevenue.AppsFlyerAdRevenue
import com.appsflyer.adrevenue.adnetworks.AppsFlyerAdNetworkEventType
import com.appsflyer.adrevenue.adnetworks.generic.MediationNetwork
import com.appsflyer.adrevenue.adnetworks.generic.Scheme
import com.appsflyer.af_adrevenue.databinding.FragmentAdMobBinding
import com.appsflyer.af_adrevenue.enums.AdType
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.nativead.MediaView
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback
import java.util.*

class AdMobFragment : androidx.fragment.app.Fragment() {
    private var _binding: FragmentAdMobBinding? = null
    private val binding get() = _binding!!
    private val args: AdMobFragmentArgs by navArgs()
    private var mAdView: AdView? = null
    private var adRequest = AdRequest.Builder().build()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdMobBinding.inflate(inflater, container, false)
        mAdView = binding.adView
        when(args.adType){
            AdType.BANNER -> showBanner()
            AdType.INTERSTITIAL -> showInterstitial()
            AdType.INTERSTITIAL_VIDEO -> showInterstitialVideo()
            AdType.REWARDED -> showRewarded()
            AdType.REWARDED_INTERSTITIAL -> showRewardedInterstitial()
            AdType.NATIVE_AD -> showNativeAd()
        }
        binding.toolbar.title = args.adType.toString()
        binding.textView.text = args.adType.toString()
        binding.toolbar.setNavigationOnClickListener { it.findNavController().popBackStack() }

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showBanner() {
        mAdView?.adListener = object : AdListener() {
            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                super.onAdFailedToLoad(loadAdError)
                Log.d("af_sdk","Ad failed to load because: $loadAdError")
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
                mAdView!!.onPaidEventListener = OnPaidEventListener { adValue: AdValue? ->
                    val params: Map<String, String> =
                        object : HashMap<String, String>() {
                            init {
                                put(Scheme.AD_TYPE, AppsFlyerAdNetworkEventType.BANNER.toString())
                                put(Scheme.AD_UNIT, "89b8c0159a50ebd1")
                            }
                        }
                    AppsFlyerAdRevenue.logAdRevenue(
                        "ironsource",
                        MediationNetwork.googleadmob,
                        Currency.getInstance(Locale.US),
                        0.99,
                        params
                    )
                }
            }
        }
        binding.adView.visibility = View.VISIBLE
        mAdView?.loadAd(adRequest)
    }

    private fun showInterstitial() {
        context?.let {
            InterstitialAd.load(
                it,
                "ca-app-pub-3940256099942544/1033173712",
                adRequest,
                object : InterstitialAdLoadCallback() {
                    override fun onAdLoaded(interstitialAd: InterstitialAd) {
                        super.onAdLoaded(interstitialAd)
                        interstitialAd.onPaidEventListener = OnPaidEventListener {
                            val params: Map<String, String> =
                                object : HashMap<String, String>() {
                                    init {
                                        put(
                                            Scheme.AD_TYPE,
                                            AppsFlyerAdNetworkEventType.INTERSTITIAL.toString()
                                        )
                                        put(Scheme.AD_UNIT, "ca-app-pub-3940256099942544/1033173712")
                                    }
                                }
                            AppsFlyerAdRevenue.logAdRevenue(
                                "ironsource",
                                MediationNetwork.customMediation,
                                Currency.getInstance(Locale.US),
                                0.99,
                                params
                            )
                        }
                        binding.adView.visibility = View.VISIBLE
                        interstitialAd.show(context as Activity)
                    }
                })
        }
    }

    private fun showInterstitialVideo() {
        context?.let {
            InterstitialAd.load(
                it,
                "ca-app-pub-3940256099942544/8691691433",
                adRequest,
                object : InterstitialAdLoadCallback() {
                    override fun onAdLoaded(interstitialAd: InterstitialAd) {
                        super.onAdLoaded(interstitialAd)
                        interstitialAd.onPaidEventListener = OnPaidEventListener {
                            val params: Map<String, String> =
                                object : HashMap<String, String>() {
                                    init {
                                        put(
                                            Scheme.AD_TYPE,
                                            AppsFlyerAdNetworkEventType.INTERSTITIAL.toString()
                                        )
                                        put(Scheme.AD_UNIT, "ca-app-pub-3940256099942544/8691691433")
                                    }
                                }
                            AppsFlyerAdRevenue.logAdRevenue(
                                "ironsource",
                                MediationNetwork.googleadmob,
                                Currency.getInstance(Locale.US),
                                0.99,
                                params
                            )
                        }
                        binding.adView.visibility = View.VISIBLE
                        interstitialAd.show(context as Activity)
                    }
                })
        }
    }

    private fun showRewarded() {
        context?.let {
            RewardedAd.load(it,
                "ca-app-pub-3940256099942544/5224354917",
                adRequest,
                object : RewardedAdLoadCallback() {
                    override fun onAdLoaded(rewardedAd: RewardedAd) {
                        super.onAdLoaded(rewardedAd)
                        rewardedAd.onPaidEventListener = OnPaidEventListener { adValue: AdValue? ->
                            val params: Map<String, String> =
                                object : HashMap<String, String>() {
                                    init {
                                        put(
                                            Scheme.AD_TYPE,
                                            AppsFlyerAdNetworkEventType.REWARDED.toString()
                                        )
                                        put(Scheme.AD_UNIT, "ca-app-pub-3940256099942544/5224354917")
                                    }
                                }
                            AppsFlyerAdRevenue.logAdRevenue(
                                "ironsource",
                                MediationNetwork.googleadmob,
                                Currency.getInstance(Locale.US),
                                0.99,
                                params
                            )
                        }
                        binding.adView.visibility = View.VISIBLE
                        rewardedAd.show(
                            context as Activity
                        ) { rewardItem: RewardItem? -> }
                    }
                }
            )
        }
    }

    private fun showRewardedInterstitial() {
        context?.let {
            RewardedInterstitialAd.load(it,
                "ca-app-pub-3940256099942544/5354046379",
                adRequest,
                object : RewardedInterstitialAdLoadCallback() {
                    override fun onAdLoaded(rewardedInterstitialAd: RewardedInterstitialAd) {
                        super.onAdLoaded(rewardedInterstitialAd)
                        rewardedInterstitialAd.onPaidEventListener =
                            OnPaidEventListener { adValue: AdValue? ->
                                val params: Map<String, String> =
                                    object : HashMap<String, String>() {
                                        init {
                                            put(
                                                Scheme.AD_TYPE,
                                                AppsFlyerAdNetworkEventType.REWARDED.toString()
                                            )
                                            put(
                                                Scheme.AD_UNIT,
                                                "ca-app-pub-3940256099942544/5354046379"
                                            )
                                        }
                                    }
                                AppsFlyerAdRevenue.logAdRevenue(
                                    "ironsource",
                                    MediationNetwork.googleadmob,
                                    Currency.getInstance(Locale.US),
                                    0.99,
                                    params
                                )
                            }
                        binding.adView.visibility = View.VISIBLE
                        rewardedInterstitialAd.show(
                            context as Activity
                        ) { }
                    }
                }
            )
        }
    }

    fun showNativeAd() {
        binding.nativeAdView.visibility = View.VISIBLE
        context?.let {
            AdLoader.Builder(it, "ca-app-pub-3940256099942544/2247696110")
                .forNativeAd { nativeAd ->
                    val nativeAdView = binding.nativeAdView
                    val headlineView = nativeAdView.findViewById<TextView>(binding.adHeadline.id)
                    headlineView.text = nativeAd.headline
                    nativeAdView.headlineView = headlineView
                    val bodyView = nativeAdView.findViewById<TextView>(binding.adBody.id)
                    bodyView.text = nativeAd.body
                    nativeAdView.bodyView = bodyView
                    val mediaView =
                        nativeAdView.findViewById<MediaView>(binding.adMedia.id)
                    nativeAdView.mediaView = mediaView
                    nativeAdView.setNativeAd(nativeAd)
                    nativeAd.setOnPaidEventListener {
                        val params: Map<String, String> =
                            object : HashMap<String, String>() {
                                init {
                                    put(
                                        Scheme.AD_TYPE,
                                        AppsFlyerAdNetworkEventType.NATIVE.toString()
                                    )
                                    put(Scheme.AD_UNIT, "89b8c0159a50ebd1")
                                }
                            }
                        AppsFlyerAdRevenue.logAdRevenue(
                            "ironsource",
                            MediationNetwork.googleadmob,
                            Currency.getInstance(Locale.US),
                            0.99,
                            params
                        )
                    }
                }.build()
        }?.loadAd(adRequest)
    }
}