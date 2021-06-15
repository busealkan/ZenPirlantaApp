package com.h5190059.zenpirlanta

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.resimCek(url: String){
    Glide.with(this.context)
        .load(url)
        .error(R.drawable.interneticon)
        .centerCrop()
        .into(this)
}

