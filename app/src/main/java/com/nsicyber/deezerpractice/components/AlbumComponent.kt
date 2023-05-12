package com.nsicyber.deezerpractice.components

import android.widget.ImageButton
import android.widget.TextView
import com.ace1ofspades.recyclerview.items.Item
import com.ace1ofspades.recyclerview.viewHolders.ViewHolder
import com.google.android.material.imageview.ShapeableImageView
import com.nsicyber.deezerpractice.R
import com.nsicyber.deezerpractice.models.AlbumModel
import com.nsicyber.deezerpractice.models.MusicModel
import com.nsicyber.deezerpractice.ui.AlbumDetailFragment
import com.nsicyber.deezerpractice.ui.ArtistListFragment
import com.nsicyber.deezerpractice.utils.Parser
import com.nsicyber.deezerpractice.utils.convertDate
import com.nsicyber.deezerpractice.utils.formatTime
import com.nsicyber.deezerpractice.utils.handleClick
import com.nsicyber.deezerpractice.utils.loadUrlRadius

class AlbumComponent(model: AlbumModel) : Item<ViewHolder, AlbumModel>(model) {

    // ViewObjects
    lateinit var image: ShapeableImageView
    lateinit var albumTitle: TextView
    lateinit var releaseDate: TextView

    override fun getLayout(): Int = layout

    override fun initialize() {
        super.initialize()
        image = findViewById(R.id.imageView)
        albumTitle = findViewById(R.id.albumText)
        releaseDate = findViewById(R.id.releaseText)

    }

    override fun configure() {
        super.configure()

        image.loadUrlRadius(model?.coverXl, 12)
        albumTitle.text = model?.title
        releaseDate.text = convertDate(model?.releaseDate)

        this.viewHolder?.itemView?.setOnClickListener {
            handleClick(this.fragment, R.id.albumDetailFragment, Parser.parse(model)!!)
        }

    }


    companion object {
         var layout = R.layout.c_album

    }
}

