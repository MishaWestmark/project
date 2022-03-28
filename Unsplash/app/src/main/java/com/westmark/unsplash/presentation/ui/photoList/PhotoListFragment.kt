package com.westmark.unsplash.presentation.ui.photoList

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.westmark.unsplash.R
import com.westmark.unsplash.databinding.FragmentPhotoListBinding
import com.westmark.unsplash.presentation.ui.photoList.adapter.PhotoLoadStateAdapter
import com.westmark.unsplash.presentation.ui.photoList.adapter.PhotoPagingAdapter
import com.westmark.unsplash.presentation.ui.photoList.adapter.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PhotoListFragment : Fragment(R.layout.fragment_photo_list) {
    private val binding: FragmentPhotoListBinding by viewBinding()
    private val viewModel: PhotoListViewModel by viewModels()
    private var adapterPhotoPaging: PhotoPagingAdapter by autoCleared()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListPhoto()
        bindViewModel()
    }

    private fun initListPhoto() {
        adapterPhotoPaging = PhotoPagingAdapter{id->
            findNavController().navigate(PhotoListFragmentDirections.actionPhotoListFragmentToDetailFragment(id))
        }
        with(binding.recyclerViewPhotos) {
            adapter =
                adapterPhotoPaging.withLoadStateHeaderAndFooter(footer = PhotoLoadStateAdapter { bindViewModel() },
                    header = PhotoLoadStateAdapter {})
            adapterPhotoPaging.addLoadStateListener { loadState ->
                binding.recyclerViewPhotos.isVisible =
                    loadState.source.refresh is LoadState.NotLoading

            }
            val dividerItemDecoration =
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
            layoutManager =
                object : StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL) {
                    override fun smoothScrollToPosition(
                        recyclerView: RecyclerView,
                        state: RecyclerView.State,
                        position: Int
                    ) {
                        val smoothScroller: LinearSmoothScroller =
                            object : LinearSmoothScroller(requireContext()) {
                                private val SPEED = 10000f // Change this value (default=25f)
                                override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                                    return SPEED / displayMetrics.densityDpi
                                }
                            }
                        smoothScroller.targetPosition = recyclerView.adapter!!.itemCount
//                    smoothScroller.setTargetPosition(position); //This Will Scroll Recyclerview From Top to Bottom And Automatically Bottom To top
                        startSmoothScroll(smoothScroller)
                    }
                }
        }
    }

    private fun bindViewModel() {
        viewModel.networkLiveData.observe(viewLifecycleOwner) { isNetworkAvailable ->
            if (!isNetworkAvailable) {
                val snackBar = Snackbar.make(
                    binding.photoListContainerFragment,
                    "Ошибка сетиБ загрузка продолжится позже",
                    Snackbar.LENGTH_LONG
                )
                snackBar.setAction("Close", View.OnClickListener {
                    snackBar.dismiss()
                })
                snackBar.show()
            }
        }
        lifecycleScope.launch {
            viewModel.photoStateFlow.collectLatest {
                adapterPhotoPaging.submitData(it)
            }
        }
    }
}