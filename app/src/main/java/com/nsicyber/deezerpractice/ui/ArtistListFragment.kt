package com.nsicyber.deezerpractice.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.nsicyber.deezerpractice.R
import com.nsicyber.deezerpractice.models.ArtistModel
import com.nsicyber.deezerpractice.models.GenreModel
import com.nsicyber.deezerpractice.utils.Parser

/**
 * A simple [Fragment] subclass.
 * Use the [ArtistListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArtistListFragment : Fragment() {
    private lateinit var collapsing_toolbar_layout: CollapsingToolbarLayout
    private lateinit var image_view: ImageView
    var model: GenreModel? = GenreModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_artist_list, container, false)

        collapsing_toolbar_layout = view.findViewById(R.id.collapsing_toolbar_layout)
        image_view = view.findViewById(R.id.imageView)
        arguments?.let {
            model = Parser.parse(it.getSerializable("data"))
        }
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the title of the collapsing toolbar
        collapsing_toolbar_layout.title = model?.name

        // Load the image into the image view using Glide
        Glide.with(this).load(model?.picture).placeholder(R.drawable.music_logo)
            .error(R.drawable.music_logo).into(image_view)


    }

}