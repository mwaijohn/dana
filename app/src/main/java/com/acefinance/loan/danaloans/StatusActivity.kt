package com.acefinance.loan.danaloans

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class StatusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)

        val linearLayout = findViewById<LinearLayout>(R.id.banner_container)
        val banner = FacebookAds.fbBanner(this,getString(R.string.banner))
        linearLayout.addView(banner)

        val statusText = findViewById<TextView>(R.id.status)
        val logout = findViewById<Button>(R.id.logout)
        val isApplied = SharedPrefs.read(SharedPrefs.IS_APPLIED,false)
        if (isApplied){
            statusText.text = getString(R.string.applied_already)
        }else{
            statusText.text = getString(R.string.not_applied)
        }

        logout.setOnClickListener { finish() }
    }
}