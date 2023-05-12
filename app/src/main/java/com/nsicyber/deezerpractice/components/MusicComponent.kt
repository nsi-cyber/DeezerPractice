package com.nsicyber.deezerpractice.components

import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ace1ofspades.recyclerview.GroupAdapter
import com.ace1ofspades.recyclerview.items.Item
import com.ace1ofspades.recyclerview.viewHolders.ViewHolder
import com.google.android.material.imageview.ShapeableImageView
import com.nsicyber.deezerpractice.PreferencesHelper
import com.nsicyber.deezerpractice.R
import com.nsicyber.deezerpractice.dialogs.PlaySongDialog
import com.nsicyber.deezerpractice.models.MusicModel
import com.nsicyber.deezerpractice.utils.formatTime
import com.nsicyber.deezerpractice.utils.loadUrlRadius

class MusicComponent(model: MusicModel) : Item<ViewHolder, MusicModel>(model) {

    // ViewObjects
    lateinit var image: ShapeableImageView
    lateinit var musicTitle: TextView
    lateinit var artistTitle: TextView
    lateinit var length: TextView
    lateinit var likeButton: ImageButton

    override fun getLayout(): Int = layout

    override fun initialize() {
        super.initialize()
        image = findViewById(R.id.imageView)
        musicTitle = findViewById(R.id.musicText)
        artistTitle = findViewById(R.id.artistText)
        length = findViewById(R.id.lengthText)
        likeButton = findViewById(R.id.likeButton)
    }

    override fun configure() {
        super.configure()
        var isLiked = false
        image.loadUrlRadius(model?.album?.cover, 12)
        musicTitle.text = model?.title
        artistTitle.text = model?.artist?.name
        length.text = formatTime(model?.duration)
        PreferencesHelper(context!!).likedMusics?.forEach {
            if (model?.id == it.id) {
                likeButton.setImageResource(R.drawable.ic_heart_filled)
                isLiked = true
            }
        }

        likeButton.setOnClickListener {
            if (isLiked) {
                println(PreferencesHelper(context!!).likedMusics)
var i=0
                PreferencesHelper(context!!).likedMusics?.forEach {
                    if (model?.id == it.id) {
                        likeButton.setImageResource(R.drawable.ic_heart)
                        isLiked = false

                        var arr= PreferencesHelper(context!!).likedMusics
                        arr!!.removeAt(i)
                        PreferencesHelper(context!!).likedMusics=arr
                    }
                    i++
                }
println(this.fragment!!::class.java.simpleName.toString())
               if(this.fragment!!::class.java.simpleName.toString()=="LikesFragment")
                   baseAdapter?.remove(this)




            } else {
                var arr= PreferencesHelper(context!!).likedMusics

                arr!!.add(model!!)

                    PreferencesHelper(context!!).likedMusics=arr
                println(PreferencesHelper(context!!).likedMusics)
                likeButton.setImageResource(R.drawable.ic_heart_filled)
                isLiked = true
            }
        }

this.itemView?.setOnClickListener {
    PlaySongDialog().start(context!!,
        this@MusicComponent.fragment!!
            .requireActivity(),model)
    println("")
}

    }


    companion object {
        var layout = R.layout.c_music

    }
}

