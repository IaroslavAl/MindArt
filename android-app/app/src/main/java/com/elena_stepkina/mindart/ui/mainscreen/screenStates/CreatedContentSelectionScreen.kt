package com.elena_stepkina.mindart.ui.mainscreen.screenStates

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.elena_stepkina.mindart.R
import com.elena_stepkina.mindart.model.TaskType
import com.elena_stepkina.mindart.ui.mainscreen.MainViewModel
import com.elena_stepkina.mindart.ui.mainscreen.components.CustomButton
import com.elena_stepkina.mindart.ui.mainscreen.components.TextView

@Composable
fun CreatedContentSelectionScreen(viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier)
        TextView(stringResource(R.string.created_content_message))
        ButtonsSection(
            onTaskSelected = { taskType ->
                viewModel.setTaskType(taskType)
                viewModel.goToNext()
            }
        )
        Spacer(modifier = Modifier)
    }
}

@Composable
fun ButtonsSection(onTaskSelected: (TaskType) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        TaskType.entries.forEach { taskType ->
            val text = when (taskType) {
                TaskType.Goal -> stringResource(R.string.created_content_goal)
                TaskType.Memories -> stringResource(R.string.created_content_memories)
            }
            CustomButton(
                text = text,
                onClick = { onTaskSelected(taskType) }
            )
        }
    }
}