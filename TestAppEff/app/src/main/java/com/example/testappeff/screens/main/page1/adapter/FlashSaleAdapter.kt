package com.example.testappeff.screens.main.page1.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.network.FlashSaleItems
import com.example.testappeff.databinding.ItemFlashSaleListBinding

class FlashSaleAdapter(private val onItemClick: (itemId: Int) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val differ = AsyncListDiffer(this, ItemsDiffUtilCallback())

    fun updateList(items: FlashSaleItems) {
        differ.submitList(items.item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemFlashSaleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    class ItemsDiffUtilCallback() : DiffUtil.ItemCallback<FlashSaleItems.Item>() {
        override fun areItemsTheSame(
            oldItem: FlashSaleItems.Item,
            newItem: FlashSaleItems.Item
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FlashSaleItems.Item,
            newItem: FlashSaleItems.Item
        ): Boolean {
            return oldItem == newItem
        }


    }

    class ItemHolder(
        private val binding: ItemFlashSaleListBinding,
        private val onItemCLick: (taskId: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        private var currentItemId: Int? = null

        init {
            binding.root.setOnClickListener {
                Log.e("GO", "GO TO ")
                currentItemId?.let(onItemCLick)
            }
        }

        fun bind(item: FlashSaleItems.Item) {
            currentItemId = item.id
            with(binding) {
                tvCategory.text = item.category
                tvName.text = item.name
                tvDiscount.text = "${item.discount}% off"
                tvPrice.text = "$ ${item.price}"
            }

            Glide.with(itemView)
                .load(item.imageUrl)
                .into(binding.ivMain)
        }
    }
}