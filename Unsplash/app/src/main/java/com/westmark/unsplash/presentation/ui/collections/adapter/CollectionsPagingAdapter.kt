package com.westmark.unsplash.presentation.ui.collections.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.westmark.unsplash.R
import com.westmark.unsplash.databinding.ItemCollectionBinding
import com.westmark.unsplash.presentation.data.CollectionsEntity

class CollectionsPagingAdapter(private val onItemClick: (id: String) -> Unit) :
    PagingDataAdapter<CollectionsEntity, CollectionsPagingAdapter.CollectionsListHolder>(
        CollectionDiffUtilCallback()
    ) {

    override fun onBindViewHolder(holder: CollectionsListHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CollectionsListHolder {
        return CollectionsListHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_collection, parent, false),
            onItemClick
        )
    }

    class CollectionsListHolder(
        view: View,
        private val onItemClick: (id: String) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCollectionBinding.bind(view)
        private var currentId: String? = null

        init {
            with(view) {
                setOnClickListener {
                    currentId?.let(onItemClick)
                }
            }
        }

        fun bind(
            collection: CollectionsEntity
        ) {
            currentId = collection.id
            with(binding) {
                textViewAccount.text = "@${collection.user.userName}"
                textViewCountPhotos.text = "Количество фото: ${collection.totalPhotos}"
                textViewDescription.text = collection.title
                textViewProfileName.text = collection.user.name
            }
            Glide.with(itemView)
                .load(collection.coverPhotoUrl.photoUrl.urlRegular)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imageViewCollection)
            Glide.with(itemView)
                .load(collection.user.profileImage.urlSmall)
                .circleCrop()
                .into(binding.imageViewAccount)
        }

    }

    class CollectionDiffUtilCallback : DiffUtil.ItemCallback<CollectionsEntity>() {
        override fun areItemsTheSame(
            oldItem: CollectionsEntity,
            newItem: CollectionsEntity
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CollectionsEntity,
            newItem: CollectionsEntity
        ): Boolean {
            return oldItem == newItem
        }

    }
}