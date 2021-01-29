package com.acefinance.loan.danaloans

import android.content.Context
import android.util.Log
import com.facebook.ads.*

class FacebookAds{
    companion object{

        private val TAG: String = FacebookAds::class.java.simpleName

        fun fbBanner(context: Context, addId: String): AdView{
            val adView = AdView(context, addId, AdSize.BANNER_HEIGHT_50)
            adView.loadAd()
            return adView
        }

        fun fbInterstitial(context: Context, addId: String): InterstitialAd{
            val interstitialAd = InterstitialAd(context, addId)
            // Create listeners for the Interstitial Ad
            // Create listeners for the Interstitial Ad
            val interstitialAdListener: InterstitialAdListener = object : InterstitialAdListener {
                override fun onInterstitialDisplayed(ad: Ad?) {
                    // Interstitial ad displayed callback
                    Log.e(TAG, "Interstitial ad displayed.")
                }

                override fun onInterstitialDismissed(ad: Ad?) {
                    // Interstitial dismissed callback
                    Log.e(TAG, "Interstitial ad dismissed.")
                }

                override fun onError(ad: Ad?, adError: AdError) {
                    // Ad error callback
                    Log.e(TAG, "Interstitial ad failed to load: " + adError.errorMessage)
                }

                override fun onAdLoaded(ad: Ad?) {
                    // Interstitial ad is loaded and ready to be displayed
                    Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!")
                    // Show the ad
                    // interstitialAd.show()
                }

                override fun onAdClicked(ad: Ad?) {
                    // Ad clicked callback
                    Log.d(TAG, "Interstitial ad clicked!")
                }

                override fun onLoggingImpression(ad: Ad?) {
                    // Ad impression logged callback
                    Log.d(TAG, "Interstitial ad impression logged!")
                }
            }

            interstitialAd.loadAd(
                interstitialAd.buildLoadAdConfig()
                    .withAdListener(interstitialAdListener)
                    .build()
            )

            return interstitialAd
        }

        fun showInterstitial(interstitialAd: InterstitialAd?){
            if(interstitialAd == null || !interstitialAd.isAdLoaded) {
                return
            }
            // Check if ad is already expired or invalidated, and do not show ad if that is the case. You will not get paid to show an invalidated ad.
            if(interstitialAd.isAdInvalidated) {
                return
            }
            // Show the ad
            interstitialAd.show()
        }

    }
}