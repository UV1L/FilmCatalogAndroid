package com.example.utils

import android.content.Context
import android.widget.Toast

object ToastUtils {

    private var toast: Toast? = null

    fun showShortMessage(context: Context?, message: String?) {
        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast!!.show()
    }

    fun showLongMessage(context: Context?, message: String?) {
        toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
        toast!!.show()
    }

    fun showShortMessage(context: Context?, resId: Int) {
        toast = Toast.makeText(context, resId, Toast.LENGTH_SHORT)
        toast!!.show()
    }

    fun showLongMessage(context: Context?, resId: Int) {
        toast = Toast.makeText(context, resId, Toast.LENGTH_LONG)
        toast!!.show()
    }
}