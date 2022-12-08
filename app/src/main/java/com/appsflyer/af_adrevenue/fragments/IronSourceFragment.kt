package com.appsflyer.af_adrevenue.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.appsflyer.adrevenue.AppsFlyerAdRevenue
import com.appsflyer.adrevenue.adnetworks.AppsFlyerAdNetworkEventType
import com.appsflyer.adrevenue.adnetworks.generic.MediationNetwork
import com.appsflyer.adrevenue.adnetworks.generic.Scheme
import com.appsflyer.af_adrevenue.databinding.FragmentIronSourceBinding
import com.appsflyer.af_adrevenue.enums.AdType
import com.ironsource.mediationsdk.ISBannerSize
import com.ironsource.mediationsdk.IronSource
import com.ironsource.mediationsdk.IronSourceBannerLayout
import com.ironsource.mediationsdk.logger.IronSourceError
import com.ironsource.mediationsdk.sdk.BannerListener
import com.ironsource.mediationsdk.sdk.InterstitialListener
import com.ironsource.mediationsdk.sdk.OfferwallListener
import java.util.*

class IronSourceFragment : androidx.fragment.app.Fragment() {
    private var _binding: FragmentIronSourceBinding? = null
    private val binding get() = _binding!!
    private val args: IronSourceFragmentArgs by navArgs()
    private lateinit var banner: IronSourceBannerLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIronSourceBinding.inflate(inflater, container, false)

            initializeBannerView()

            banner.bannerListener = object : BannerListener {

                val params: Map<String, String> =
                    object : HashMap<String, String>() {
                        init {
                            put(Scheme.AD_TYPE, AppsFlyerAdNetworkEventType.BANNER.toString())
                            put(Scheme.AD_UNIT, IronSource.AD_UNIT.BANNER.toString())
                        }
                    }

                override fun onBannerAdLoaded() {
                    generateAdRevenueEvent(params)
                }

                override fun onBannerAdLoadFailed(p0: IronSourceError?) {
                    Log.d("ironSourceDebug", p0.toString())
                }

                override fun onBannerAdClicked() {
                    Log.d("ironSourceDebug", "banner ad clicked")
                }

                override fun onBannerAdScreenPresented() {
                    Log.d("ironSourceDebug", "banner ad screen presented")
                }

                override fun onBannerAdScreenDismissed() {
                    Log.d("ironSourceDebug", "banner ad screen dismissed")
                }

                override fun onBannerAdLeftApplication() {
                    Log.d("ironSourceDebug", "banner ad left application")
                }

            }

            IronSource.setInterstitialListener(object : InterstitialListener {
                override fun onInterstitialAdReady() {
                    val placement = IronSource.getInterstitialPlacementInfo("DefaultInterstitial")
                    IronSource.showInterstitial(placement.placementName);
                }

                override fun onInterstitialAdLoadFailed(p0: IronSourceError?) {
                    Log.d("ironSourceDebug", p0.toString())
                }

                override fun onInterstitialAdOpened() {
                    Log.d("ironSourceDebug", "Interstitial ad opened")

                }

                override fun onInterstitialAdClosed() {
                    Log.d("ironSourceDebug", "Interstitial ad closed")
                }

                override fun onInterstitialAdShowSucceeded() {
                    val params: Map<String, String> =
                        object : HashMap<String, String>() {
                            init {
                                put(
                                    Scheme.AD_TYPE,
                                    AppsFlyerAdNetworkEventType.INTERSTITIAL.toString()
                                )
                                put(Scheme.AD_UNIT, IronSource.AD_UNIT.INTERSTITIAL.toString())
                            }
                        }
                    generateAdRevenueEvent(params)
                }

                override fun onInterstitialAdShowFailed(p0: IronSourceError?) {
                    Log.d("ironSourceDebug", p0.toString())
                }

                override fun onInterstitialAdClicked() {
                    Log.d("ironSourceDebug", "Interstitial ad clicked")
                }
            })

            IronSource.setOfferwallListener(object : OfferwallListener {

                override fun onOfferwallAvailable(isAvailable: Boolean) {
                    DataProvider.isWallAvailable = true
                }

                override fun onOfferwallOpened() {
                    Log.d("ironSourceDebug", "OfferWall opened")
                }

                override fun onOfferwallShowFailed(error: IronSourceError) {
                    Log.d("ironSourceDebug", error.toString())
                }

                override fun onOfferwallAdCredited(
                    credits: Int,
                    totalCredits: Int,
                    totalCreditsFlag: Boolean
                ): Boolean {
                    return true
                }

                override fun onGetOfferwallCreditsFailed(error: IronSourceError) {
                    Log.d("ironSourceDebug", error.toString())

                }

                override fun onOfferwallClosed() {
                    Log.d("ironSourceDebug", "OfferWall closed")
                }
            })

            initializeIronSource()

        when (args.adType) {
            AdType.BANNER -> showBanner()
            AdType.INTERSTITIAL_VIDEO -> showInterstitialVideo()
            AdType.OFFER_WALL -> showOfferWall()
            else -> {
                Log.d("ironSourceDebug", "not available")
            }
        }

        val adType = args.adType.toString()
        binding.toolbar.title = adType
        binding.textView.text = adType

        binding.toolbar.setNavigationOnClickListener {
            it.findNavController().popBackStack()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPause() {
        super.onPause()
        IronSource.onPause(this.activity)
    }

    override fun onResume() {
        super.onResume()
        IronSource.onResume(this.activity)
    }

    private fun showBanner() {
        IronSource.loadBanner(banner)
    }

    private fun showInterstitialVideo() {
        IronSource.loadInterstitial()
    }

    private fun showOfferWall() {
        val params: Map<String, String> =
            object : HashMap<String, String>() {
                init {
                    put(Scheme.AD_TYPE, AppsFlyerAdNetworkEventType.REWARDED.toString())
                    put(Scheme.AD_UNIT, IronSource.AD_UNIT.OFFERWALL.toString())
                }
            }
        if (DataProvider.isWallAvailable) {
            IronSource.showOfferwall()
            generateAdRevenueEvent(params)
        }
    }

    private fun initializeBannerView() {
        val bannerContainer: FrameLayout = binding.bannerContainer
        val layoutParams: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        banner = IronSource.createBanner(activity, ISBannerSize.BANNER)
        bannerContainer.addView(banner, 0, layoutParams)
    }

    private fun initializeIronSource() {
        IronSource.init(
            activity,
            "174060a55",
            IronSource.AD_UNIT.OFFERWALL,
            IronSource.AD_UNIT.INTERSTITIAL,
            IronSource.AD_UNIT.REWARDED_VIDEO,
            IronSource.AD_UNIT.BANNER
        )
        DataProvider.shouldInitIronSource = false
    }

    private fun generateAdRevenueEvent(params: Map<String, String>) {
        AppsFlyerAdRevenue.logAdRevenue(
            "ironsource",
            MediationNetwork.ironsource,
            Currency.getInstance(Locale.US),
            0.99,
            params
        )
    }
}