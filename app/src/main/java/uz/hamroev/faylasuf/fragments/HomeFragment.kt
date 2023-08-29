package uz.hamroev.faylasuf.fragments

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.realpacific.clickshrinkeffect.applyClickShrink
import uz.hamroev.faylasuf.R
import uz.hamroev.faylasuf.adapters.NavAdapter
import uz.hamroev.faylasuf.cache.Cache
import uz.hamroev.faylasuf.databinding.FragmentHomeBinding
import uz.hamroev.faylasuf.models.Nav
import uz.hamroev.faylasuf.utils.toast
import uz.hamroev.faylasuf.utils.vibrate
import uz.hamroev.faylasuf.viewModel.SharedViewModel
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    private val sharedViewModel: SharedViewModel by activityViewModels()


    //nav menu list and navAdapter to down
    private lateinit var listNav: ArrayList<Nav>
    private lateinit var navAdapter: NavAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        checkSoundAndMusic()


        binding.menuButton.setOnClickListener {
            binding.drawerLayout.open()
        }

        binding.accountButton.setOnClickListener {
            toast("Tez orada...")
        }

        loadNav()
        navAdapter = NavAdapter(requireContext(), listNav, object : NavAdapter.OnNavClickListener {
            override fun onCLick(nav: Nav, position: Int) {
                when (position) {
                    0 -> {
                        binding.drawerLayout.close()
                    }
                    1 -> {
                        binding.drawerLayout.close()
                        findNavController().navigate(R.id.savedFragment)
                    }
                    2 -> {
                        binding.drawerLayout.close()
                        findNavController().navigate(R.id.aboutAppFragment)
                    }
                    3 -> {
                        binding.drawerLayout.close()
                        findNavController().navigate(R.id.authorsFragment)
                    }
                    4 -> {
                        try {
                            val intent = Intent(Intent.ACTION_SEND)
                            intent.type = "text/plain"
                            intent.putExtra(Intent.EXTRA_SUBJECT, "KIM FAYLASUF BO'LMOQCHI")
                            val shareMessage =
                                "https://play.google.com/store/apps/details?id=${activity!!.packageName}"
                            intent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                            startActivity(Intent.createChooser(intent, "share by Doston"))
                        } catch (e: Exception) {
                        }
                        binding.drawerLayout.close()
                    }
                    5 -> {
                        try {
                            val uri: Uri =
                                Uri.parse("market://details?id=${activity!!.packageName}")
                            val intent = Intent(Intent.ACTION_VIEW, uri)
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        } catch (e: ActivityNotFoundException) {
                            val uri: Uri =
                                Uri.parse("http://play.google.com/store/apps/details?id=${activity!!.packageName}")
                            val intent = Intent(Intent.ACTION_VIEW, uri)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                        }
                        binding.drawerLayout.close()
                    }
                    6 -> {
                        activity?.finish()
                    }
                }
            }
        })
        binding.rvNav.adapter = navAdapter

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


    private fun loadNav() {
        listNav = ArrayList()
        listNav.clear()
        listNav.add(Nav(requireActivity().resources.getString(R.string.main), R.drawable.fi_home))
        listNav.add(
            Nav(
                requireActivity().resources.getString(R.string.save),
                R.drawable.fi_bookmark
            )
        )
        listNav.add(Nav(requireActivity().resources.getString(R.string.info), R.drawable.fi_info))

        listNav.add(
            Nav(
                requireActivity().resources.getString(R.string.authors),
                R.drawable.fi_users
            )
        )
        listNav.add(
            Nav(
                requireActivity().resources.getString(R.string.share),
                R.drawable.fi_share_2
            )
        )
        listNav.add(Nav(requireActivity().resources.getString(R.string.rate), R.drawable.fi_star))
        listNav.add(
            Nav(
                requireActivity().resources.getString(R.string.exit),
                R.drawable.fi_log_out
            )
        )
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