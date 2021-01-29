package com.acefinance.loan.danaloans

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class ApplyLoan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_loan)

        val linearLayout = findViewById<LinearLayout>(R.id.banner_container)
        val banner = FacebookAds.fbBanner(this,getString(R.string.banner))
        linearLayout.addView(banner)

        val applied = findViewById<TextView>(R.id.applied)
        val amount = findViewById<EditText>(R.id.amount)
        val request = findViewById<Button>(R.id.requestLoan)
        val interstitialAd = FacebookAds.fbInterstitial(this,getString(R.string.interstitial))

        request.setOnClickListener {
            val builder: AlertDialog = AppUtils.setProgressDialog(this,"Submitting your application. Please wait")
            builder.show()

            if ( (amount.text.toString()).isEmpty() || (amount.text.toString()).toInt() > 25000
                    || (amount.text.toString()).toInt() < 500){
                        builder.dismiss()
                        AppUtils.toastMessage(this,"Loan amount can only be in the range Ksh 500 - 25000")
            }else{
                val isConnected = AppUtils.isConnected()
                if (isConnected){
                    Handler().postDelayed({
                        builder.dismiss()
                        SharedPrefs.write(SharedPrefs.IS_APPLIED,true)
                        SharedPrefs.write(SharedPrefs.LOAN_AMOUNT,(amount.text.toString()).toLong())
                        startActivity(Intent(this,ApplicationSuccess::class.java))
                        finish()
                        FacebookAds.showInterstitial(interstitialAd)
                    },3000)
                }else{
                    AppUtils.toastMessage(this,"Make sure you are connected to the internet")
                }
            }
        }

        applied.setOnClickListener {
            val builder: AlertDialog = AppUtils.setProgressDialog(this,"Checking loan status. Hold on a moment.")
            builder.show()
            val interstitialAd = FacebookAds.fbInterstitial(this,getString(R.string.interstitial))

          Handler().postDelayed({
              builder.dismiss()
              startActivity(Intent(this,StatusActivity::class.java))
              FacebookAds.showInterstitial(interstitialAd)
          },3000)
        }
    }
}