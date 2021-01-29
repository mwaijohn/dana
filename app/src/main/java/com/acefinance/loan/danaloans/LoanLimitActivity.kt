package com.acefinance.loan.danaloans

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.LinearLayout

class LoanLimitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_limit)

        val linearLayout = findViewById<LinearLayout>(R.id.banner_container)
        val banner = FacebookAds.fbBanner(this,getString(R.string.banner))
        linearLayout.addView(banner)

        val apply = findViewById<Button>(R.id.apply)
        apply.setOnClickListener {
            val builder: AlertDialog = AppUtils.setProgressDialog(this,"Getting credibility report. Hold on...")
            builder.show()
            Handler().postDelayed({
                builder.dismiss()
                startActivity(Intent(this,ApplyLoan::class.java))
            },3000)

        }
    }
}