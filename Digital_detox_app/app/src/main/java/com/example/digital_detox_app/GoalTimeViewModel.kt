package com.example.digital_detox_app

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GoalTimeViewModel : ViewModel() {
    // MutableStateFlow to hold the goal time
    private val _goalTime = MutableStateFlow(0L)

    // Expose the StateFlow as immutable LiveData to observe changes
    val goalTime: StateFlow<Long> = _goalTime

    // Function to set the goal time
    fun setGoalTime(timeInSeconds: Long) {
        _goalTime.value = timeInSeconds
    }
}