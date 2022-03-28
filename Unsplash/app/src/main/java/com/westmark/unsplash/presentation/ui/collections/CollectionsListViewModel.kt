package com.westmark.unsplash.presentation.ui.collections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.westmark.unsplash.presentation.data.CollectionsEntity
import com.westmark.unsplash.presentation.ui.collections.data.RepositoryCollectionsImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionsListViewModel @Inject constructor(
    private val repository: RepositoryCollectionsImpl
) : ViewModel() {
    private val collectionsMutableStateFlow = MutableStateFlow<PagingData<CollectionsEntity>>(
        PagingData.empty()
    )
    val collectionsStateFlow: StateFlow<PagingData<CollectionsEntity>> = collectionsMutableStateFlow

    init {
        viewModelScope.launch {
            repository.getCollectionResult()
                .cachedIn(viewModelScope)
                .collectLatest {
                    collectionsMutableStateFlow.value = it
                }
        }
    }
}