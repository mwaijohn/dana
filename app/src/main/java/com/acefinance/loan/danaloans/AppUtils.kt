package com.acefinance.loan.danaloans

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.*
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import java.io.IOException

class AppUtils {
    companion object{
        @Throws(InterruptedException::class, IOException::class)
        fun isConnected(): Boolean {
            val command = "ping -c 1 google.com"
            return Runtime.getRuntime().exec(command).waitFor() == 0
        }

        fun toastMessage(context: Context?,message: String){
            Toast.makeText(context,message,Toast.LENGTH_LONG).show()
        }

        fun aprDialog(context: Context?): AlertDialog.Builder{
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            val dialogView: View = LayoutInflater.from(context).inflate(R.layout.dialogue, null)

            val continueApplication = dialogView.findViewById<TextView>(R.id.continue_application)
            continueApplication.setOnClickListener {
                context?.startActivity(Intent(context, LoginActivity::class.java))
            }

            builder.setTitle("APR Calculation")
            builder.setView(dialogView)

//            builder.setNegativeButton("close") { dialogInterface, _ -> // dismiss dialog
//                context?.startActivity(Intent(context,LoginActivity::class.java))
//                dialogInterface.dismiss()
//            }
            //builder.show()
            return builder
        }

        fun setProgressDialog(context: Context?,massage: String): AlertDialog {

            val llPadding = 30
            val ll = LinearLayout(context)
            ll.orientation = LinearLayout.HORIZONTAL
            ll.setPadding(llPadding, llPadding, llPadding, llPadding)
            ll.gravity = Gravity.CENTER
            var llParam = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            llParam.gravity = Gravity.CENTER
            ll.layoutParams = llParam
            val progressBar = ProgressBar(context)
            progressBar.isIndeterminate = true
            progressBar.setPadding(0, 0, llPadding, 0)
            progressBar.layoutParams = llParam
            llParam = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            llParam.gravity = Gravity.CENTER
            val tvText = TextView(context)
            tvText.text = massage
            //tvText.setTextColor(Color.parseColor("#0288d1"))
            //tvText.textSize = 20f
            tvText.layoutParams = llParam
            ll.addView(progressBar)
            ll.addView(tvText)
            val builder = AlertDialog.Builder(context)
            builder.setCancelable(true)
            builder.setView(ll)
            val dialog = builder.create()
            //dialog.show();
            val window = dialog.window
            if (window != null) {
                val layoutParams = WindowManager.LayoutParams()
                layoutParams.copyFrom(dialog.window!!.attributes)
                layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
                layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
                dialog.window!!.attributes = layoutParams
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                )
            }
            return dialog
        }
    }
}