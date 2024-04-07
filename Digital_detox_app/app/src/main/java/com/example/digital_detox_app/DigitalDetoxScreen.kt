package com.example.digital_detox_app

import MainMenuScreen
import StartDetoxScreen
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

enum class DigitalDetoxScreen(@StringRes val title: Int) {
    MainMenu(title = R.string.app_name),
    RunningSession(title = R.string.start_detox_session),
    LeaderBoard(title = R.string.view_leaderboard),
    Analysis(title = R.string.view_analysis),
    SetGoal(title = R.string.set_goal)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DigitalDetoxBar (
    currentScreen: DigitalDetoxScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun DigitalDetoxApp(
//    viewModel: ViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = DigitalDetoxScreen.valueOf(
        backStackEntry?.destination?.route ?: DigitalDetoxScreen.MainMenu.name
    )
    Scaffold (
        topBar = {
            DigitalDetoxBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry !=null,
                navigateUp = { navController.navigateUp() })
        }
    ) { innerPadding ->
//        val uiState by viewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = DigitalDetoxScreen.MainMenu.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable(route = DigitalDetoxScreen.MainMenu.name) {
                MainMenuScreen(
                    navController = navController,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            }
            composable(route = DigitalDetoxScreen.RunningSession.name) {
                // TODO: create Screen for RunningSession
                StartDetoxScreen(
                    navController = navController,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            }
            composable(route = DigitalDetoxScreen.LeaderBoard.name) {
                // TODO: create Screen for Leaderboard
            }
            composable(route = DigitalDetoxScreen.Analysis.name) {
                // TODO: create Screen for Analysis
            }
            composable(route = DigitalDetoxScreen.SetGoal.name) {
                // TODO: create Screen for Set Goals
            }
        }
    }
}


