package com.nsicyber.deezerpractice.enums

import androidx.fragment.app.Fragment
import com.nsicyber.deezerpractice.R
import com.nsicyber.deezerpractice.ui.AlbumDetailFragment
import com.nsicyber.deezerpractice.ui.CategoryFragment
import com.nsicyber.deezerpractice.ui.ArtistDetailFragment
import com.nsicyber.deezerpractice.ui.ArtistListFragment
import com.nsicyber.deezerpractice.ui.LikesFragment

enum class Fragments(val id:String, val fragment: Class<Fragment>, val navigation_id:Int) {

    CategoryFragment("CategoryFragment",CategoryFragment().javaClass,R.id.categoryFragment),
    ArtistDetailFragment("ArtistDetailFragment",ArtistDetailFragment().javaClass,R.id.artistDetailFragment),
    ArtistListFragment            ("ArtistListFragment",         ArtistListFragment().javaClass,          R.id.artistListFragment),
    LikesFragment            ("LikesFragment",         LikesFragment().javaClass,          R.id.likesFragment),
    AlbumDetailFragment            ("AlbumDetailFragment",         AlbumDetailFragment().javaClass,          R.id.albumDetailFragment);


    companion object{
        fun get(
            id: String? = null,
            fragment: Fragment? = null,
            navigation_id: Int? = null
        ): Fragments? {
            id?.let { it1 ->
                return values().find { it.id == it1 }
            }
            fragment?.let { it1 ->
                return values().find { it.fragment == it1 }
            }
            navigation_id?.let { it1 ->
                return values().find { it.navigation_id == it1 }
            }
            return null
        }
    }
}