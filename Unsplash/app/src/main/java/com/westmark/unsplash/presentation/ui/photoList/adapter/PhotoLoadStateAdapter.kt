package com.westmark.unsplash.presentation.ui.photoList.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.westmark.unsplash.R
import com.westmark.unsplash.databinding.ItemProgressbarBinding

class PhotoLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<PhotoLoadStateAdapter.PhotoLoadViewHolder>() {

    override fun onBindViewHolder(holder: PhotoLoadViewHolder, loadState: LoadState) {
        holder.progressbar().isVisible = loadState is LoadState.Loading
        holder.btnRetry().isVisible = loadState !is LoadState.Loading
        holder.btnRetry().setOnClickListener {
            retry.invoke()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): PhotoLoadViewHolder {
        return PhotoLoadViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_progressbar, parent, false)
        )
    }

    class PhotoLoadViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {
        private val binding = ItemProgressbarBinding.bind(view)
        fun progressbar(): ProgressBar {
            return binding.progressbar
        }

        fun btnRetry(): Button {
            return binding.btnRetry
        }
    }
}