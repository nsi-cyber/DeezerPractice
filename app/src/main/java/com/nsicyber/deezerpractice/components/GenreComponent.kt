package com.nsicyber.deezerpractice.components

import android.widget.TextView
import com.ace1ofspades.recyclerview.items.Item
import com.ace1ofspades.recyclerview.viewHolders.ViewHolder
import com.google.android.material.imageview.ShapeableImageView
import com.nsicyber.deezerpractice.R
import com.nsicyber.deezerpractice.models.GenreModel
import com.nsicyber.deezerpractice.ui.ArtistListFragment
import com.nsicyber.deezerpractice.utils.Parser
import com.nsicyber.deezerpractice.utils.handleClick
import com.nsicyber.deezerpractice.utils.loadUrlRadius

class GenreComponent (model: GenreModel) : Item<ViewHolder, GenreModel>(model) {

    // ViewObjects
    lateinit var image: ShapeableImageView
    lateinit var genreText: TextView

    override fun getLayout(): Int = layout

    override fun initialize() {
        super.initialize()
        image = findViewById(R.id.imageView)
        genreText = findViewById(R.id.genreText)

    }

    override fun configure() {
        super.configure()

        image.loadUrlRadius(model?.picture, 12)
        genreText.text = model?.name
        this.viewHolder?.itemView?.setOnClickListener {
            handleClick(this.fragment,R.id.artistListFragment, Parser.parse(model)!!)
        }
    }


    companion object {
         var layout = R.layout.c_genre

    }
}

