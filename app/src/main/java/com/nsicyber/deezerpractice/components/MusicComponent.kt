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
import com.nsicyber.deezerpractice.R
import com.nsicyber.deezerpractice.models.MusicModel
import com.nsicyber.deezerpractice.utils.formatTime
import com.nsicyber.deezerpractice.utils.loadUrlRadius

class MusicComponent(model: MusicModel) : Item<ViewHolder, MusicModel>(model) {

    // ViewObjects
    lateinit var image: ShapeableImageView
    lateinit var musicTitle: TextView
    lateinit var albumTitle: TextView
    lateinit var artistTitle: TextView
    lateinit var length: TextView
    lateinit var likeButton: ImageButton

    override fun getLayout(): Int = layout

    override fun initialize() {
        super.initialize()
        image = findViewById(R.id.imageView)
        musicTitle = findViewById(R.id.musicText)
        albumTitle = findViewById(R.id.albumText)
        artistTitle = findViewById(R.id.artistText)
        length = findViewById(R.id.lengthText)
        likeButton = findViewById(R.id.likeButton)
    }

    override fun configure() {
        super.configure()

        image.loadUrlRadius(model?.album?.cover, 12)
        musicTitle.text = model?.title
        albumTitle.text = model?.album?.title
        artistTitle.text = model?.artist?.name
        length.text = formatTime(model?.duration)
    }


    companion object {
         var layout = R.layout.c_music

    }
}

