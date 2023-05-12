package com.nsicyber.deezerpractice.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ace1ofspades.recyclerview.GroupAdapter
import com.ace1ofspades.recyclerview.scrollListeners.ItemOffsetDecoration
import com.ace1ofspades.recyclerview.viewHolders.ViewHolder
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.nsicyber.deezerpractice.R
import com.nsicyber.deezerpractice.components.AlbumComponent
import com.nsicyber.deezerpractice.components.ArtistComponent
import com.nsicyber.deezerpractice.components.GenreComponent
import com.nsicyber.deezerpractice.models.AlbumModel
import com.nsicyber.deezerpractice.models.ArrayAlbumModel
import com.nsicyber.deezerpractice.models.ArrayArtistModel
import com.nsicyber.deezerpractice.models.ArrayGenreModel
import com.nsicyber.deezerpractice.models.ArtistModel
import com.nsicyber.deezerpractice.models.GenreModel
import com.nsicyber.deezerpractice.network.RetrofitCallback
import com.nsicyber.deezerpractice.network.RetrofitClient
import com.nsicyber.deezerpractice.utils.Parser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class ArtistListFragment : Fragment() {
    private lateinit var collapsing_toolbar_layout: CollapsingToolbarLayout
    private lateinit var image_view: ImageView
    lateinit var recyclerView: RecyclerView
    var adapter = GroupAdapter<ViewHolder>()
    var model: GenreModel? = GenreModel()
    var listData:List<ArtistModel>?=null
    var isConfigured=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_artist_list, container, false)

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
        collapsing_toolbar_layout.title = model?.name

        recyclerView.layoutManager =
            GridLayoutManager(context, 2,RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(ItemOffsetDecoration(14))

        // Load the image into the image view using Glide
        Glide.with(this).load(model?.picture).placeholder(R.drawable.music_logo)
            .error(R.drawable.music_logo).into(image_view)

        getData()

    }

    private fun configureRows(list: List<ArtistModel>) {
        adapter.clear()
        for (i in list) {
            adapter.add(ArtistComponent(i).apply {
                this.fragment=this@ArtistListFragment
            })
        }
    }


    fun getData(){

        adapter.isLoading = true
        var call = RetrofitClient.retrofitInterface(context).getGenreArtists(model?.id?.toInt())
        call.enqueue(
            RetrofitCallback(
                this.requireContext(),
                object : Callback<ArrayArtistModel?> {

                    override fun onResponse(
                        call: Call<ArrayArtistModel?>,
                        response: Response<ArrayArtistModel?>
                    ) {
                        if (response.code() == 200) {
                            isConfigured=true
                            adapter.isLoading = false
                            listData=response.body()?.data as List<ArtistModel>
                            configureRows(listData!!)
                        }
                    }

                    override fun onFailure(
                        call: Call<ArrayArtistModel?>,
                        t: Throwable
                    ) {
                        Toast.makeText(
                            this@ArtistListFragment.requireContext(),
                            t.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
        )
    }



}