package com.nsicyber.deezerpractice.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ArtistModel : Serializable {

    @SerializedName("id"             ) var id            : String?  = null
    @SerializedName("name"           ) var name          : String?  = null
    @SerializedName("picture"        ) var picture       : String?  = null
    @SerializedName("picture_small"  ) var pictureSmall  : String?  = null
    @SerializedName("picture_medium" ) var pictureMedium : String?  = null
    @SerializedName("picture_big"    ) var pictureBig    : String?  = null
    @SerializedName("picture_xl"     ) var pictureXl     : String?  = null
    @SerializedName("radio"          ) var radio         : Boolean? = null
    @SerializedName("tracklist"      ) var tracklist     : String?  = null
    @SerializedName("type"           ) var type          : String?  = null




}


class ArrayArtistModel:Serializable {
    @SerializedName("data" ) var data : ArrayList<ArtistModel> = arrayListOf()
}
