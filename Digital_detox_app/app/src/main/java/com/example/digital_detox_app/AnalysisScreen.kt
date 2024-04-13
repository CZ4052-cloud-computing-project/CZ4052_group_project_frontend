import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.digital_detox_app.GoalTimeViewModel
import com.example.digital_detox_app.StatisticsUiState
import com.example.digital_detox_app.StatisticsViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun TotalDetoxTimeCard(totalDetoxTime: Long, goalTime: Long) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
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

            Text("Total Detox Time Today: ${totalDetoxTime.formatTime()}")

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Goal Detox Time Today",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            // Display goal time and current duration
            Text("Goal Time: ${goalTime.formatTime()}")

        }
    }
}

@Composable
fun BarGraph(dataPoints: List<Pair<String, Int>>) {
    val maxValue = dataPoints.maxOf { it.second }
    val maxHeight = 300.dp // Maximum height for the bar graph
    val barWidth = 40.dp
    val borderColor = MaterialTheme.colorScheme.primary
    val density = LocalDensity.current
    val strokeWidth = with(density) { 1.dp.toPx() }
//    VerticalAxis(maxValue = maxValue, maxHeight = maxHeight)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {

                drawLine(
                    color = borderColor,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = strokeWidth
                )
                // draw Y-Axis
                drawLine(
                    color = borderColor,
                    start = Offset(0f, 0f),
                    end = Offset(0f, size.height),
                    strokeWidth = strokeWidth
                )
            },
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
//
//        VerticalAxis(maxValue = maxValue, maxHeight = maxHeight)
//        Spacer(modifier = Modifier.width(30.dp))
        dataPoints.forEach { (detoxTime) ->

                Box(
                    modifier = Modifier
                        .size(barWidth, (detoxTime.toFloat() / maxValue.toFloat()) * maxHeight)
                        .background(Color.Blue)
                )
        }

    }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        dataPoints.forEach { (day) ->
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = day
                )
            }
        }
    }
}

//@Composable
//fun VerticalAxis(maxValue: Int, maxHeight: Dp) {
//    Column(
//
//        horizontalAlignment = Alignment.End
//    ) {
//        val step = maxValue / 5
//        for (i in 0..maxValue step step) {
//            val adjustedValue = maxValue - i // Adjust the value to start from the bottom
//            Text(text = "$adjustedValue")
//            Spacer(modifier = Modifier.height(maxHeight / 5))
//        }
//    }
//}

@Composable
fun AnalysisScreen(
    goalTimeViewModel: GoalTimeViewModel,
    statisticsViewModel: StatisticsViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ProgressBar(
            statisticsUiState = statisticsViewModel.statisticsUiState,
            goalTimeViewModel = goalTimeViewModel,
            modifier = modifier
        )
        Spacer(modifier = Modifier.height(20.dp))
        WeeklyProgress(
            statisticsUiState = statisticsViewModel.statisticsUiState
            , modifier = modifier)
    }
}

@Composable
fun ProgressBar(
    statisticsUiState: StatisticsUiState,
    goalTimeViewModel: GoalTimeViewModel,
    modifier: Modifier
) {
    when (statisticsUiState) {
        is StatisticsUiState.Loading -> Text("Loading...")
        is StatisticsUiState.Success -> {
            val goalTime by goalTimeViewModel.goalTime.collectAsState()
            val currDuration = statisticsUiState.statisticInfo.last().totalDurationOfSessions.toLong()

            val progress = if (goalTime > 0) {
                (currDuration.toFloat() / goalTime.toFloat()) * 100
            } else {
                0f
            }

            Column(modifier) {
                // Display TotalDetoxTimeCard
                TotalDetoxTimeCard(
                    totalDetoxTime = currDuration,
                    goalTime = goalTime
                )


                // Display progress bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp) // Adjust the height for a larger progress bar
                        .padding(vertical = 24.dp)
                        .background(color = Color.Black)
                ) {
                    LinearProgressIndicator(
                        progress = progress / 100, // progress should be between 0 and 1
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(1.dp) // Adjust the padding for the outline thickness
                    )
                }

                // Display percentage within the progress bar
                if (progress < 100) {
                    Text(
                        text = "${String.format("Current Progress towards Goal: %.2f", progress)}%",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                    )
                }
                else{
                    Text(
                        text = "Daily Goal Reached. Congratulations!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)

                    )
                }

            }

        }

        is StatisticsUiState.Error -> Text("Error")
    }
}

@Composable
fun WeeklyProgress(
    statisticsUiState: StatisticsUiState,
    modifier: Modifier
) {
    when (statisticsUiState) {
        is StatisticsUiState.Loading -> Text("Loading...")
        is StatisticsUiState.Success -> {

            Column {
                Text(
                    text = "Weekly Progress of Detox Duration",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                val datePattern = DateTimeFormatter.ISO_LOCAL_DATE
                val dateEntries = statisticsUiState.statisticInfo.map { entry ->
                    LocalDate.parse(entry.date, datePattern) to entry.totalDurationOfSessions
                }

                // Find the latest date
                val latestDate = dateEntries.maxOfOrNull { it.first } ?: return

                // Generate a sequence of dates going back 7 days from the latest date
                val allDates = generateSequence(latestDate.minusDays(6)) { it.plusDays(1) }
                    .takeWhile { it <= latestDate }

                // Create a map to quickly look up durations by date
                val dateDurationMap = dateEntries.toMap()

                // Fill in missing dates with zero duration
                val formatter = DateTimeFormatter.ofPattern("MM/dd")
                val paddedDateEntries = allDates.map { date ->
                    date.format(formatter) to (dateDurationMap[date] ?: 0)
                }
                // Optional to get numbered instead of dates x axis
//                val numberedEntries = paddedDateEntries.mapIndexed { index, (date, duration) ->
//                    (index + 1).toString() to duration
//                }

                BarGraph(dataPoints = paddedDateEntries.toList())
            }
        }
        is StatisticsUiState.Error -> Text("Error")
    }
}


