package com.elena_stepkina.mindart.ui.mainscreen.screenStates

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.elena_stepkina.mindart.model.TaskType
import com.elena_stepkina.mindart.ui.mainscreen.MainViewModel
import com.elena_stepkina.mindart.ui.mainscreen.components.ButtonLabel
import com.elena_stepkina.mindart.ui.mainscreen.components.TextView

@Composable
fun CreatedContentSelectionScreen(viewModel: MainViewModel) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = 32.dp),
//            .background(color = AppTheme.colors.bg1),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier)
        TextView(text = "What do you want to create?")
        ButtonsSection(
            onTaskSelected = { _ ->
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
            text = "Goal",
            onClick = { onTaskSelected(TaskType.Goal) }
        )
        ButtonLabel(
            text = "Memories",
            onClick = { onTaskSelected(TaskType.Memories) }
        )
    }
}