package com.westmark.unsplash.presentation.ui.collections.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.Target
import com.westmark.unsplash.R
import com.westmark.unsplash.databinding.ItemCollectionPhotoBinding
import com.westmark.unsplash.presentation.data.CollectionDetail

class PhotoCollectionPagingAdapter :
    PagingDataAdapter<CollectionDetail, PhotoCollectionPagingAdapter.PhotoCollectionsListHolder>(
        PhotoCollectionDiffUtilCallback()
    ) {

    override fun onBindViewHolder(holder: PhotoCollectionsListHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoCollectionsListHolder {
        return PhotoCollectionsListHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_collection_photo, parent, false)

        )
    }

    class PhotoCollectionsListHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCollectionPhotoBinding.bind(view)


        fun bind(
            photoCollection: CollectionDetail
        ) {
            with(binding) {
                textViewAccount.text = photoCollection.user.userName
                textViewProfileName.text = photoCollection.user.name
                textViewLikes.text = photoCollection.likes
            }
            Glide.with(itemView)
                .load(photoCollection.urlPhoto.urlRegular)
                .centerCrop()
                .override(Target.SIZE_ORIGINAL)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imageViewPhoto)
            Glide.with(itemView)
                .load(photoCollection.user.profileImageUrl.urlSmall)
                .circleCrop()
                .into(binding.imageViewAccount)
        }

    }

    class PhotoCollectionDiffUtilCallback : DiffUtil.ItemCallback<CollectionDetail>() {
        override fun areItemsTheSame(
            oldItem: CollectionDetail,
            newItem: CollectionDetail
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CollectionDetail,
            newItem: CollectionDetail
        ): Boolean {
            return oldItem == newItem
        }

    }
}