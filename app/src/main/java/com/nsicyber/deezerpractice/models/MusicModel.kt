package com.nsicyber.deezerpractice.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class MusicModel : Serializable {
    @SerializedName("id")
    var id: String? = null
    @SerializedName("readable")
    var readable: Boolean? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("title_short")
    var titleShort: String? = null
    @SerializedName("title_version")
    var titleVersion: String? = null
    @SerializedName("link")
    var link: String? = null
    @SerializedName("duration")
    var duration: String? = null
    @SerializedName("rank")
    var rank: String? = null
    @SerializedName("explicit_lyrics")
    var explicitLyrics: Boolean? = null
    @SerializedName("explicit_content_lyrics")
    var explicitContentLyrics: Int? = null
    @SerializedName("explicit_content_cover")
    var explicitContentCover: Int? = null
    @SerializedName("preview")
    var preview: String? = null
    @SerializedName("md5_image")
    var md5Image: String? = null
    @SerializedName("artist")
    var artist: ArtistModel? = ArtistModel()
    @SerializedName("album")
    var album: AlbumModel? = AlbumModel()
    @SerializedName("type")
    var type: String? = null


}

class ArrayMusicModel : Serializable {
    @SerializedName("data")
    var data: ArrayList<MusicModel> = arrayListOf()
}

