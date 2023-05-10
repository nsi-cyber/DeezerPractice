package com.nsicyber.deezerpractice.network


import com.nsicyber.deezerpractice.models.ArrayAlbumModel
import com.nsicyber.deezerpractice.models.ArrayArtistModel
import com.nsicyber.deezerpractice.models.ArrayGenreModel
import retrofit2.Call
import retrofit2.http.*

//            var BASE_URL = "https://api.deezer.com"
interface RetrofitInterface {


    @GET("/genre")
    fun getAllGenres(): Call<ArrayGenreModel?>

    @GET("/genre/{genreId}/artists")
    fun getGenreArtists(
        @Path(value = "genreId") genreId: Int?
    ): Call<ArrayArtistModel?>

    @GET("/artist/{artistId}/albums")
    fun getArtistAlbums(
        @Path(value = "artistId") artistId: String?
    ): Call<ArrayAlbumModel?>

    @GET("/album/{albumId}")
    fun getAlbum(
        @Path(value = "albumId") albumId: String?
    ): Call<ArrayAlbumModel?>


}
