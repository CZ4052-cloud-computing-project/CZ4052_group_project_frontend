
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.digital_detox_app.ProfileViewModel
import com.example.digital_detox_app.R
import com.example.digital_detox_app.SessionButtonViewModel
import com.example.digital_detox_app.TimerUiState
import com.example.digital_detox_app.TimerViewModel

@Composable
fun StartDetoxScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    sessionButtonViewModel: SessionButtonViewModel = viewModel(),
    timerViewModel: TimerViewModel = viewModel(),
    timerUiState: TimerUiState,
    userProfileViewModel: ProfileViewModel
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            UsernameTextField(userProfileViewModel)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            TimerScreenContent(timerViewModel)

            val isTimerRunning by sessionButtonViewModel.isTimerRunning.collectAsState()
            Button(
                onClick = {
                    sessionButtonViewModel.setToggle()
                    if (isTimerRunning) {
                        timerViewModel.stopTimer()
                    }
                    else {
                        timerViewModel.startTimer()
                    }
                },
                modifier = modifier.widthIn(min = 250.dp)
            ) {
                Text(text = if (isTimerRunning) stringResource(id = R.string.endTimerButton) else stringResource(id = R.string.startTimerButton))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            UploadResults(timerUiState)
        }
    }
}

@Composable
fun UploadResults(
    timerUiState: TimerUiState,
) {
    when (timerUiState) {
        is TimerUiState.Initial -> Text("")
        is TimerUiState.Loading -> Text("loading...")
        is TimerUiState.Success -> Text("Upload Successful")
        is TimerUiState.Error -> Text("Upload failed")
    }
}


@Composable
fun TimerScreenContent(timerViewModel: TimerViewModel) {
    val timerValue by timerViewModel.timer.collectAsState()
    TimerScreen(
        timerValue = timerValue,
    )
}
fun Long.formatTime(): String {
    val hours = this / 3600
    val minutes = (this % 3600) / 60
    val remainingSeconds = this % 60
    return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds)
}
@Composable
fun TimerScreen(
    timerValue: Long
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = timerValue.formatTime(), fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun UsernameTextField(
    userProfileViewModel: ProfileViewModel
) {
    val username by userProfileViewModel.userProfile.collectAsState()
    TextField(
        value = username,
        onValueChange = {
            userProfileViewModel.userProfile.value = it
            userProfileViewModel.updateUserProfile(it)
        },
        label = { Text("Username") }
    )
}