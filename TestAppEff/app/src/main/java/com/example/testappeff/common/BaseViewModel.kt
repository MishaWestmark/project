package com.example.testappeff.common

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

abstract class BaseViewModel<STATE>(initialState: STATE) : ViewModel() {
    protected val mutableStateFlow: MutableStateFlow<STATE> by lazy { MutableStateFlow(initialState) }
    val stateFlow: StateFlow<STATE> = mutableStateFlow.asStateFlow()
    protected val state: STATE get() = stateFlow.value

    protected val sideEffectSharedFlow = MutableSharedFlow<BaseSideEffect>()
    val sideEffect: SharedFlow<BaseSideEffect> = sideEffectSharedFlow.asSharedFlow()


    private val handler = CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }

    protected inline fun updateState(transform: STATE.() -> STATE) {
        mutableStateFlow.value = transform.invoke(state)
    }

    protected fun launch(block: suspend CoroutineScope.() -> Unit): Job =
        viewModelScope.launch(handler) { block(this) }

    protected fun onError(throwable: Throwable) {
        if (throwable !is CancellationException) {
            Log.e(throwable.message.orEmpty(), throwable.message.toString())
        }
    }
}