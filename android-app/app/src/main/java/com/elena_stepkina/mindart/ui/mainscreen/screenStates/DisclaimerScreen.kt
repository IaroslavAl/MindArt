package com.elena_stepkina.mindart.ui.mainscreen.screenStates

import com.elena_stepkina.mindart.ui.mainscreen.MainViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.elena_stepkina.mindart.ui.mainscreen.components.ButtonLabel
import com.elena_stepkina.mindart.ui.mainscreen.components.TextView

@Composable
fun DisclaimerScreen(viewModel: MainViewModel) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TextView("DisclaimerScreen")
            ButtonLabel(
                text = "Go to next",
                onClick = { viewModel.goToNext() },
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}