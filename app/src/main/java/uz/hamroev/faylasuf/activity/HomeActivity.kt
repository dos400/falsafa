package uz.hamroev.faylasuf.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.Navigation
import uz.hamroev.faylasuf.R
import uz.hamroev.faylasuf.cache.Cache
import uz.hamroev.faylasuf.database.userDatabase.UserDatabase
import uz.hamroev.faylasuf.database.userDatabase.UserEntity
import uz.hamroev.faylasuf.databinding.ActivityHomeBinding
import uz.hamroev.faylasuf.services.MusicService
import uz.hamroev.faylasuf.viewModel.SharedViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController

    private val musicService: Intent by lazy {
        Intent(this, MusicService::class.java)
    }

    private val sharedViewModel: SharedViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkIsHaveUser()

        navController = Navigation.findNavController(this, R.id.my_nav_host_fragment)

        if (Cache.firstTime!!) {
            navController.navigate(R.id.homeFragment)
        } else {
            navController.navigate(R.id.splashFragment)
        }


        checkSound()

        // Observe the shared data
        sharedViewModel.sharedData.observe(this) { newData ->
            // Update UI with the new data
            // For example:
            //textView.text = newData
            checkSound()

        }


    }

    private fun checkIsHaveUser() {
        if (!Cache.isHaveUser!!) {
            val userEntity = UserEntity()
            UserDatabase.GET.getUserDatabase().getUserDao().creteUser(userEntity)
            Cache.isHaveUser = true
        }
    }

    private fun checkSound() {
        if (Cache.sound == true) {
            startSound()
        } else {
            stopSound()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun startSound() {
        startService(musicService)
    }

    private fun stopSound() {
        stopService(musicService)
    }

    override fun onDestroy() {
        stopService(musicService)
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        if (Cache.sound == true) {
            startService(musicService)
        }
    }


}