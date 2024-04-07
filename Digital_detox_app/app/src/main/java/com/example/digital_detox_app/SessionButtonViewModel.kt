package com.example.digital_detox_app

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SessionButtonViewModel: ViewModel() {
//    var isTimerRunning: MutableState<Boolean> = mutableStateOf(false)

    private val _isTimerRunning = MutableStateFlow(false)
    val isTimerRunning: StateFlow<Boolean> = _isTimerRunning.asStateFlow()

    fun setToggle() {
        _isTimerRunning.update { currentState ->
            !currentState
        }
    }
}