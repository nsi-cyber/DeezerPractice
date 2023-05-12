package com.nsicyber.deezerpractice.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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
import com.nsicyber.deezerpractice.components.ArtistComponent
import com.nsicyber.deezerpractice.components.MusicComponent
import com.nsicyber.deezerpractice.databinding.ActivityMainBinding
import com.nsicyber.deezerpractice.models.AlbumModel
import com.nsicyber.deezerpractice.models.ArrayArtistModel
import com.nsicyber.deezerpractice.models.ArtistModel
import com.nsicyber.deezerpractice.models.MusicModel
import com.nsicyber.deezerpractice.network.RetrofitCallback
import com.nsicyber.deezerpractice.network.RetrofitClient
import com.nsicyber.deezerpractice.utils.Parser
import com.nsicyber.deezerpractice.utils.loadUrlRadius
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AlbumDetailFragment : Fragment() {

    private lateinit var collapsing_toolbar_layout: CollapsingToolbarLayout
    private lateinit var image_view: ImageView
    private lateinit var recyclerView: RecyclerView
    var adapter = GroupAdapter<ViewHolder>()
    var listData:AlbumModel?=null
    var isConfigured=false
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
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            }
            )

        // Set the title of the collapsing toolbar
        collapsing_toolbar_layout.title = model?.title

        // Load the image into the image view using Glide
        Glide
            .with(this)
            .load(model?.cover)
            .placeholder(R.drawable.music_logo)
            .error(R.drawable.music_logo)
            .into(image_view)


        recyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter

getData()


    }


    private fun configureRows(list: AlbumModel) {
        adapter.clear()
        for(i in list.tracks?.data as ArrayList<MusicModel>)
        {
            adapter.add(MusicComponent(i).apply {
                this.fragment=this@AlbumDetailFragment
            })
        }
        }



    fun getData(){

        adapter.isLoading = true
        var call = RetrofitClient.retrofitInterface(context).getAlbum(model?.id)
        call.enqueue(
            RetrofitCallback(
                this.requireContext(),
                object : Callback<AlbumModel?> {

                    override fun onResponse(
                        call: Call<AlbumModel?>,
                        response: Response<AlbumModel?>
                    ) {
                        if (response.code() == 200) {
                            isConfigured=true
                            adapter.isLoading = false
                            listData=response.body()
                            configureRows(listData!!)
                        }
                    }

                    override fun onFailure(
                        call: Call<AlbumModel?>,
                        t: Throwable
                    ) {
                        Toast.makeText(
                            this@AlbumDetailFragment.requireContext(),
                            t.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
        )
    }

    override fun onResume() {
        super.onResume()
        if(isConfigured==true)
            configureRows(listData!!)
    }



}