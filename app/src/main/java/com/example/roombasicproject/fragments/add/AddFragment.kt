package com.example.roombasicproject.fragments.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roombasicproject.R
import com.example.roombasicproject.UserViewModel
import com.example.roombasicproject.databinding.FragmentAddBinding
import com.example.roombasicproject.entities.User


class addFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        binding.buttonAdd.setOnClickListener {
            insertToDatabase()
        }

        return _binding!!.root
    }

    private fun insertToDatabase() {
        val firstName = _binding?.firstNameInsert?.text.toString()
        val lastName = _binding?.lastNameInsert?.text.toString()
        val age = _binding?.ageInsert?.text.toString()

        // TODO check this
        if (inputCheck(firstName, lastName, age)) {
            val user = User(0, firstName, lastName, Integer.parseInt(age))
            mUserViewModel.upsertUser(user)

            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else {
            Toast.makeText(requireContext(), "Please fill out all the fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: String): Boolean {
        return firstName.isNotEmpty() && lastName.isNotEmpty() && age.isNotEmpty()
    }
}