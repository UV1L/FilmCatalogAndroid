package com.example.filmcatalog.utils

import android.content.Context
import android.preference.PreferenceManager

import android.content.SharedPreferences
import com.example.filmcatalog.BuildConfig
import dagger.Component


class PrefUtils(prefs: SharedPreferences)  {

    val prefs = prefs

    fun getString(key: String?, defValue: String?): String? {
        return prefs!!.getString(key, defValue)
    }

    fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return prefs!!.getBoolean(key, defValue)
    }

    fun getLong(key: String?, defValue: Long): Long {
        return prefs!!.getLong(key, defValue)
    }

    fun getInt(key: String?, defValue: Int): Int {
        return prefs.getInt(key, defValue)
    }

    fun getDouble(key: String?, defValue: Double): Double {
        return java.lang.Double.longBitsToDouble(
            getLong(
                key,
                java.lang.Double.doubleToLongBits(defValue)
            )
        )
    }

    fun getDouble(key: String?, defValue: Long): Double {
        return java.lang.Double.longBitsToDouble(getLong(key, defValue))
    }

    fun putString(key: String?, value: String?) {
        prefs!!.edit().putString(key, value).apply()
    }

    fun putBoolean(key: String?, value: Boolean) {
        prefs!!.edit().putBoolean(key, value).apply()
    }

    fun putInt(key: String?, value: Int) {
        prefs!!.edit().putInt(key, value).apply()
    }

    fun putLong(key: String?, value: Long) {
        prefs!!.edit().putLong(key, value).apply()
    }

    fun putDouble(key: String?, value: Double) {
        putLong(key, java.lang.Double.doubleToLongBits(value))
    }

    fun clear() {
        prefs!!.edit().clear().apply()
    }

    fun getKey(key: String?): String {
        return java.lang.String.format("%s.%s", BuildConfig.APPLICATION_ID, key)
    }

    companion object {

        fun create(context: Context): PrefUtils {
            return PrefUtils(context.getSharedPreferences("userPrefs", 0))
        }
    }
}