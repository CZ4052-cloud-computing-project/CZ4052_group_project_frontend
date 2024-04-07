
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.digital_detox_app.ui.theme.Digital_detox_appTheme

@Composable
fun StartDetoxScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    var buttonText by remember { mutableStateOf("Start Detox session") }
    var toggleFlag = remember { mutableStateOf(false) }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Button(
                onClick = {
                    buttonText = if (buttonText == "Start Detox session") "End Detox Session" else "Start Detox session"
                },
                modifier = modifier.widthIn(min = 250.dp)
            ) {
                Text(buttonText)
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