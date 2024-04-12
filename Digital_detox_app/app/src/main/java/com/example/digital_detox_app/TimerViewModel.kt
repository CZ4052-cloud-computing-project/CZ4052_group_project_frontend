package com.example.digital_detox_app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.digital_detox_app.data.DigitalDetoxTimerRepository
import com.example.digital_detox_app.data.TimerInfo
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

sealed interface TimerUiState {
    data class Success(val timerInfo: Response<Unit>) : TimerUiState
    object Error : TimerUiState
    object Loading : TimerUiState
}

class TimerViewModel(
    private val timerRepository: DigitalDetoxTimerRepository
) : ViewModel() {
    private val _timer = MutableStateFlow(0L)
    val timer = _timer.asStateFlow()
    private var timerJob: Job? = null

    var timerUiState: TimerUiState by mutableStateOf(TimerUiState.Loading)
        private set

    fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                _timer.value++
            }
        }
    }

    fun pauseTimer() {
        timerJob?.cancel()
    }

    fun stopTimer() {
        var timerInfo = TimerInfo(
            // TODO: resolve Call requires API level 26 (current min is 24): java.time.LocalDate#now issue
            date = LocalDate.now().format(DateTimeFormatter.ISO_DATE),
            username = "johnny_boy",
            duration = _timer.value.toInt()
        )
        _timer.value = 0
        timerJob?.cancel()

        viewModelScope.launch {
            timerUiState = TimerUiState.Loading
            timerUiState = try {
                val listResult = timerRepository.sendTimerInfo(timerInfo)
                TimerUiState.Success(
                    listResult
                )
            } catch (e: IOException) {
                TimerUiState.Error
            } catch (e: HttpException) {
                TimerUiState.Error
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as DigitalDetoxApplication)
                val timerRepository = application.container.timerRepository
                TimerViewModel(timerRepository = timerRepository)
            }
        }
    }
}