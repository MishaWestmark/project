package com.westmark.unsplash.presentation.ui.photoList.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.westmark.unsplash.presentation.data.PhotoDetail
import com.westmark.unsplash.presentation.ui.photoList.data.RepositoryPhotoListImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: RepositoryPhotoListImpl
) : ViewModel() {
    private val photoByIdMutableLiveData = MutableLiveData<PhotoDetail>()
    val photoByIdLiveData: LiveData<PhotoDetail>
        get() = photoByIdMutableLiveData


    fun getPhotoById(id: String) {
        viewModelScope.launch {
            val photoDetail = repository.getPhotoById(id)
            photoByIdMutableLiveData.postValue(photoDetail)
        }
    }

}