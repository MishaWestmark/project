package com.example.testappeff.screens.main.page1.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.network.LatestItems
import com.example.testappeff.databinding.ItemLatestListBinding

class LatestItemsAdapter(private val onItemClick: (itemId: Int) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val differ = AsyncListDiffer(this, ItemsDiffUtilCallback())

    fun updateList(items: LatestItems) {
        differ.submitList(items.items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemLatestListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHolder(binding, onItemClick)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemHolder -> {
                val item = differ.currentList[position]
                holder.bind(item)
            }
        }
    }

    class ItemsDiffUtilCallback() : DiffUtil.ItemCallback<LatestItems.Item>() {
        override fun areItemsTheSame(
            oldItem: LatestItems.Item,
            newItem: LatestItems.Item
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: LatestItems.Item,
            newItem: LatestItems.Item
        ): Boolean {
            return oldItem == newItem
        }

    }

    class ItemHolder(
        private val binding: ItemLatestListBinding,
        private val onItemCLick: (taskId: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        private var currentItemId: Int? = 1

        init {
            binding.root.setOnClickListener {
                Log.e("GO", "GO TO ")
                currentItemId?.let(onItemCLick)
            }
        }

        fun bind(item: LatestItems.Item) {
            with(binding) {
                tvCategory.text = item.category
                tvName.text = item.name
                tvPrice.text = item.price

            }
            Glide.with(itemView)
                .load(item.imageUrl)
                .into(binding.ivMain)
        }
    }
}