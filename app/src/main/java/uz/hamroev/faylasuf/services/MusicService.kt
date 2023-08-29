package uz.hamroev.faylasuf.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import uz.hamroev.faylasuf.R
import uz.hamroev.faylasuf.utils.toast
import java.io.IOException

class MusicService : Service() {

    lateinit var mediaPlayer: MediaPlayer

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer()
        try {
            mediaPlayer.setDataSource(
                this, Uri.parse("android.resource://uz.hamroev.faylasuf/${R.raw.background_music}")
            )
        } catch (e: IOException) {
            e.printStackTrace()
        }
        mediaPlayer.isLooping = true // Set looping

        mediaPlayer.setVolume(100f, 100f)

        mediaPlayer.prepare()
        mediaPlayer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}