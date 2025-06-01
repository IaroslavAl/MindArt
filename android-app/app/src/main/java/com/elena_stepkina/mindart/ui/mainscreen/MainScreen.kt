package com.elena_stepkina.mindart.ui.mainscreen

import com.elena_stepkina.mindart.ui.mainscreen.components.ProgressBar
import com.elena_stepkina.mindart.ui.mainscreen.screenStates.ColorSelectionScreen
import com.elena_stepkina.mindart.ui.mainscreen.screenStates.CreatedContentSelectionScreen
import com.elena_stepkina.mindart.ui.mainscreen.screenStates.DisclaimerScreen
import com.elena_stepkina.mindart.ui.mainscreen.screenStates.PictureStyleSelectionScreen
import com.elena_stepkina.mindart.ui.mainscreen.screenStates.ResultScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val screenState by viewModel.screenState.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {
        when (screenState) {
            MainViewModel.ScreenState.CreatedContentSelectionScreen -> CreatedContentSelectionScreen(viewModel)
            MainViewModel.ScreenState.DisclaimerScreen -> DisclaimerScreen(viewModel)
            MainViewModel.ScreenState.ColorSelectionScreenFirst -> ColorSelectionScreen(viewModel)
            MainViewModel.ScreenState.ColorSelectionScreenSecond -> ColorSelectionScreen(viewModel)
            MainViewModel.ScreenState.ColorSelectionScreenThird -> ColorSelectionScreen(viewModel)
            MainViewModel.ScreenState.PictureStyleSelectionScreen -> PictureStyleSelectionScreen(viewModel)
            MainViewModel.ScreenState.ResultScreen -> ResultScreen(viewModel)
        }

        ProgressBar(
            currentStep = screenState.ordinal,
            totalSteps = MainViewModel.ScreenState.entries.size
        )
    }
}