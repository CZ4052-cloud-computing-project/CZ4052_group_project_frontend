
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.digital_detox_app.R
import com.example.digital_detox_app.SessionButtonViewModel
import com.example.digital_detox_app.TimerViewModel
import com.example.digital_detox_app.ui.theme.Digital_detox_appTheme

@Composable
fun StartDetoxScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    sessionButtonViewModel: SessionButtonViewModel = viewModel(),
    timerViewModel: TimerViewModel = viewModel(),
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

@Preview(showBackground = true)
@Composable
fun StartDetoxScreenPreview() {
    Digital_detox_appTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            StartDetoxScreen()
        }
    }
}
