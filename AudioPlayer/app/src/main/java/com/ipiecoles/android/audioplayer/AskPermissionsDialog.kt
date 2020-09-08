package com.ipiecoles.android.audioplayer

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class AskPermissionsDialog(
    private val requestPerm: () -> Unit
): DialogFragment() {
    override fun onCreateDialog(
        savedInstanceState: Bundle?
    ): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle("Permission requise")
            builder.setMessage("Nécessaire pour récupérer les musiques dans la mémoire du téléphone")
            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                Toast.makeText(activity,
                    android.R.string.yes, Toast.LENGTH_SHORT).show()
                    requestPerm()
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->
                Toast.makeText(activity,
                    android.R.string.no, Toast.LENGTH_SHORT).show()
            }
            builder.show()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}