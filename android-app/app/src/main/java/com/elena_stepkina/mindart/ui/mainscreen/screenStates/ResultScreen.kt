package com.elena_stepkina.mindart.ui.mainscreen.screenStates

import Elena.Stepkina.MindArt.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.elena_stepkina.mindart.ui.mainscreen.MainViewModel
import com.elena_stepkina.mindart.ui.mainscreen.components.CustomButton
import com.elena_stepkina.mindart.ui.mainscreen.components.TextView

@Composable
fun ResultScreen(viewModel: MainViewModel) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TextView(stringResource(R.string.result_image_creating))
            CustomButton(
                text = stringResource(R.string.result_repeat),
                onClick = { viewModel.goToNext() },
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}