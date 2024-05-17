package com.example.roombasicproject

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roombasicproject.entities.User
import com.example.roombasicproject.fragments.list.listFragmentDirections

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    //create an empty user list
    private var userList = emptyList<User>()

    //ItemView
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val studentId: TextView = itemView.findViewById(R.id.student_id)
        val firstName: TextView = itemView.findViewById(R.id.first_name_text)
        val lastName: TextView = itemView.findViewById(R.id.last_name_text)
        val age: TextView = itemView.findViewById(R.id.age_text)
        val rowLayout: ConstraintLayout = itemView.findViewById(R.id.row_layout)
        val deleteButton: ImageButton = itemView.findViewById(R.id.delete_button)
    }

    //get custom_row pattern
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        //get current item
        val currentItem = userList[position]
        holder.studentId.text = currentItem.id.toString()
        holder.age.text = currentItem.age.toString()

        // already is String type
        holder.firstName.text = currentItem.firstName
        holder.lastName.text = currentItem.lastName

        // interacting Item
        holder.rowLayout.setOnClickListener {
            // an action going from ListFragment to UpdateFragment
            val action = listFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

        holder.deleteButton.setOnClickListener {
            // TODO delete Data
        }

    }

    fun setData(user: List<User>) {
        // userList is empty (size = 0)
        // user.size = 12
        this.userList = user
        notifyDataSetChanged()
    }

}