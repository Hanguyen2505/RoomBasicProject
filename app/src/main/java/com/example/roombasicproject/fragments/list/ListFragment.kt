package com.example.roombasicproject.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roombasicproject.ListAdapter
import com.example.roombasicproject.databinding.FragmentListBinding
import com.example.roombasicproject.R
import com.example.roombasicproject.UserViewModel

class listFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)

        //RecyclerView
        val recyclerView = binding.recycleView
        val adapter = ListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //UserViewModel

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllDatabase.observe(viewLifecycleOwner, Observer { user ->
            // user.size = 0
            adapter.setData(user)

        })

        binding.addFloatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        binding.deleteFloatingActionButton.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes") {_, _ ->
                mUserViewModel.deleteALlUser()
                Toast.makeText(requireContext(), "Deleted all user", Toast.LENGTH_SHORT).show()
            }

            builder.setNegativeButton("No") {_, _ ->

            }

            builder.setTitle("Delete All Data")
            builder.setMessage("Do you want to Delete all data")
            builder.create().show()
        }

        return _binding!!.root
    }


}