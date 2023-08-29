@file:Suppress("DEPRECATION")

package uz.hamroev.faylasuf

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.hamroev.faylasuf.activity.HomeActivity
import uz.hamroev.faylasuf.databinding.ActivityMainBinding
import uz.hamroev.faylasuf.utils.anim

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideSystemBars()
        startAnimation()

        MainScope().launch {
            delay(2250)
            startActivity(Intent(this@MainActivity, HomeActivity::class.java))
            finish()
        }




    }

    private fun startAnimation() {
        val topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation )
        val bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation )

        binding.logoImage.startAnimation(topAnimation)
        binding.appNameObject.startAnimation(bottomAnimation)
    }

    private fun hideSystemBars() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val decorView = window.decorView
            val windowInsetsController = decorView.windowInsetsController ?: return
            windowInsetsController.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            windowInsetsController.hide(WindowInsets.Type.systemBars())
        } else {
            window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }
    }
}