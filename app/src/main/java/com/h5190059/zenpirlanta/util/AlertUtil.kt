package com.h5190059.zenpirlanta.util

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.Drawable
import android.provider.Settings;

object AlertUtil {
    fun alertGoster(activity: Activity, style: Int, icon: Drawable?, title: CharSequence?, message: CharSequence?, negativeButton: CharSequence?, positiveButton: CharSequence?,alertSecilen: AlertSecilen) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity, style)
        builder.setIcon(icon)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setNegativeButton(negativeButton,
            DialogInterface.OnClickListener { dialog, which ->
                if (alertSecilen.equals(AlertSecilen.INTERNET)) {
                    activity.finish()
                } else {
                    activity.finish()
                }
            })
        builder.setPositiveButton(positiveButton,
            DialogInterface.OnClickListener { dialog, which ->
                if (alertSecilen.equals(AlertSecilen.INTERNET)) {
                    dialog.dismiss()
                    activity.startActivity(Intent(Settings.ACTION_SETTINGS))
                    activity.finish()
                } else {
                    dialog.dismiss()
                }
            })
        builder.show()
    }

    enum class AlertSecilen {
        INTERNET,
        CIKIS
    }
}

