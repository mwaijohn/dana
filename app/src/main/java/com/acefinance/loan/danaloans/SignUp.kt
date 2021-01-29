package com.acefinance.loan.danaloans

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val fullName = findViewById<EditText>(R.id.name)
        val phone = findViewById<EditText>(R.id.phone)
        val id = findViewById<EditText>(R.id.idNo)
        val password = findViewById<EditText>(R.id.password)
        val confirmPassword = findViewById<EditText>(R.id.confirmPassword)
        val privacyPolicy = findViewById<TextView>(R.id.privacyPolicy)
        val signUp = findViewById<Button>(R.id.signUp)

        val builder: AlertDialog = AppUtils.setProgressDialog(this,"Creating your account. Hold on for a moment")

        val string = Html.fromHtml("by signing up you agree to this <b>privacy policy</b>")
        privacyPolicy.text = string

        privacyPolicy.setOnClickListener {
//            val i = Intent(Intent.ACTION_VIEW)
//            i.data = Uri.parse(getString(R.string.privacy_policy))
//            startActivity(i)
            showPolicyDialog()
        }

        signUp.setOnClickListener {

            if (fullName.text.isEmpty()){
                fullName.error = "Name cannot be blank"
                return@setOnClickListener
            }
            if (phone.text.isEmpty()){
                phone.error = "Phone cannot be blank"
                return@setOnClickListener
            }
            if (id.text.isEmpty()){
                id.error = "ID cannot be blank"
                return@setOnClickListener
            }
            if (password.text.isEmpty() || confirmPassword.text.isEmpty()){
                password.error = "Password cannot be blank"
                return@setOnClickListener
            }else{
                if (password.text.toString() != confirmPassword.text.toString()){
                    Toast.makeText(this, "Password must match", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
            }
            builder.show()

            Handler().postDelayed({

                val isConnected = AppUtils.isConnected()
                if(isConnected){
                    //save the shit
                    SharedPrefs.write(SharedPrefs.FULLNAME, fullName.text.toString())
                    SharedPrefs.write(SharedPrefs.PHONE, phone.text.toString())
                    SharedPrefs.write(SharedPrefs.IDNO, id.text.toString())
                    SharedPrefs.write(SharedPrefs.PASSWORD, password.text.toString())
                    SharedPrefs.write(SharedPrefs.IS_REGISTERED, true)

                    builder.dismiss()
                    Toast.makeText(this, "Registration Successful", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, AfterSignUpActivity::class.java))
                    finish()
                }else{
                    builder.dismiss()
                    Toast.makeText(this, "Registration failed make sure you are connected to the internet", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            },3000)
        }
    }

    private fun showPolicyDialog() {
//        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
//        val dialogView: View = LayoutInflater.from(this@SignUp).inflate(R.layout.dialogue, null)
//
//        builder.setTitle("Privacy policy")
//        builder.setView(dialogView)
//
//        builder.setNegativeButton("Close") { dialogInterface, _ -> // dismiss dialog
//            dialogInterface.dismiss()
//        }
//        builder.show()

        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.privacy_url))))

    }
}