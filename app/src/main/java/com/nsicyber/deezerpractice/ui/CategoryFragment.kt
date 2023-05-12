package com.nsicyber.deezerpractice.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ace1ofspades.recyclerview.GroupAdapter
import com.ace1ofspades.recyclerview.scrollListeners.ItemOffsetDecoration
import com.ace1ofspades.recyclerview.viewHolders.ViewHolder
import com.nsicyber.deezerpractice.R
import com.nsicyber.deezerpractice.components.GenreComponent
import com.nsicyber.deezerpractice.models.ArrayGenreModel
import com.nsicyber.deezerpractice.models.GenreModel
import com.nsicyber.deezerpractice.network.RetrofitCallback
import com.nsicyber.deezerpractice.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CategoryFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    var adapter = GroupAdapter<ViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =inflater.inflate(R.layout.fragment_category, container, false)
        recyclerView=view.findViewById(R.id.recyclerView)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager =
            GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(ItemOffsetDecoration(14))

        getData()
    }

    private fun configureRows(list: List<GenreModel>) {
        adapter.clear()
        for (i in list) {
            adapter.add(GenreComponent(i).apply {
                this.fragment=this@CategoryFragment
            })
        }
    }

    private fun getData() {
        adapter.isLoading = true
        var call = RetrofitClient.retrofitInterface(context).getAllGenres()
        call.enqueue(
            RetrofitCallback(
                this.requireContext(),
                object : Callback<ArrayGenreModel?> {

                    override fun onResponse(
                        call: Call<ArrayGenreModel?>,
                        response: Response<ArrayGenreModel?>
                    ) {
                        if (response.code() == 200) {
                            adapter.isLoading = false
                            configureRows(response.body()?.data as List<GenreModel>)
                        }
                    }

                    override fun onFailure(
                        call: Call<ArrayGenreModel?>,
                        t: Throwable
                    ) {
                        Toast.makeText(
                            this@CategoryFragment.requireContext(),
                            t.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
        )
    }


}