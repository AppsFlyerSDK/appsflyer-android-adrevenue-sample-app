package com.appsflyer.af_adrevenue.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.appsflyer.af_adrevenue.databinding.FragmentIronSourceBinding
import com.appsflyer.af_adrevenue.enums.AdType
import com.google.android.gms.ads.*

class IronSourceFragment : Fragment() {
    private var _binding: FragmentIronSourceBinding? = null
    private val binding get() = _binding!!
    private val args: IronSourceFragmentArgs by navArgs()
    private var mAdView: AdView? = null
    private var adRequest = AdRequest.Builder().build()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIronSourceBinding.inflate(inflater, container, false)
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

        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showBanner() {
    }

    private fun showInterstitial() {
    }

    private fun showInterstitialVideo() {
    }

    private fun showRewarded() {
    }

    private fun showRewardedInterstitial() {
    }

    fun showNativeAd() {
    }
}