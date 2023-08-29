package uz.hamroev.faylasuf.fragments.aboutApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.hamroev.faylasuf.databinding.FragmentAboutAppBinding


class AboutAppFragment : Fragment() {


    private lateinit var binding: FragmentAboutAppBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAboutAppBinding.inflate(layoutInflater)

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }








        return binding.root
    }


}