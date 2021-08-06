package com.acefinance.loan.danaloans

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import java.lang.Exception

class AfterLaunch : AppCompatActivity() {
    //lateinit var dialogue: AlertDialog.Builder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_launch)

        val checkBox: CheckBox = findViewById(R.id.checkBox)

        val linearLayout = findViewById<LinearLayout>(R.id.banner_container)
        val banner = FacebookAds.fbBanner(this,getString(R.string.banner))
        linearLayout.addView(banner)

        val terms  = findViewById<TextView>(R.id.terms)
        terms.setOnClickListener {startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.privacy_url)))) }

        val policy  = findViewById<TextView>(R.id.privacyPolicy)
        policy.setOnClickListener {startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.privacy_url)))) }
        val next = findViewById<Button>(R.id.next)
        next.setOnClickListener {
            //dialogue = AppUtils.aprDialog(this)
            try {
                if (checkBox.isChecked){
                    //dialogue.show()
                    startActivity(Intent(this, LoginActivity::class.java))
                }else{
                    AppUtils.toastMessage(this,"Agree to our privacy policy")
                }
            }catch (ex: Exception){
                print(ex)
            }
        }
    }
}