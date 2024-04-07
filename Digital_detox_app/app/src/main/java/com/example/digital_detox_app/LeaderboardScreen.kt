
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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

@Preview
@Composable
fun PreviewLeaderboardPage() {
    LeaderboardScreen(leaderboard = sampleLeaderboard)
}
