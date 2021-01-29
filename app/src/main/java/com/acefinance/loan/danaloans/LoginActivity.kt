package com.acefinance.loan.danaloans

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kobakei.ratethisapp.RateThisApp

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        SharedPrefs.init(this)
        //set rating dialog
        val config = RateThisApp.Config(2, 5)
        RateThisApp.init(config)
        RateThisApp.onCreate(this)
        RateThisApp.showRateDialogIfNeeded(this)

        val isRegistered = SharedPrefs.read(SharedPrefs.IS_REGISTERED, false)
        val password = findViewById<EditText>(R.id.password)
        val phone = findViewById<EditText>(R.id.phone)
        val login = findViewById<Button>(R.id.login)
        val forgotPassword = findViewById<TextView>(R.id.forgotPassword)
        val privacyPolicy = findViewById<TextView>(R.id.privacyPolicy)

        val builder: AlertDialog = AppUtils.setProgressDialog(this,"Login you in. Hold on for a moment")
        val interstitialAd = FacebookAds.fbInterstitial(this,getString(R.string.interstitial))

        forgotPassword.setOnClickListener {
            if (isRegistered){
                startActivity(Intent(this, ForgetPassword::class.java))
            }else{
                Toast.makeText(this, "Create account to login", Toast.LENGTH_LONG).show()
            }
        }

        login.setOnClickListener {
            builder.show()

            if (password.text.isEmpty()){
                Toast.makeText(this, "Enter your password", Toast.LENGTH_LONG).show()
                builder.dismiss()
                return@setOnClickListener
            }


            if (isRegistered){

                Handler().postDelayed({
                    val isConnected = AppUtils.isConnected()

                    if(isConnected){
                        if ((password.text.toString() == SharedPrefs.read(SharedPrefs.PASSWORD, null))
                                && phone.text.toString() == SharedPrefs.read(SharedPrefs.PHONE,null)){
                            startActivity(Intent(this, ApplyLoan::class.java))
                            finish()
                            FacebookAds.showInterstitial(interstitialAd)
                        }else{
                            Toast.makeText(this, "The entered password is wrong", Toast.LENGTH_LONG).show()
                        }
                        builder.dismiss()
                    }else{
                        AppUtils.toastMessage(this,"Make sure you are connected to the internet")
                        builder.dismiss()
                    }
                 },3000)
            }else{
                builder.dismiss()
                Toast.makeText(this, "Create account to login", Toast.LENGTH_LONG).show()
            }
        }

        val signUp = findViewById<Button>(R.id.signUp)
        signUp.setOnClickListener {

            startActivity(Intent(this, SignUp::class.java))

        }

        privacyPolicy.setOnClickListener {
            showPolicyDialog()
        }
    }

    private fun showPolicyDialog() {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.privacy_url))))
    }
}