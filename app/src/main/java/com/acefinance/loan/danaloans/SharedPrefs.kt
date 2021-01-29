package com.acefinance.loan.danaloans

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class SharedPrefs {
    companion object{

        var mSharedPref: SharedPreferences? = null

        const val FULLNAME = "full_name"
        const val PHONE = "phone"
        const val IDNO = "id"
        const val PASSWORD = "password"
        const val IS_REGISTERED = "is_registered"
        const val LOAN_AMOUNT = "loan_amount"
        const val IS_APPLIED = "is_applied"

        fun init(context: Context) {
            if (mSharedPref == null) mSharedPref = context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
        }

        fun read(key: String?, defValue: String?): String? {
            return mSharedPref?.getString(key, defValue)
        }

        fun read(key: String?,defValue: Boolean): Boolean {
            return mSharedPref?.getBoolean(key, defValue)!!
        }

        fun read(key: String?,defValue: Long): Long {
            return mSharedPref?.getLong(key, defValue)!!
        }

        fun write(key: String?, value: String?) {
            val prefsEditor = mSharedPref?.edit()
            prefsEditor?.putString(key, value)
            prefsEditor?.apply()
        }

        fun write(key: String?, value: Boolean) {
            val prefsEditor = mSharedPref?.edit()
            prefsEditor?.putBoolean(key, value)
            prefsEditor?.apply()
        }

        fun write(key: String?, value: Long) {
            val prefsEditor = mSharedPref?.edit()
            prefsEditor?.putLong(key, value)
            prefsEditor?.apply()
        }
    }
}