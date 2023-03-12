package com.example.testappeff.screens.main.page1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testappeff.R
import com.example.testappeff.databinding.FragmentPage1Binding
import com.example.testappeff.screens.main.page1.adapter.FlashSaleAdapter
import com.example.testappeff.screens.main.page1.adapter.LatestItemsAdapter
import com.example.testappeff.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Page1Fragment : Fragment(R.layout.fragment_page1) {
    private val binding: FragmentPage1Binding by viewBinding()
    private val viewModel by viewModels<Page1ViewModel>()
    private var itemsLatestAdapter: LatestItemsAdapter by autoCleared()
    private var itemsFlashSaleAdapter: FlashSaleAdapter by autoCleared()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.itemsLatestLivedata.observe(viewLifecycleOwner) {
            itemsLatestAdapter.updateList(it)
        }
        viewModel.itemsFlashSaleLivedata.observe(viewLifecycleOwner) {
            itemsFlashSaleAdapter.updateList(it)
        }
        initList()


    }

    private fun initList() {
        itemsLatestAdapter = LatestItemsAdapter {

        }
        itemsFlashSaleAdapter = FlashSaleAdapter { id ->
            findNavController().navigate(Page1FragmentDirections.actionPage1FragmentToPage2Fragment())
        }
        with(binding.recyclerLatest) {
            adapter = itemsLatestAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        with(binding.recyclerFlashSale) {
            adapter = itemsFlashSaleAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }
}