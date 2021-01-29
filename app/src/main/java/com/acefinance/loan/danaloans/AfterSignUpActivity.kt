package com.acefinance.loan.danaloans

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button

class AfterSignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thank_you)

        val limit = findViewById<Button>(R.id.loanLimit)
        val proceed = findViewById<Button>(R.id.proceedLoan)

        limit.setOnClickListener {
            val interstitialAd = FacebookAds.fbInterstitial(this,getString(R.string.interstitial))
            val builder: AlertDialog = AppUtils.setProgressDialog(this,"Assessing your loan limit.Please wait")
            builder.show()

            Handler().postDelayed({
                builder.dismiss()
                startActivity(Intent(this,LoanLimitActivity::class.java))
                finish()
                FacebookAds.showInterstitial(interstitialAd)
            },3000)
        }


        proceed.setOnClickListener {
            val builder: AlertDialog = AppUtils.setProgressDialog(this,"Checking your CRB credit score. Please wait")
            builder.show()
            val interstitialAd = FacebookAds.fbInterstitial(this,getString(R.string.interstitial))

            Handler().postDelayed({
                builder.dismiss()
                startActivity(Intent(this,ApplyLoan::class.java))
                finish()
                FacebookAds.showInterstitial(interstitialAd)

            },3000)

        }
    }
}