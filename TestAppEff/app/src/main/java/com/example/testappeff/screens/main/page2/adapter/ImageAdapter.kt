package com.example.testappeff.screens.main.page2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.testappeff.databinding.ItemImagePage2Binding

class ImageAdapter(private val imageUrlList: List<String>) :
    RecyclerView.Adapter<ImageAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(val binding: ItemImagePage2Binding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(imageUrl: String) {
            Glide.with(binding.root.context)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.ivMain)
        }

    }

    override fun getItemCount(): Int = imageUrlList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {

        val binding = ItemImagePage2Binding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {

        holder.setData(imageUrlList[position])
    }

}