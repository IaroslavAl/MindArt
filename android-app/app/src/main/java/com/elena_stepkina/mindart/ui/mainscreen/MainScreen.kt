package com.elena_stepkina.mindart.ui.mainscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.elena_stepkina.mindart.model.MainScreenState
import com.elena_stepkina.mindart.ui.mainscreen.components.BackgroundView
import com.elena_stepkina.mindart.ui.mainscreen.components.ProgressBar
import com.elena_stepkina.mindart.ui.mainscreen.screenStates.ColorSelectionScreen
import com.elena_stepkina.mindart.ui.mainscreen.screenStates.CreatedContentSelectionScreen
import com.elena_stepkina.mindart.ui.mainscreen.screenStates.DisclaimerScreen
import com.elena_stepkina.mindart.ui.mainscreen.screenStates.PictureStyleSelectionScreen
import com.elena_stepkina.mindart.ui.mainscreen.screenStates.ResultScreen

@Composable
fun MainScreen(viewModel: MainViewModel, modifier: Modifier) {
    val screenState by viewModel.screenState.collectAsState()

    BackgroundView(screenState = screenState, modifier = modifier) {
        Column {
            ProgressBar(
                currentStep = screenState.ordinal,
                totalSteps = MainScreenState.entries.size
            )
            when (screenState) {
                MainScreenState.SelectContent -> CreatedContentSelectionScreen(viewModel)
                MainScreenState.Disclaimer -> DisclaimerScreen(viewModel)
                MainScreenState.SelectFirstColor -> ColorSelectionScreen(viewModel)
                MainScreenState.SelectSecondColor -> ColorSelectionScreen(viewModel)
                MainScreenState.SelectThirdColor -> ColorSelectionScreen(viewModel)
                MainScreenState.SelectPictureStyle -> PictureStyleSelectionScreen(viewModel)
                MainScreenState.Result -> ResultScreen(viewModel)
            }
        }
    }
}