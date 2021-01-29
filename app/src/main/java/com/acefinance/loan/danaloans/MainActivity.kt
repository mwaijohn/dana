package com.acefinance.loan.danaloans

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import com.facebook.ads.AudienceNetworkAds

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AudienceNetworkAds.initialize(this)
        SharedPrefs.init(this)

//        val linearLayout = findViewById<LinearLayout>(R.id.banner_container)
//        val banner = FacebookAds.fbBanner(this,getString(R.string.banner))
//        linearLayout.addView(banner)

        val terms  = findViewById<TextView>(R.id.terms)
        terms.setOnClickListener {startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.privacy_url)))) }
        val next = findViewById<Button>(R.id.next)
        next.setOnClickListener {
            val builder: AlertDialog = AppUtils.setProgressDialog(this,"Checking a few things. Hold on a moment")
            val interstitialAd = FacebookAds.fbInterstitial(this,getString(R.string.interstitial))

            builder.show()
            Handler().postDelayed({
                startActivity(Intent(this,AfterLaunch::class.java))
                builder.dismiss()
                FacebookAds.showInterstitial(interstitialAd)
            },4000)
        }
        val builder: AlertDialog = AppUtils.setProgressDialog(this,"Initializing app.Please wait")
        builder.show()
        Handler().postDelayed({
            builder.dismiss()
        },4000)
    }
}