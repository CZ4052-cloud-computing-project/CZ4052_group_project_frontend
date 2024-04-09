package com.example.digital_detox_app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digital_detox_app.network.DigitalDetoxApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface LeaderBoardUiState {
    data class Success(val leaderboardInfo: String) : LeaderBoardUiState
    object Error : LeaderBoardUiState
    object Loading : LeaderBoardUiState
}
class LeaderBoardViewModel: ViewModel() {
    var leaderboardUiState: LeaderBoardUiState by mutableStateOf(LeaderBoardUiState.Loading)
        private set
    init {
        getLoaderBoardInfo()
    }

    fun getLoaderBoardInfo() {
        viewModelScope.launch {
            leaderboardUiState = LeaderBoardUiState.Loading
            leaderboardUiState = try {
                val listResult = DigitalDetoxApi.retrofitService.getLeaderBoardInfo()
                LeaderBoardUiState.Success(
//                    "Success: ${listResult.size} entries retrieved"
                    "Successful retrieval: ${listResult}"
                )
            } catch (e: IOException) {
                    LeaderBoardUiState.Error
            } catch (e: HttpException) {
                    LeaderBoardUiState.Error
            }
        }
    }
}