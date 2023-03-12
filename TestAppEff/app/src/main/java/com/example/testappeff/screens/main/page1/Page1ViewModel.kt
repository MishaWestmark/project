package com.example.testappeff.screens.main.page1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Repository
import com.example.data.network.FlashSaleItems
import com.example.data.network.LatestItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Page1ViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _itemLatestLiveData = MutableLiveData<LatestItems>()
    val itemsLatestLivedata: LiveData<LatestItems> = _itemLatestLiveData

    private val _itemFlashSaleLiveData = MutableLiveData<FlashSaleItems>()
    val itemsFlashSaleLivedata: LiveData<FlashSaleItems> = _itemFlashSaleLiveData

    init {
        viewModelScope.launch {
            repository.getLatestItems() {
                _itemLatestLiveData.value = it
                Log.e("Latest", it.toString())
            }
            repository.getFlashSaleItems {
                _itemFlashSaleLiveData.value = it
                Log.e("FlashSale", it.toString())
            }
        }
    }
}