package com.example.filmcatalog.ui.base

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.DialogFragment
import android.widget.LinearLayout




class BaseAlertDialog(private val message: String) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity).create()
        builder.setMessage(message)
        builder.setCanceledOnTouchOutside(false)

        val progressBar = ProgressBar(context)
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        progressBar.layoutParams = lp
        builder.setView(progressBar)

        return builder
    }
}