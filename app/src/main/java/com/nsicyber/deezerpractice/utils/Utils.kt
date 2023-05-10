package com.nsicyber.deezerpractice.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.nsicyber.deezerpractice.R

class Utils {
}

fun ImageView.loadUrlRadius(url: String?, radius: Int) {


    if (url != null) {
        Glide.with(this.rootView)
            .load(url)
            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA))
            .apply(RequestOptions.bitmapTransform(RoundedCorners(radius)))
            .apply(RequestOptions.centerCropTransform())
            .placeholder(R.drawable.music_logo)
            .error(R.drawable.music_logo)
            .into(this)
    }
}

fun formatTime(input: String?): String {
    var seconds=input?.toInt()
    val minutes = seconds?.div(60)
    val remainingSeconds = seconds?.rem(60)
    return "$minutes:${String.format("%02d", remainingSeconds)}"
}
