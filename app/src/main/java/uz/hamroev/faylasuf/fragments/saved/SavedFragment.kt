package uz.hamroev.faylasuf.fragments.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.hamroev.faylasuf.databinding.FragmentSavedBinding

class SavedFragment : Fragment() {


    private lateinit var binding: FragmentSavedBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSavedBinding.inflate(layoutInflater)


        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }


        return binding.root
    }


}