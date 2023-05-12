package com.nsicyber.deezerpractice.components

import android.widget.TextView
import com.ace1ofspades.recyclerview.items.Item
import com.ace1ofspades.recyclerview.viewHolders.ViewHolder
import com.google.android.material.imageview.ShapeableImageView
import com.nsicyber.deezerpractice.R
import com.nsicyber.deezerpractice.models.ArtistModel
import com.nsicyber.deezerpractice.utils.Parser
import com.nsicyber.deezerpractice.utils.handleClick
import com.nsicyber.deezerpractice.utils.loadUrlRadius

class ArtistComponent(model: ArtistModel) : Item<ViewHolder, ArtistModel>(model) {

    // ViewObjects
    private lateinit var image: ShapeableImageView
    private lateinit var artistText: TextView

    override fun getLayout(): Int = layout

    override fun initialize() {
        super.initialize()
        image = findViewById(R.id.imageView)
        artistText = findViewById(R.id.artistText)

    }

    override fun configure() {
        super.configure()

        image.loadUrlRadius(model?.pictureXl, 12)
        artistText.text = model?.name
        this.viewHolder?.itemView?.setOnClickListener {
            handleClick(this.fragment, R.id.artistDetailFragment, Parser.parse(model)!!)
        }
    }


    companion object {
         var layout = R.layout.c_artist

    }
}

