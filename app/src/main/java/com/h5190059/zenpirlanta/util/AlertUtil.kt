package com.h5190059.zenpirlanta.util

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.Drawable
import android.provider.Settings
import com.h5190059.zenpirlanta.ItemClickListener


object AlertUtil {
    fun alertDialogGoster(activity: Activity, style: Int, icon: Drawable?, title: CharSequence?, message: CharSequence?, negativeButton: CharSequence?, positiveButton: CharSequence?,alertSecilen: AlertDialogSecilen) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity, style)
        builder.setIcon(icon)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setNegativeButton(negativeButton,
            DialogInterface.OnClickListener { dialog, which ->
                if (alertSecilen.equals(AlertDialogSecilen.INTERNET)) {
                    activity.finish()
                } else {
                    activity.finish()
                }
            })
        builder.setPositiveButton(positiveButton,
            DialogInterface.OnClickListener { dialog, which ->
                if (alertSecilen.equals(AlertDialogSecilen.INTERNET)) {
                    dialog.dismiss()
                    activity.startActivity(Intent(Settings.ACTION_SETTINGS))
                    activity.finish()
                } else {
                    dialog.dismiss()
                }
            })
        builder.show()
    }


    fun alertListGoster(activity: Activity, title: String,listSiralama:Array<String>,callback: ItemClickListener) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        builder.setTitle(title)


        builder.setItems(listSiralama) { dialog, pozisyon ->
            callback.onItemClick(pozisyon)
        }


        val dialog = builder.create()
        dialog.show()
    }



    enum class AlertDialogSecilen {
        INTERNET,
        CIKIS
    }

    enum class AlertListDialogSecilen(val gelenDeger:Int){
        ARTAN_SIRALAMA (Constants.ARTAN_SIRALAMA),
        AZALAN_SIRALAMA (Constants.AZALAN_SIRALAMA)
    }
}




