package com.nsicyber.deezerpractice.dialogs

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nsicyber.deezerpractice.R
import com.nsicyber.deezerpractice.models.MusicModel
import com.nsicyber.deezerpractice.utils.loadUrlRadius


class PlaySongDialog {
    private var anim: ObjectAnimator? = null
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var songName: TextView
    private lateinit var artistName: TextView
    private lateinit var albumName: TextView
    lateinit var imageView: ImageView
    private lateinit var playPauseView: ImageView
    private lateinit var frameLay: FrameLayout
    private var isPlaying: Boolean = false

    private fun close() {
        mediaPlayer?.reset()
        mediaPlayer?.release()
        imageView.clearAnimation()
    }

    private fun playSound(context: Context, model: MusicModel, completion: () -> Unit) {
        albumName.text = model.album?.title
        artistName.text = model.artist?.name
        imageView.loadUrlRadius(model.album?.coverXl, 1)
        songName.text = model.title
        mediaPlayer?.apply {
            reset()
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
        isPlaying = true


        anim = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f)
        anim?.duration = 5000
        anim?.repeatCount = 6
        anim?.repeatMode = ObjectAnimator.RESTART;


        anim?.start()
        frameLay.setOnClickListener {
            if (isPlaying) {
                mediaPlayer?.pause()
                anim?.pause()
                playPauseView.setImageResource(R.drawable.ic_play)
                isPlaying = false
            } else {
                mediaPlayer?.start()
                anim?.resume()
                playPauseView.setImageResource(R.drawable.ic_pause)
                isPlaying = true
            }
        }

        mediaPlayer?.setOnCompletionListener {
            completion()
        }
    }

    fun start(
        context: Context,
        activity: Activity,
        musicModel: MusicModel? = null,
        isShuffle: Boolean? = null,
        musicList: List<MusicModel>? = null,
    ) {
        mediaPlayer = MediaPlayer()
        val dialog = BottomSheetDialog(context, R.style.BottomSheetDialog)
        val view = activity.layoutInflater.inflate(R.layout.dialog_play_song, null)
        songName = view.findViewById(R.id.songText)
        artistName = view.findViewById(R.id.artistText)
        albumName = view.findViewById(R.id.albumText)
        imageView = view.findViewById(R.id.imageView)
        frameLay = view.findViewById(R.id.frameLay)
        playPauseView = view.findViewById(R.id.playPauseView)
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
                playNextSong(context, musicList.shuffled(), 0, dialog)
            else
                playNextSong(context, musicList, 0, dialog)
        }
    }

    private fun playNextSong(
        context: Context,
        musicList: List<MusicModel>,
        iterator: Int,
        dialog: BottomSheetDialog
    ) {
        if (iterator < musicList.size)
            playSound(context, musicList[iterator]) {
                playNextSong(context, musicList, iterator + 1, dialog)
            }
        else {
            close()
            dialog.dismiss()
        }
    }
}
