package com.nsicyber.deezerpractice.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ace1ofspades.recyclerview.GroupAdapter
import com.ace1ofspades.recyclerview.viewHolders.ViewHolder
import com.nsicyber.deezerpractice.PreferencesHelper
import com.nsicyber.deezerpractice.R
import com.nsicyber.deezerpractice.components.MusicComponent
import com.nsicyber.deezerpractice.dialogs.PlaySongDialog
import com.nsicyber.deezerpractice.models.MusicModel
import java.util.Locale


class LikesFragment : Fragment() {
    var adapter = GroupAdapter<ViewHolder>()
    // ViewObjects
    private lateinit var searchText: EditText
    private lateinit var playButton: CardView
    private lateinit var mixButton: CardView
    lateinit var recyclerView: RecyclerView
    var adapterNested = GroupAdapter<ViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_likes, container, false)

       // recyclerView = view.findViewById(R.id.recyclerView)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchText = view.findViewById(R.id.searchText)
        playButton = view.findViewById(R.id.playButton)
        mixButton = view.findViewById(R.id.mixButton)
        recyclerView = view.findViewById(R.id.recyclerView)
    configure()

    }

    fun configure() {
        var list = PreferencesHelper(requireContext()).likedMusics as ArrayList<MusicModel>

        recyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.isNestedScrollingEnabled = true
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        for(i in list)
        {
            adapter.add(MusicComponent(i).apply {
                fragment=this@LikesFragment
                baseAdapter=adapter
            })
        }
        var playDialog= PlaySongDialog()
        playButton.setOnClickListener {
            playDialog.start(requireContext(),requireActivity(), musicList = list, isShuffle = false)
        }
        mixButton.setOnClickListener {
            playDialog.start(requireContext(),requireActivity(), musicList = list, isShuffle = true)
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
                adapter.clear()
                for (item in list) {
                    if (filterText.isEmpty() ||
                        item.album?.title?.toLowerCase(Locale.getDefault())!!.contains(filterText) ||
                        item.title?.toLowerCase(Locale.getDefault())!!.contains(filterText) ||
                        item.artist?.name?.toLowerCase(Locale.getDefault())!!.contains(filterText)) {
                        adapter.add(MusicComponent(item).apply {
                            this.fragment = fragment
                            this.baseAdapter = adapter
                        })
                    }
                }
            }
        })



    }

    override fun onResume() {
        super.onResume()
       configure()
    }


}