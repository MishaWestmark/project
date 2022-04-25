package com.westmark.rickandmorty.presentation.ui.detailInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.westmark.rickandmorty.models.CharacterDetail
import com.westmark.rickandmorty.presentation.data.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: RepositoryImpl) : ViewModel() {
    private val detailInfoMutableLiveData = MutableLiveData<CharacterDetail>()
    val detailInfoLiveData: LiveData<CharacterDetail> = detailInfoMutableLiveData
    fun getDetailCharacter(id: String) {
        viewModelScope.launch {
            detailInfoMutableLiveData.postValue(repository.getDetailCharacter(id))
        }
    }
}