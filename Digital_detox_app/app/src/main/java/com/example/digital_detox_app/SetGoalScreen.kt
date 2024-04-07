package com.example.digital_detox_app

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.digital_detox_app.ui.theme.Digital_detox_appTheme

@Composable
fun SetGoalScreen(
    modifier: Modifier = Modifier,
    goalTimeViewModel: GoalTimeViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    var hours: Long by remember { mutableLongStateOf(0) }
    var minutes: Long by remember { mutableLongStateOf(0) }
    var seconds: Long by remember { mutableLongStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TimeInputField("Hours", hours) { hours = it }
            TimeInputField("Minutes", minutes) { minutes = it }
            TimeInputField("Seconds", seconds) { seconds = it }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val goalTimeInSeconds = hours * 3600 + minutes * 60 + seconds
                goalTimeViewModel.setGoalTime(goalTimeInSeconds)
            }
        ) {
            Text("Set Goal")
        }
        Spacer(modifier = Modifier.height(16.dp))

        val goalTime by goalTimeViewModel.goalTime.collectAsState()

        GoalCard(goalTime)
    }
}

@Composable
fun GoalCard(goalTime: Long) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Current Goal Time:", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = formatTime(goalTime),
                fontSize = 18.sp
            )
        }
    }
}

fun formatTime(timeInSeconds: Long): String {
    val hours = timeInSeconds / 3600
    val minutes = (timeInSeconds % 3600) / 60
    val seconds = timeInSeconds % 60
    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}

@Composable
fun TimeInputField(label: String, value: Long, onValueChange: (Long) -> Unit) {
    Column {
        Text(
            text = label,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = if (value == 0L) "" else value.toString(), // Clear field if value is 0
            onValueChange = { newValue ->
                onValueChange(newValue.toLongOrNull() ?: 0L)
            },
            singleLine = true,
            modifier = Modifier.width(100.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SetGoalPreview() {
    Digital_detox_appTheme {
        SetGoalScreen()
    }
}
