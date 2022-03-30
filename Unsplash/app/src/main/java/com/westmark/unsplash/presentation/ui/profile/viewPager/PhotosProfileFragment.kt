package com.westmark.unsplash.presentation.ui.profile.viewPager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.westmark.unsplash.R
import com.westmark.unsplash.databinding.FragmentProfilePhotosBinding
import com.westmark.unsplash.presentation.ui.photoList.adapter.autoCleared
import com.westmark.unsplash.presentation.ui.profile.viewPager.adapter.PhotosProfilePagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotosProfileFragment : Fragment(R.layout.fragment_profile_photos) {
    private val binding: FragmentProfilePhotosBinding by viewBinding()
    private val viewModel: PhotosProfileViewModel by viewModels()
    private var adapterPhotos: PhotosProfilePagingAdapter by autoCleared()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        bindViewModel()
    }

    private fun initList() {
        adapterPhotos = PhotosProfilePagingAdapter()
        with(binding.recyclerView) {
            adapter = adapterPhotos
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun bindViewModel() {
        lifecycleScope.launch {
            viewModel.userPhotoStateFlow.collectLatest {
                adapterPhotos.submitData(it)
            }
        }
    }
}