package com.nsicyber.deezerpractice.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ace1ofspades.recyclerview.GroupAdapter
import com.ace1ofspades.recyclerview.viewHolders.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.nsicyber.deezerpractice.R
import com.nsicyber.deezerpractice.components.MusicComponent
import com.nsicyber.deezerpractice.databinding.ActivityMainBinding
import com.nsicyber.deezerpractice.models.AlbumModel
import com.nsicyber.deezerpractice.models.MusicModel
import com.nsicyber.deezerpractice.utils.Parser
import com.nsicyber.deezerpractice.utils.loadUrlRadius

/**
 * A simple [Fragment] subclass.
 * Use the [AlbumDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class AlbumDetailFragment : Fragment() {
    private lateinit var collapsing_toolbar_layout: CollapsingToolbarLayout
    private lateinit var image_view: ImageView
    private lateinit var recyclerView: RecyclerView
    var adapter = GroupAdapter<ViewHolder>()

    var model: AlbumModel? = AlbumModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_album_detail, container, false)

        collapsing_toolbar_layout = view.findViewById(R.id.collapsing_toolbar_layout)
        image_view = view.findViewById(R.id.imageView)
        recyclerView = view.findViewById(R.id.recyclerView)

        arguments?.let {
            model = Parser.parse(it.getSerializable("data"))
        }
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the title of the collapsing toolbar
        collapsing_toolbar_layout.title = model?.title

        // Load the image into the image view using Glide
        Glide.with(this).load(model?.cover).placeholder(R.drawable.music_logo)
            .error(R.drawable.music_logo).into(image_view)


        recyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
        for(i in model?.tracks as ArrayList<MusicModel>)
        {
            adapter.add(MusicComponent(i))
        }


    }

}