package com.nsicyber.deezerpractice.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ace1ofspades.recyclerview.GroupAdapter
import com.ace1ofspades.recyclerview.viewHolders.ViewHolder
import com.nsicyber.deezerpractice.PreferencesHelper
import com.nsicyber.deezerpractice.R
import com.nsicyber.deezerpractice.components.SearchListComponent
import com.nsicyber.deezerpractice.models.MusicModel


class LikesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    var adapter = GroupAdapter<ViewHolder>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_likes, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
        configureRows()

    }


    private fun configureRows(
    ) {
        var list = PreferencesHelper(requireContext()).likedMusics as ArrayList<MusicModel>
        adapter.clear()
        adapter.add(SearchListComponent(list).apply {
            this.fragment = this@LikesFragment
        })

    }

    override fun onResume() {
        super.onResume()
        configureRows()
    }


}