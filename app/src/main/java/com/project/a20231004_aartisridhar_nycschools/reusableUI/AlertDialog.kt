package com.project.a20231004_aartisridhar_nycschools.reusableUI

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getString
import com.project.a20231004_aartisridhar_nycschools.R

//Handles displaying any error or informational popups to the user
class AlertDialog(private val context: Context) {
    fun onError(message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(getString(context, R.string.app_name))
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
}