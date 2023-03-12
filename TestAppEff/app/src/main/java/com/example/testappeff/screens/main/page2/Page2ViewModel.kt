package com.example.testappeff.screens.main.page2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Repository
import com.example.data.network.DetailItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Page2ViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _detailInfoLiveData = MutableLiveData<DetailItem>()
    val detailInfoLiveData: LiveData<DetailItem> = _detailInfoLiveData

    init {
        viewModelScope.launch {
            repository.getDetailInfo {
                _detailInfoLiveData.value = it
            }
        }
    }
}