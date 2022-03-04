package com.westmark.chatapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.westmark.chatapp.databinding.ItemUserBinding

class UserAdapter(private val context: Context, private val userList: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.UserHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        return UserHolder(view)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(userList[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("name", userList[position].name)
            intent.putExtra("uid", userList[position].uid)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = userList.size

    class UserHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        private val binding = ItemUserBinding.bind(view)
        fun bind(
            user: User
        ) {
            binding.textViewName.text = user.name
        }
    }
}