package uz.hamroev.faylasuf.fragments.authors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.hamroev.faylasuf.R
import uz.hamroev.faylasuf.adapters.UserAdapter
import uz.hamroev.faylasuf.databinding.FragmentAuthorsBinding
import uz.hamroev.faylasuf.models.MyUser

class AuthorsFragment : Fragment() {


    private lateinit var binding: FragmentAuthorsBinding


    private lateinit var userAdapter: UserAdapter
    private lateinit var list: ArrayList<MyUser>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAuthorsBinding.inflate(layoutInflater)


        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        // will be authors

        loadUsers()
        userAdapter = UserAdapter(requireContext(), list)
        binding.rvUsers.adapter = userAdapter




        return binding.root
    }

    private fun loadUsers() {
        list = ArrayList()
        list.clear()
        list.add(MyUser("", "", R.drawable.img_splash))
        list.add(MyUser("", "", R.drawable.img_splash))
        list.add(MyUser("", "", R.drawable.img_splash))
        list.add(MyUser("", "", R.drawable.img_splash))
    }


}