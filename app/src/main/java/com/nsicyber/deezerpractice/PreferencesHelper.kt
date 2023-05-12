package com.nsicyber.deezerpractice

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nsicyber.deezerpractice.models.MusicModel

class PreferencesHelper constructor(context: Context) {
    private val mPreferences: SharedPreferences
    private val mEditor: SharedPreferences.Editor


    var likedMusics: ArrayList<MusicModel>?
        get() {
            val type = object : TypeToken<ArrayList<MusicModel>>() {}.type
            return Gson().fromJson(
                mPreferences.getString(LIKED_MUSICS, ArrayList<MusicModel>().toString()),
                type
            )
        }
        set(musicList) {
            val json = Gson().toJson(musicList)
            mEditor.putString(LIKED_MUSICS, json)
            mEditor.commit()
        }


    fun clear() {
        mEditor.clear().commit()
    }

    companion object {
        private var instance: PreferencesHelper? = null

        private const val LIKED_MUSICS = "likedMusicList"

        fun getInstance(context: Context): PreferencesHelper? {
            if (instance == null) instance = PreferencesHelper(context)
            return instance
        }
    }

    init {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        mEditor = mPreferences.edit()
    }
}