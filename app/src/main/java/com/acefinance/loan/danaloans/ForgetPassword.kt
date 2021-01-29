package com.acefinance.loan.danaloans

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ForgetPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        val phone = findViewById<EditText>(R.id.phone)
        val id = findViewById<EditText>(R.id.idNo)
        val password = findViewById<EditText>(R.id.password)
        val forgot = findViewById<Button>(R.id.forgot)

        val builder: AlertDialog = AppUtils.setProgressDialog(this,"Confirming your details. Please wait")
        val interstitialAd = FacebookAds.fbInterstitial(this,getString(R.string.interstitial))

        forgot.setOnClickListener {
            builder.show()
            if (id.text.isEmpty()){
                id.error = "ID cannot be blank"
                return@setOnClickListener
            }

            if (phone.text.isEmpty()){
                phone.error = "Phone cannot be blank"
                return@setOnClickListener
            }
            if (password.text.isEmpty()) {
                password.error = "Password cannot be blank"
                return@setOnClickListener
            }

            if (id.text.toString() == SharedPrefs.read(SharedPrefs.IDNO,null) &&
                    phone.text.toString() == SharedPrefs.read(SharedPrefs.PHONE,null)){
                SharedPrefs.write(SharedPrefs.PASSWORD,password.text.toString())
                Toast.makeText(this,"Password Changed Successfully", Toast.LENGTH_LONG).show()
                Handler().postDelayed({
                    builder.dismiss()
                    FacebookAds.showInterstitial(interstitialAd)
                    startActivity(Intent(this, LoginActivity::class.java))
                },4000)
            }else{
                Toast.makeText(this,"Entered details do not match. Try again", Toast.LENGTH_LONG).show()
                builder.dismiss()
            }

        }


    }
}