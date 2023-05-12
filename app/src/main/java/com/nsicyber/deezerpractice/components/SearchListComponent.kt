package com.nsicyber.deezerpractice.components

import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ace1ofspades.recyclerview.GroupAdapter
import com.ace1ofspades.recyclerview.items.Item
import com.ace1ofspades.recyclerview.scrollListeners.ItemOffsetDecoration
import com.ace1ofspades.recyclerview.viewHolders.ViewHolder
import com.google.gson.annotations.SerializedName
import com.nsicyber.deezerpractice.PreferencesHelper
import com.nsicyber.deezerpractice.R
import com.nsicyber.deezerpractice.dialogs.PlaySongDialog
import com.nsicyber.deezerpractice.models.MusicModel
import com.nsicyber.deezerpractice.utils.Parser
import java.util.Locale

class SearchListComponent(model: List<MusicModel>) : Item<ViewHolder, List<MusicModel>>(model) {

    // ViewObjects
    lateinit var searchText: EditText
    lateinit var playButton: CardView
    lateinit var mixButton: CardView
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
            adapter.add(MusicComponent(i).apply { this.fragment=this@SearchListComponent.fragment
            this.baseAdapter=this@SearchListComponent.adapter})
        }
        var playDialog=PlaySongDialog()
        playButton.setOnClickListener {
            playDialog.start(context!!,fragment!!.requireActivity(), musicList = model, isShuffle = false)
        }
        mixButton.setOnClickListener {
            playDialog.start(context!!,fragment!!.requireActivity(), musicList = model, isShuffle = true)
        }

        searchText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val filterText = s.toString().toLowerCase(Locale.getDefault())
                adapter.clear() // mevcut öğeleri temizle
                for (item in model!!) {
                    if (filterText.isEmpty() || item.album?.title?.toLowerCase(Locale.getDefault())!!.contains(filterText)  || item.title?.toLowerCase(Locale.getDefault())!!.contains(filterText) || item.artist?.name?.toLowerCase(Locale.getDefault())!!.contains(filterText)) {
                        adapter.add(MusicComponent(item).apply {
                            this.fragment = this@SearchListComponent.fragment
                            this.baseAdapter = this@SearchListComponent.adapter
                        })
                    }
                }
            }
        })



    }


    companion object {
         var layout = R.layout.c_search_list

    }
}

