
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.digital_detox_app.LeaderBoardUiState
import com.example.digital_detox_app.R
import com.example.digital_detox_app.data.LeaderBoardEntry
import com.example.digital_detox_app.data.LeaderBoardResponse
import com.example.digital_detox_app.ui.theme.Digital_detox_appTheme

@Composable
fun LeaderboardScreen(
    leaderBoardUiState: LeaderBoardUiState,
    modifier: Modifier
) {
    when (leaderBoardUiState) {
        is LeaderBoardUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is LeaderBoardUiState.Success -> ResultantScreen(
            leaderBoardUiState.leaderboardInfo, modifier = modifier.fillMaxWidth()
        )
        is LeaderBoardUiState.Error -> ErrorScreen( modifier = modifier.fillMaxSize())
    }
}

@Composable
fun ResultantScreen(leaderboard: LeaderBoardResponse, modifier: Modifier) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Leaderboard",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(16.dp)
            )
//            Text(text = leaderboard)
            leaderboard.leaderboard.forEach { entry ->
                LeaderboardEntryItem(entry = entry)
            }
        }
}

@Composable
fun LeaderboardEntryItem(entry: LeaderBoardEntry) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = entry.position.toString(),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = entry.username,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = entry.totalDurationOfSessions.toString(),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
    }
}

//@Preview
//@Composable
//fun PreviewLeaderboardPage() {
//    ResultantScreen()
//}
@Preview
@Composable
fun ErrorScreenPreview() {
    Digital_detox_appTheme {
        ErrorScreen()
    }
}
@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    Digital_detox_appTheme {
        LoadingScreen()
    }
}
