package com.nsicyber.deezerpractice.components

import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ace1ofspades.recyclerview.GroupAdapter
import com.ace1ofspades.recyclerview.items.Item
import com.ace1ofspades.recyclerview.viewHolders.ViewHolder
import com.google.gson.annotations.SerializedName
import com.nsicyber.deezerpractice.R
import com.nsicyber.deezerpractice.models.MusicModel
import com.nsicyber.deezerpractice.utils.Parser

class SearchListComponent(model: List<MusicModel>) : Item<ViewHolder, List<MusicModel>>(model) {

    // ViewObjects
    lateinit var searchText: EditText
    lateinit var playButton: Button
    lateinit var mixButton: Button
    lateinit var recyclerView: RecyclerView
    var adapter = GroupAdapter<ViewHolder>()

    override fun getLayout(): Int = layout

    override fun initialize() {
        super.initialize()
        searchText = findViewById(R.id.searchText)
        playButton = findViewById(R.id.playButton)
        mixButton = findViewById(R.id.mixButton)
        recyclerView = findViewById(R.id.recyclerView)
    }

    override fun configure() {
        super.configure()
        recyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
        for(i in model!!)
        {
            adapter.add(MusicComponent(i))
        }

    }


    companion object {
        const val layout = R.layout.c_search_list

    }
}

