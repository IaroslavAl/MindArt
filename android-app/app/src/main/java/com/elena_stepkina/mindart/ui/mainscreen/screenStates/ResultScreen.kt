package com.elena_stepkina.mindart.ui.mainscreen.screenStates

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.elena_stepkina.mindart.R
import com.elena_stepkina.mindart.ui.mainscreen.MainViewModel
import com.elena_stepkina.mindart.ui.mainscreen.components.AnimationView
import com.elena_stepkina.mindart.ui.mainscreen.components.CustomButton

@Composable
fun ResultScreen(viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            AnimationView()
        }
        Column {
            CustomButton(
                text = stringResource(R.string.result_download),
                modifier = Modifier.padding(bottom = 16.dp),
                onClick = { viewModel.goToNext() }
            )
            CustomButton(
                text = stringResource(R.string.result_repeat),
                modifier = Modifier.padding(bottom = 32.dp),
                onClick = { viewModel.goToNext() }
            )
        }
    }
}