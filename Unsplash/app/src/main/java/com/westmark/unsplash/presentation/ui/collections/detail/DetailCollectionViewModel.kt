package com.westmark.unsplash.presentation.ui.collections.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.westmark.unsplash.presentation.data.CollectionDetail
import com.westmark.unsplash.presentation.ui.collections.data.RepositoryCollections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailCollectionViewModel @Inject constructor(private val repository: RepositoryCollections) :
    ViewModel() {
    private val photoCollectionMutableStateFlow = MutableStateFlow<PagingData<CollectionDetail>>(
        PagingData.empty()
    )
    val photosCollectionSateFlow: StateFlow<PagingData<CollectionDetail>> =
        photoCollectionMutableStateFlow

    suspend fun getPhotosCollection(id: String) {
        viewModelScope.launch {
            repository.getCollectionPhotos(id)
                .collectLatest {
                    photoCollectionMutableStateFlow.value = it
                    Log.d("CollectionDetail", it.toString())
                }
        }
    }
}