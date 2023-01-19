package com.example.apptest

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {
    private val _cardInfoStatFlow = MutableStateFlow<CardInformation?>(null)
    val cardInfoStateFlow: StateFlow<CardInformation?> = _cardInfoStatFlow
    val repos = Repository()
    fun getCardInfo(number: String) {
        repos.getInfoCard(number) { cardInformation ->
            _cardInfoStatFlow.value = cardInformation
        }
    }
}