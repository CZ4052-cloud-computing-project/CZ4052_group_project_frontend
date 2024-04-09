import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.digital_detox_app.ui.theme.Digital_detox_appTheme

@Composable
fun TotalDetoxTimeCard(totalDetoxTime: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Total Detox Time Today",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = totalDetoxTime,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun BarGraph(dataPoints: List<Pair<String, Int>>) {
    val maxValue = dataPoints.maxOf { it.second }
    val maxHeight = 200.dp // Maximum height for the bar graph
    val barWidth = 30.dp

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        dataPoints.forEach { (day, detoxTime) ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = day)
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .size(barWidth, (detoxTime.toFloat() / maxValue.toFloat()) * maxHeight)
                        .background(Color.Blue)
                )
            }
        }
    }
}

@Composable
fun AnalysisPage(totalDetoxTime: String, dataPoints: List<Pair<String, Int>>) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TotalDetoxTimeCard(totalDetoxTime = totalDetoxTime)
            Spacer(modifier = Modifier.height(16.dp))
            BarGraph(dataPoints = dataPoints)
        }

}

@Composable
fun AnalysisScreen() {
    // Dummy data
    val totalDetoxTime = "2 hours 30 minutes"
    val dataPoints = listOf(
        "Mon" to 120, // 2 hours
        "Tue" to 180, // 3 hours
        "Wed" to 90    // 1 hour 30 minutes
    )

    AnalysisPage(totalDetoxTime = totalDetoxTime, dataPoints = dataPoints)
}

@Preview(showBackground = true)
@Composable
fun AnalysisScreenPreview() {
    Digital_detox_appTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AnalysisScreen()
        }
    }
}
