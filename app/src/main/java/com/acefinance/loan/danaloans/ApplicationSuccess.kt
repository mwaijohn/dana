package com.acefinance.loan.danaloans

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class ApplicationSuccess : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application_success)

        val linearLayout = findViewById<LinearLayout>(R.id.banner_container)
        val banner = FacebookAds.fbBanner(this,getString(R.string.banner))
        linearLayout.addView(banner)

        val share = findViewById<Button>(R.id.share)
        share.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND;
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Download ${getString(R.string.app_name)} to receive instant" +
                    " loans with the market competitive interest rates. Download from Google Playstore ${"https://play.google.com/store/apps/details?id=$packageName"}")
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }
        val rate = findViewById<Button>(R.id.rateUs)
        rate.setOnClickListener {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
            } catch (e: ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
            }
        }
    }
}