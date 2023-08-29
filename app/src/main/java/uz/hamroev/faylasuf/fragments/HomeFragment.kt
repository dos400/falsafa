package uz.hamroev.faylasuf.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.realpacific.clickshrinkeffect.applyClickShrink
import uz.hamroev.faylasuf.R
import uz.hamroev.faylasuf.cache.Cache
import uz.hamroev.faylasuf.databinding.FragmentHomeBinding
import uz.hamroev.faylasuf.utils.vibrate
import uz.hamroev.faylasuf.viewModel.SharedViewModel
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        checkSoundAndMusic()

        binding.menuButton.setOnClickListener {
            binding.drawerLayout.open()
        }

        binding.playCardButton.setOnClickListener {
            findNavController().navigate(R.id.quizFragment)
        }

        binding.apply {

            cardSoundButton.setOnClickListener {

                cardSoundButton.applyClickShrink()
                binding.root.context.vibrate(200)

                if (Cache.sound == true) {
                    binding.ivSound.setImageResource(R.drawable.ic_sound_off)
                    Cache.sound = false
                    //stopSound()
                    sharedViewModel.updateData("no")
                } else {
                    binding.ivSound.setImageResource(R.drawable.ic_sound)
                    Cache.sound = true
                    //startSound()
                    sharedViewModel.updateData("yes")
                }

            }
        }







        return binding.root
    }

    private fun checkSoundAndMusic() {
        if (Cache.sound == true) {
            binding.ivSound.setImageResource(R.drawable.ic_sound)
//            startSound()
            sharedViewModel.updateData("yes")

        } else {
            binding.ivSound.setImageResource(R.drawable.ic_sound_off)
//            stopSound()
            sharedViewModel.updateData("no")
        }

        if (Cache.music == true) {
            binding.ivMusic.setImageResource(R.drawable.ic_volume)
            //startMusic()
        } else {
            binding.ivMusic.setImageResource(R.drawable.ic_volume_x)
            //stopMusic()
        }

    }

    private fun getCurrentDateAndTime(): String {
        val dateAndTime: Date = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val date = dateFormat.format(dateAndTime)
        val time = timeFormat.format(dateAndTime)
        val vaqt: String = "$date - $time"
        //Toast.makeText(this, "$vaqt", Toast.LENGTH_SHORT).show()
        return vaqt

    }


}