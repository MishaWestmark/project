package com.westmark.unsplash.presentation.ui.profile.viewPager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.westmark.unsplash.R
import com.westmark.unsplash.databinding.FragmentProfileLikedPhotosBinding
import com.westmark.unsplash.presentation.ui.photoList.adapter.autoCleared
import com.westmark.unsplash.presentation.ui.profile.viewPager.adapter.PhotosProfilePagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotosLikedProfileFragment : Fragment(R.layout.fragment_profile_liked_photos) {
    private val binding: FragmentProfileLikedPhotosBinding by viewBinding()
    private val viewModel: PhotosLikedProfileViewModel by viewModels()
    private var adapterLikedPhotos: PhotosProfilePagingAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        bindViewModel()
    }

    private fun initList() {
        adapterLikedPhotos = PhotosProfilePagingAdapter()
        with(binding.recyclerView) {
            adapter = adapterLikedPhotos
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun bindViewModel() {
        lifecycleScope.launch {
            viewModel.userLikedStateFlow.collectLatest {
                adapterLikedPhotos.submitData(it)
            }
        }
    }
}