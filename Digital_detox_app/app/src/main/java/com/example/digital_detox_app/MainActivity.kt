package com.example.digital_detox_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.digital_detox_app.ui.theme.Digital_detox_appTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Digital_detox_appTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DigitalDetoxApp()
                }
            }
        }
    }
}
@Composable
fun DigitalDetoxApp() {
    AppMainMenu(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun AppMainMenu(modifier: Modifier = Modifier) {
    Column (
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { /*TODO*/ }) {
            Text(stringResource(R.string.start_detox_session))
        }
        Button(onClick = { /*TODO*/ }) {
            Text(stringResource(R.string.view_leaderboard))
        }
        Button(onClick = { /*TODO*/ }) {
            Text(stringResource(R.string.view_analysis))
        }
        Button(onClick = { /*TODO*/ }) {
            Text(stringResource(R.string.set_goal))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Digital_detox_appTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            DigitalDetoxApp()
        }
    }
}