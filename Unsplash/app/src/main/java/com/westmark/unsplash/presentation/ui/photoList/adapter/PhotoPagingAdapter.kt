package com.westmark.unsplash.presentation.ui.photoList.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.Target
import com.westmark.unsplash.R
import com.westmark.unsplash.databinding.ItemPhotoBinding
import com.westmark.unsplash.presentation.data.PhotoEntity
import jp.wasabeef.glide.transformations.RoundedCornersTransformation


class PhotoPagingAdapter(private val onItemClick: (id: String) -> Unit) :
    PagingDataAdapter<PhotoEntity, PhotoPagingAdapter.PhotoListHolder>(PhotoDiffUtilCallback()) {

    override fun onBindViewHolder(holder: PhotoListHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoListHolder {
        return PhotoListHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false),
            onItemClick
        )
    }

    class PhotoListHolder(
        view: View,
        private val onItemClick: (id: String) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val binding = ItemPhotoBinding.bind(view)
        private var currentID: String? = null

        init {
            with(view) {
                setOnClickListener {
                    currentID?.let(onItemClick)
                }
            }
        }

        fun bind(
            photo: PhotoEntity
        ) {
            currentID = photo.id
            binding.textViewProfileName.text = photo.user.name
            binding.textViewAccount.text = "@${photo.user.accountName}"
            binding.textViewLikes.text = photo.likes.toString()
            Glide.with(itemView)
                .load(photo.urls.urlRegular)
                .override(Target.SIZE_ORIGINAL)
                .placeholder(R.drawable.ic_image_placeholder)
                .transform(RoundedCornersTransformation(40, 0))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(binding.imageViewPhoto)
            Glide.with(itemView)
                .load(photo.user.profileImage.urlSmall)
                .circleCrop()
                .priority(Priority.LOW)
                .into(binding.imageViewAccount)
        }

    }

    class PhotoDiffUtilCallback : DiffUtil.ItemCallback<PhotoEntity>() {
        override fun areItemsTheSame(oldItem: PhotoEntity, newItem: PhotoEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PhotoEntity, newItem: PhotoEntity): Boolean {
            return oldItem == newItem
        }

    }
}