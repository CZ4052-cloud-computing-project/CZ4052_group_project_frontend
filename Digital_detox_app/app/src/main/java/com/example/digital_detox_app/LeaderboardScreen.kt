
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
import com.example.digital_detox_app.R
import com.example.digital_detox_app.ui.theme.Digital_detox_appTheme

// Sample data class representing a leaderboard entry
data class LeaderboardEntry(val rank: Int, val playerName: String, val score: Int)

// Sample list of leaderboard entries
val sampleLeaderboard = listOf(
    LeaderboardEntry(1, "Player1", 100),
    LeaderboardEntry(2, "Player2", 90),
    LeaderboardEntry(3, "Player3", 80),
    LeaderboardEntry(4, "Player4", 70),
    LeaderboardEntry(5, "Player5", 60),
    LeaderboardEntry(6, "Player6", 50)
)

@Composable
fun LeaderboardScreen(leaderboard: List<LeaderboardEntry> = sampleLeaderboard) {

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
            leaderboard.forEach { entry ->
                LeaderboardEntryItem(entry = entry)
            }
        }
}

@Composable
fun LeaderboardEntryItem(entry: LeaderboardEntry) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = entry.rank.toString(),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = entry.playerName,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = entry.score.toString(),
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

@Preview
@Composable
fun PreviewLeaderboardPage() {
    LeaderboardScreen(leaderboard = sampleLeaderboard)
}
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
