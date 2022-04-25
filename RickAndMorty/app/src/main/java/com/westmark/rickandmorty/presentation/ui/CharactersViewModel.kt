package com.westmark.rickandmorty.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.westmark.rickandmorty.models.Response
import com.westmark.rickandmorty.presentation.data.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: RepositoryImpl
) : ViewModel() {
    private val charactersMutableStateFlow = MutableStateFlow<PagingData<Response.Character>>(
        PagingData.empty()
    )
    val charactersStateFlow: StateFlow<PagingData<Response.Character>> = charactersMutableStateFlow

    init {
        viewModelScope.launch {
            repository.getCharactersResults()
                .cachedIn(viewModelScope)
                .collectLatest {
                    charactersMutableStateFlow.value = it
                }
        }
    }
}