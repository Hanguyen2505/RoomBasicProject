package com.example.roombasicproject.fragments.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.compose.material.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roombasicproject.R
import com.example.roombasicproject.UserViewModel
import com.example.roombasicproject.databinding.FragmentUpdateBinding
import com.example.roombasicproject.entities.User


class UpdateFragment : Fragment()  {

    private lateinit var mUserViewModel: UserViewModel

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<UpdateFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        binding.firstNameUpdate.setText(args.currentUser.firstName)
        binding.lastNameUpdate.setText(args.currentUser.lastName)
        binding.ageUpdate.setText(args.currentUser.age.toString())

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.buttonUpdate.setOnClickListener {
            updateItem()
        }

        binding.buttonDelete.setOnClickListener {
            deleteUser()
        }

        return binding.root
    }


    private fun deleteUser() {
        val firstName = _binding?.firstNameUpdate?.text.toString()
        val lastName = _binding?.lastNameUpdate?.text.toString()
        val age = _binding?.ageUpdate?.text.toString()

        // Alert Dialog
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_, _ ->
            val user = User(args.currentUser.id, firstName, lastName, Integer.parseInt(age))
            mUserViewModel.deleteUser(user)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            Toast.makeText(requireContext(), "Successfully deleted", Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton("No") {_, _ ->

        }

        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure to Delete ${args.currentUser.firstName}?")
        builder.create().show()

    }

    private fun updateItem() {
        val firstName = _binding?.firstNameUpdate?.text.toString()
        val lastName = _binding?.lastNameUpdate?.text.toString()
        val age = _binding?.ageUpdate?.text.toString()

        // TODO check this
        if (
            inputCheck(firstName, lastName, age)) {
            val user = User(args.currentUser.id, firstName, lastName, Integer.parseInt(age))
            mUserViewModel.upsertUser(user)

            Toast.makeText(requireContext(), "Successfully updated", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        else {
            Toast.makeText(requireContext(), "update fail", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: String): Boolean {
        return firstName.isNotEmpty() && lastName.isNotEmpty() && age.isNotEmpty()
    }

}