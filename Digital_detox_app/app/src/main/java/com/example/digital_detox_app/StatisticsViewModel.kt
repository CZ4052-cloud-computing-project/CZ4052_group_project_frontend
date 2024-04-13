package com.example.digital_detox_app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.digital_detox_app.data.DigitalDetoxStatisticsRepository
import com.example.digital_detox_app.data.ProfileDataSource
import com.example.digital_detox_app.data.StatisticEntry
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter


sealed interface StatisticsUiState {
    data class Success(val statisticInfo: List<StatisticEntry>) : StatisticsUiState
    object Error : StatisticsUiState
    object Loading : StatisticsUiState
}
class StatisticsViewModel(
    private val statisticsRepository: DigitalDetoxStatisticsRepository,
    private val userProfileDataSource: ProfileDataSource
): ViewModel() {
    var statisticsUiState: StatisticsUiState by mutableStateOf(StatisticsUiState.Loading)
        private set
    init {
        getStatisticsInfo()
    }

    fun getStatisticsInfo() {
        // TODO: Fix linter error.
        val endDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE)
        val startDate = LocalDate.now().minusDays(7).format(DateTimeFormatter.ISO_DATE)

        viewModelScope.launch {
            statisticsUiState = StatisticsUiState.Loading
            statisticsUiState = try {
                val listResult = statisticsRepository.getStatistics(
                    username = userProfileDataSource.getUserProfile(),
                    startDate,
                    endDate
                )
                StatisticsUiState.Success(
                    listResult
                )
            } catch (e: IOException) {
                    StatisticsUiState.Error
            } catch (e: HttpException) {
                    StatisticsUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as DigitalDetoxApplication)
                val statisticsRepository = application.container.statisticsRepository
                val userProfileDataSource = application.container.userProfileDataSource
                StatisticsViewModel(
                    statisticsRepository = statisticsRepository,
                    userProfileDataSource = userProfileDataSource
                )
            }
        }
    }
}

