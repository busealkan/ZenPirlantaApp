package com.h5190059.zenpirlanta.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo;



object NetworkUtil {
    fun internetVarMi(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        return if (networkInfo != null && networkInfo.isAvailable && networkInfo.isConnected) {
            true
        } else {
            false
        }
    }
}