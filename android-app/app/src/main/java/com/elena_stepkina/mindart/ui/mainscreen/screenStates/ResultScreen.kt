package com.elena_stepkina.mindart.ui.mainscreen.screenStates

import Elena.Stepkina.MindArt.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
fun ResultScreen(viewModel: MainViewModel) {
    val taskType by viewModel.taskType.collectAsState()
    val eventType by viewModel.eventType.collectAsState()
    val color1 by viewModel.color1.collectAsState()
    val color2 by viewModel.color2.collectAsState()
    val color3 by viewModel.color3.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier)
        TextView(stringResource(R.string.result_image_creating))
        TextView("taskType: ${taskType.type}\n" +
                "eventType: ${eventType.type}\n" +
                "color1: ${color1}\n" +
                "color2: ${color2}\n" +
                "color3: $color3")
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