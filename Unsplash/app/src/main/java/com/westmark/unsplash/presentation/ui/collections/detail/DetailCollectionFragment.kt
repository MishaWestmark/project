package com.westmark.unsplash.presentation.ui.collections.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.westmark.unsplash.R
import com.westmark.unsplash.databinding.FragmentCollectionsListBinding
import com.westmark.unsplash.databinding.FragmentDetailCollectionBinding
import com.westmark.unsplash.databinding.FragmentDetailPhotoBinding
import com.westmark.unsplash.presentation.ui.collections.detail.adapter.PhotoCollectionPagingAdapter
import com.westmark.unsplash.presentation.ui.photoList.adapter.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailCollectionFragment : Fragment(R.layout.fragment_detail_collection) {
    private val binding: FragmentDetailCollectionBinding by viewBinding()
    private val args: DetailCollectionFragmentArgs by navArgs()
    private var adapterPhoto: PhotoCollectionPagingAdapter by autoCleared()
    private val viewModel: DetailCollectionViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        bindViewModel()
    }

    private fun initList() {
        adapterPhoto = PhotoCollectionPagingAdapter()
        with(binding.recyclerViewCollectionPhotos) {
            adapter = adapterPhoto
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun bindViewModel() {
        lifecycleScope.launch {
            viewModel.getPhotosCollection(args.id)
            viewModel.photosCollectionSateFlow.collectLatest {
                adapterPhoto.submitData(it)
            }
        }
    }
}