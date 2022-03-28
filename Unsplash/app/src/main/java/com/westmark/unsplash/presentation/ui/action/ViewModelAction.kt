package com.westmark.unsplash.presentation.ui.action

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.westmark.unsplash.presentation.ui.action.data.RepositoryActionImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelAction @Inject constructor(
    private val repositoryAction: RepositoryActionImpl
) : ViewModel() {
    private val isFirstEntryMutableLiveData = MutableLiveData(repositoryAction.isFirstEntry())

    val isFirstEntry: LiveData<Boolean> = isFirstEntryMutableLiveData

    fun addFlagAfterFirstEntry() {
        repositoryAction.addFlagAfterFirstEntry()
    }

    fun containsAccessToken(): Boolean {
        return repositoryAction.containsAccessToken()
    }
}