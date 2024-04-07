
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.digital_detox_app.R
import com.example.digital_detox_app.SessionButtonViewModel
import com.example.digital_detox_app.ui.theme.Digital_detox_appTheme

@Composable
fun StartDetoxScreen(
    navController: NavHostController = rememberNavController(),
    sessionButtonViewModel: SessionButtonViewModel = viewModel(),
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        val isTimerRunning by sessionButtonViewModel.isTimerRunning.collectAsState()

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Button(
                onClick = {
                    sessionButtonViewModel.setToggle()
                },
                modifier = modifier.widthIn(min = 250.dp)
            ) {
                Text(text = if (isTimerRunning) stringResource(id = R.string.endTimerButton) else stringResource(id = R.string.startTimerButton))
            }
        }
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