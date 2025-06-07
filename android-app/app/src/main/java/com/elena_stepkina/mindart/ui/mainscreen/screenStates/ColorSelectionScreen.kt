package com.elena_stepkina.mindart.ui.mainscreen.screenStates

import Elena.Stepkina.MindArt.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.elena_stepkina.mindart.ui.mainscreen.MainViewModel
import com.elena_stepkina.mindart.ui.mainscreen.components.CustomButton
import com.elena_stepkina.mindart.ui.mainscreen.components.TextView

@Composable
fun ColorSelectionScreen(viewModel: MainViewModel) {
    val screenState by viewModel.screenState.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            when (screenState) {
                MainViewModel.ScreenState.CreatedContentSelectionScreen -> TextView("")
                MainViewModel.ScreenState.DisclaimerScreen -> TextView("")
                MainViewModel.ScreenState.ColorSelectionScreenFirst -> TextView(stringResource(R.string.color_selection_goal_first_question))
                MainViewModel.ScreenState.ColorSelectionScreenSecond -> TextView(stringResource(R.string.color_selection_goal_second_question))
                MainViewModel.ScreenState.ColorSelectionScreenThird -> TextView(stringResource(R.string.color_selection_goal_third_question))
                MainViewModel.ScreenState.PictureStyleSelectionScreen -> TextView("")
                MainViewModel.ScreenState.ResultScreen -> TextView("")
            }
            CustomButton(
                text = stringResource(R.string.disclaimer_continue),
                onClick = { viewModel.goToNext() },
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}