package ru.westmark.app42.screens.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.westmark.app42.data.Employers
import ru.westmark.app42.data.Repository
import ru.westmark.app42.network.ConnectionManager
import javax.inject.Inject

@HiltViewModel
class EmployerViewModel @Inject constructor(private val connectionManager: ConnectionManager) :
    ViewModel() {
    private val _listMutableStateFlow =
        MutableStateFlow<List<Employers>>(emptyList())
    val listStateFlow: StateFlow<List<Employers>> = _listMutableStateFlow
    private val networkMutableLiveData = MutableLiveData<Boolean>()
    val networkLiveData: LiveData<Boolean> = networkMutableLiveData
    private val repository = Repository()

    init {
        viewModelScope.launch {
            connectionManager.observeNetworkState().collect {
                networkMutableLiveData.value = it
            }
        }
    }

    suspend fun getEmployersList(name: String?, isVacancies: Boolean?, type: String?) {
        viewModelScope.launch {
            repository.getListEmployers(name = name, type = type, isVacancies = isVacancies)
                .collect {
                    _listMutableStateFlow.value = it
                }
        }

    }
}