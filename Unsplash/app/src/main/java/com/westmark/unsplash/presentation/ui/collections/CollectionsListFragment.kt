package com.westmark.unsplash.presentation.ui.collections

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.westmark.unsplash.R
import com.westmark.unsplash.databinding.FragmentCollectionsListBinding
import com.westmark.unsplash.presentation.ui.collections.adapter.CollectionsPagingAdapter
import com.westmark.unsplash.presentation.ui.photoList.adapter.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CollectionsListFragment : Fragment(R.layout.fragment_collections_list) {
    private val binding: FragmentCollectionsListBinding by viewBinding()
    private var adapterCollections: CollectionsPagingAdapter by autoCleared()
    private val viewModel: CollectionsListViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        bindViewModel()
    }

    private fun initList() {
        adapterCollections = CollectionsPagingAdapter { idCollection ->
            findNavController().navigate(
                CollectionsListFragmentDirections.actionCollectionsFragment2ToDetailCollectionFragment(
                    idCollection
                )
            )
        }
        with(binding.recyclerViewCollections) {
            adapter = adapterCollections
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun bindViewModel() {
        lifecycleScope.launch {
            viewModel.collectionsStateFlow.collectLatest {
                adapterCollections.submitData(it)
            }
        }
    }

}