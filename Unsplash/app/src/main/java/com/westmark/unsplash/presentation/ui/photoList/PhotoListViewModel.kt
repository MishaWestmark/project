package com.westmark.unsplash.presentation.ui.photoList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.westmark.unsplash.presentation.data.PhotoEntity
import com.westmark.unsplash.presentation.ui.photoList.data.RepositoryPhotoListImpl
import com.westmark.unsplash.networking.ConnectionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoListViewModel @Inject constructor(
    private val repository: RepositoryPhotoListImpl,
    private val connectionManager: ConnectionManager
) : ViewModel() {
    private val photoMutableStateFlow =
        MutableStateFlow<PagingData<PhotoEntity>>(PagingData.empty())
    val photoStateFlow: StateFlow<PagingData<PhotoEntity>>
        get() = photoMutableStateFlow
    private val photoDatabaseMutableStateFlow = MutableStateFlow<List<PhotoEntity>>(emptyList())
    val photoDatabaseStateFlow: StateFlow<List<PhotoEntity>>
        get() = photoDatabaseMutableStateFlow
    private val networkMutableLiveData = MutableLiveData<Boolean>()
    val networkLiveData: LiveData<Boolean> = networkMutableLiveData

    init {
        viewModelScope.launch {
            connectionManager.observeNetworkState()
                .collect {
                    networkMutableLiveData.postValue(it)
                }
        }
        viewModelScope.launch {
            getPhotoResult()
                .cachedIn(viewModelScope)
                .collectLatest {
                    photoMutableStateFlow.value = it
                }

//                .catch {
//                    repository.getPhotoFromDatabase().collectLatest {
//                        photoMutableStateFlow.value = it
//                    }
        }


    }

    private fun getPhotoResult(): Flow<PagingData<PhotoEntity>> {
        return repository.getPhotoResult().cachedIn(viewModelScope)
    }
}