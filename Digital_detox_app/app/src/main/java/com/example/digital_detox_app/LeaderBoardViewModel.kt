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
import com.example.digital_detox_app.data.DigitalDetoxLeaderboardRepository
import com.example.digital_detox_app.data.LeaderBoardResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface LeaderBoardUiState {
    data class Success(val leaderboardInfo: LeaderBoardResponse) : LeaderBoardUiState
    object Error : LeaderBoardUiState
    object Loading : LeaderBoardUiState
}
class LeaderBoardViewModel(
    private val leaderBoardRepository: DigitalDetoxLeaderboardRepository
): ViewModel() {
    var leaderboardUiState: LeaderBoardUiState by mutableStateOf(LeaderBoardUiState.Loading)
        private set
    init {
        getLoaderBoardInfo()
    }

    fun getLoaderBoardInfo() {
        viewModelScope.launch {
            leaderboardUiState = LeaderBoardUiState.Loading
            leaderboardUiState = try {
                val listResult = leaderBoardRepository.getLeaderboardInfo()
                LeaderBoardUiState.Success(
//                    "Success: ${listResult.size} entries retrieved"
                    listResult
                )
            } catch (e: IOException) {
                    LeaderBoardUiState.Error
            } catch (e: HttpException) {
                    LeaderBoardUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as LeaderBoardApplication)
                val leaderBoardRepository = application.container.leaderboardRepository
                LeaderBoardViewModel(leaderBoardRepository = leaderBoardRepository)
            }
        }
    }
}

