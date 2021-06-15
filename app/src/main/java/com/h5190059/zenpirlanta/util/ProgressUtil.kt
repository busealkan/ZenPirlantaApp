package com.h5190059.zenpirlanta.util

import android.app.ProgressDialog
import android.content.Context

object ProgressUtil {
    fun progressDialogOlustur(
        context: Context?,
        message: CharSequence?
    ): ProgressDialog? {
        val progressDialog = ProgressDialog(context)
        progressDialog.setMessage(message)
        progressDialog.show()
        return progressDialog
    }

    fun progressDialogKapat(progressDialog: ProgressDialog?) {
        if (progressDialog != null && progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }
}