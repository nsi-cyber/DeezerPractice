package com.nsicyber.deezerpractice.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class GenreModel : Serializable {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("picture")
    var picture: String? = null

    @SerializedName("picture_small")
    var pictureSmall: String? = null
    @SerializedName("picture_medium")
    var pictureMedium: String? = null
    @SerializedName("picture_big")
    var pictureBig: String? = null
    @SerializedName("picture_xl")
    var pictureXl: String? = null
    @SerializedName("type")
    var type: String? = null

}

class ArrayGenreModel : Serializable {
    @SerializedName("data")
    var data: ArrayList<GenreModel> = arrayListOf()
}