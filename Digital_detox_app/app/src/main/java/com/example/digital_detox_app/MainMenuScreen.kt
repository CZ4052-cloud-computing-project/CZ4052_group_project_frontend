
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.digital_detox_app.DigitalDetoxApp
import com.example.digital_detox_app.DigitalDetoxScreen
import com.example.digital_detox_app.R
import com.example.digital_detox_app.ui.theme.Digital_detox_appTheme

@Composable
fun MainMenuScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(R.drawable.mainmenuimage),
            contentDescription = null
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            MainMenuButton(
                labelResourceId = R.string.start_detox_session,
                onClick = {navController.navigate(DigitalDetoxScreen.RunningSession.name)}
            )
            MainMenuButton(
                labelResourceId = R.string.view_leaderboard,
                onClick = {navController.navigate(DigitalDetoxScreen.LeaderBoard.name)}
            )
            MainMenuButton(
                labelResourceId = R.string.view_analysis,
                onClick = {navController.navigate(DigitalDetoxScreen.Analysis.name)}
            )
            MainMenuButton(
                labelResourceId = R.string.set_goal,
                onClick = {navController.navigate(DigitalDetoxScreen.SetGoal.name)}
            )
        }
    }
}

@Composable
fun MainMenuButton(
    @StringRes labelResourceId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.widthIn(min = 250.dp)
    ) {
        Text(stringResource(labelResourceId))
    }
}

@Preview(showBackground = true)
@Composable
fun MainMenuScreenPreview() {
    Digital_detox_appTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            DigitalDetoxApp()
        }
    }
}