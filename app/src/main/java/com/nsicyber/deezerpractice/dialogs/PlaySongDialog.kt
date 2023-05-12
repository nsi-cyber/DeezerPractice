package com.nsicyber.deezerpractice.dialogs

import android.app.Activity
import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.text.Editable
import android.text.TextWatcher
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nsicyber.deezerpractice.R
import com.nsicyber.deezerpractice.models.ArrayMusicModel
import com.nsicyber.deezerpractice.models.MusicModel
import com.nsicyber.deezerpractice.utils.loadUrlRadius
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaySongDialog {
    var mediaPlayer: MediaPlayer? = null
    lateinit var songName: TextView
    lateinit var artistName: TextView
    lateinit var albumName: TextView
    lateinit var imageView: ImageView


    fun close() {

        mediaPlayer?.reset()
        mediaPlayer?.release()
        imageView.clearAnimation()
    }

    fun playSound(context: Context, model: MusicModel, completion: () -> Unit) {

        albumName.text=model.album?.title
        artistName.text=model.artist?.name
        imageView.loadUrlRadius(model.album?.coverXl,1)
        songName.text=model.title
mediaPlayer!!.reset()
        mediaPlayer?.apply {
            setDataSource(context, model.preview!!.toUri())
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            prepare()
            start()
        }


        val rotateAnim = AnimationUtils.loadAnimation(
            context,
            R.anim.rotate_image
        )

        imageView.startAnimation(rotateAnim)


        mediaPlayer?.setOnCompletionListener {
            completion()
        }

    }


    fun start(
        context: Context,
        activity: Activity,
        musicModel: MusicModel?=null,
        isShuffle: Boolean?=null,
        musicList: List<MusicModel>?=null,
    ) {
        mediaPlayer = MediaPlayer()
        val dialog = BottomSheetDialog(context, R.style.BottomSheetDialog)
        val view = activity.layoutInflater.inflate(R.layout.dialog_play_song,  null)

         songName = view.findViewById(R.id.songText)
         artistName = view.findViewById(R.id.artistText)
         albumName = view.findViewById(R.id.albumText)
         imageView = view.findViewById(R.id.imageView)


        dialog.setCancelable(true)
        dialog.setContentView(view)
        dialog.show()

        dialog.setOnCancelListener {
            close()
        }

        if (musicModel != null) {
            playSound(context, musicModel) {

                close()
                dialog.dismiss()
            }

        } else if (musicList != null) {
            if (isShuffle == true)
                playNextSong(context,musicList.shuffled(),0,dialog)
            else
                playNextSong(context,musicList,0,dialog)




        }


    }

    fun playNextSong(context: Context,musicList: List<MusicModel>,iterator: Int,dialog:BottomSheetDialog){
        if(iterator<musicList.size)
        playSound(context, musicList.get(iterator)) {
            playNextSong(context,musicList,(iterator+1),dialog)
        }
        else
        {
            close()
            dialog.dismiss()
        }

    }


}
