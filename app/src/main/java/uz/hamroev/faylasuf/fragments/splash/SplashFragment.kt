package uz.hamroev.faylasuf.fragments.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.hamroev.faylasuf.R
import uz.hamroev.faylasuf.cache.Cache
import uz.hamroev.faylasuf.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater)

        binding.bottomLayout.setOnClickListener {
            Cache.firstTime = true
            findNavController().navigate(R.id.homeFragment)
        }






        return binding.root
    }


}