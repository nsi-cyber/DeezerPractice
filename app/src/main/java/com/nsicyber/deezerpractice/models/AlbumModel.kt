package com.nsicyber.deezerpractice.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class AlbumModel : Serializable {
    @SerializedName("id")
    var id: String? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("upc")
    var upc: String? = null
    @SerializedName("link")
    var link: String? = null
    @SerializedName("share")
    var share: String? = null
    @SerializedName("cover")
    var cover: String? = null
    @SerializedName("cover_small")
    var coverSmall: String? = null
    @SerializedName("cover_medium")
    var coverMedium: String? = null
    @SerializedName("cover_big")
    var coverBig: String? = null
    @SerializedName("cover_xl")
    var coverXl: String? = null
    @SerializedName("md5_image")
    var md5Image: String? = null
    @SerializedName("genre_id")
    var genreId: Int? = null
    @SerializedName("genres")
    var genres: ArrayGenreModel? = ArrayGenreModel()
    @SerializedName("label")
    var label: String? = null
    @SerializedName("nb_tracks")
    var nbTracks: Int? = null
    @SerializedName("duration")
    var duration: Int? = null
    @SerializedName("fans")
    var fans: Int? = null
    @SerializedName("release_date")
    var releaseDate: String? = null
    @SerializedName("record_type")
    var recordType: String? = null
    @SerializedName("available")
    var available: Boolean? = null
    @SerializedName("tracklist")
    var tracklist: String? = null
    @SerializedName("explicit_lyrics")
    var explicitLyrics: Boolean? = null
    @SerializedName("explicit_content_lyrics")
    var explicitContentLyrics: Int? = null
    @SerializedName("explicit_content_cover")
    var explicitContentCover: Int? = null
    @SerializedName("contributors")
    var contributors: ArrayList<ArtistModel> = arrayListOf()
    @SerializedName("artist")
    var artist: ArtistModel? = ArtistModel()
    @SerializedName("type")
    var type: String? = null
    @SerializedName("tracks")
    var tracks: MusicModel? = MusicModel()

}

class ArrayAlbumModel : Serializable {
    @SerializedName("data")
    var data: ArrayList<AlbumModel> = arrayListOf()
}
