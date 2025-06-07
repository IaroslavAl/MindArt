package com.elena_stepkina.mindart.ui.mainscreen.screenStates

import Elena.Stepkina.MindArt.R
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
import com.elena_stepkina.mindart.model.TaskType
import com.elena_stepkina.mindart.ui.mainscreen.MainViewModel
import com.elena_stepkina.mindart.ui.mainscreen.components.BackgroundView
import com.elena_stepkina.mindart.ui.mainscreen.components.ButtonLabel
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
            onTaskSelected = { _ ->
                // заменить
                viewModel.goToNext()
            }
        )
        Spacer(modifier = Modifier)
    }
}

@Composable
fun ButtonsSection(
    onTaskSelected: (TaskType) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
//        TaskType.entries.forEach { taskType ->
//            ButtonLabel(
//                text = "taskType",
//                onClick = { onTaskSelected(taskType) }
//            )
//        }
        ButtonLabel(
            text = stringResource(R.string.created_content_goal),
            onClick = { onTaskSelected(TaskType.Goal) }
        )
        ButtonLabel(
            text = stringResource(R.string.created_content_memories),
            onClick = { onTaskSelected(TaskType.Memories) }
        )
    }
}