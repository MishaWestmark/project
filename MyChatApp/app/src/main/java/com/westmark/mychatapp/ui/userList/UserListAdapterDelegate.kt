package com.westmark.mychatapp.ui.userList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.westmark.mychatapp.R
import com.westmark.mychatapp.data.User
import com.westmark.mychatapp.databinding.ItemUserBinding
import com.westmark.mychatapp.inflate

class UserListAdapterDelegate :
    AbsListItemAdapterDelegate<User, User, UserListAdapterDelegate.UserHolder>() {
    override fun isForViewType(item: User, items: MutableList<User>, position: Int): Boolean {
       return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): UserHolder {
        return UserHolder(parent.inflate(R.layout.item_user))
    }

    override fun onBindViewHolder(item: User, holder: UserHolder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    class UserHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        private val binding = ItemUserBinding.bind(view)
        fun bind(user: User) {
            binding.textViewName.text = user.name
            Glide.with(itemView)
                .load(R.drawable.ic_person)
                .into(binding.imageViewUser)
        }
    }


}