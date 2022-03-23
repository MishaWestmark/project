package com.westmark.mychatapp.ui.userList

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.westmark.mychatapp.data.User

class UserListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val differ = AsyncListDiffer(this, UserDiffUtilCallback())
    private val delegateManager = AdapterDelegatesManager<List<User>>()

    init {
        delegateManager.addDelegate(UserListAdapterDelegate())
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateManager.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserListAdapterDelegate.UserHolder -> {
                val user = differ.currentList[position]
                holder.bind(user)
            }
            else -> error("incorrect holder")
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun updateAdapter(newUserList: List<User>){
        differ.submitList(newUserList)
    }

    class UserDiffUtilCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.uid == newItem.uid
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.equals(newItem)
        }

    }
}