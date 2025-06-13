package com.elena_stepkina.mindart.ui.mainscreen.screenStates

import androidx.compose.runtime.Composable
import com.elena_stepkina.mindart.ui.mainscreen.MainViewModel

@Composable
fun ResultScreen(viewModel: MainViewModel) {
    LoadingScreen(viewModel)
}