package com.nsicyber.deezerpractice.utils

import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.nsicyber.deezerpractice.R
import com.nsicyber.deezerpractice.enums.Fragments
import java.text.SimpleDateFormat
import java.util.Locale

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
    var seconds = input?.toInt()
    val minutes = seconds?.div(60)
    val remainingSeconds = seconds?.rem(60)
    return "$minutes:${String.format("%02d", remainingSeconds)}"
}

fun handleClick(fragment: Fragment?, destinationFragment: Int, data: Map<*,*>) {
    val navController = fragment?.findNavController()

    val navOpt = navOptions {
        anim {
            exit = R.anim.slide_out
            enter = R.anim.slide_in
            popExit = R.anim.slide_pop_out
            popEnter = R.anim.slide_pop_in
        }

    }

    val bundle = Bundle()


    data?.let {

        var hashMap = hashMapOf<String, Any>()
        for (i in it.keys) {
            it[i]?.let { it1 ->
                hashMap[i.toString()] = it1
            }
        }
        bundle.putSerializable("data", hashMap)

        navController?.navigate(
        destinationFragment,
            bundle,
            navOpt
        )
        return
    }

    navController?.navigate(
       destinationFragment,
        null,
        navOpt
    )

}

fun convertDate(dateString: String?): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("d MMMM, yyyy", Locale.getDefault())
    val date = inputFormat.parse(dateString)
    return outputFormat.format(date)
}