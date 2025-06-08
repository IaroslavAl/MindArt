package com.elena_stepkina.mindart.ui.mainscreen.screenStates

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.elena_stepkina.mindart.R
import com.elena_stepkina.mindart.model.CustomColor
import com.elena_stepkina.mindart.model.TaskType
import com.elena_stepkina.mindart.ui.mainscreen.MainViewModel
import com.elena_stepkina.mindart.ui.mainscreen.components.TextView

@Composable
fun ColorSelectionScreen(viewModel: MainViewModel) {
    val screenState by viewModel.screenState.collectAsState()
    val taskType by viewModel.taskType.collectAsState()
    val stringResource = when (taskType) {
        TaskType.Goal -> when (screenState) {
            MainViewModel.ScreenState.ColorSelectionScreenFirst -> R.string.color_selection_goal_first_question
            MainViewModel.ScreenState.ColorSelectionScreenSecond -> R.string.color_selection_goal_second_question
            MainViewModel.ScreenState.ColorSelectionScreenThird -> R.string.color_selection_goal_third_question
            else -> null
        }
        TaskType.Memories -> when (screenState) {
            MainViewModel.ScreenState.ColorSelectionScreenFirst -> R.string.color_selection_memories_first_question
            MainViewModel.ScreenState.ColorSelectionScreenSecond -> R.string.color_selection_memories_second_question
            MainViewModel.ScreenState.ColorSelectionScreenThird -> R.string.color_selection_memories_third_question
            else -> null
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier)

        stringResource?.let { stringResource(it) }?.let {
            TextView(text = it, dynamicText = true)
        }

        ColorsView { customColor ->
            viewModel.setColor(customColor.name)
            viewModel.goToNext()
        }
    }
}

@Composable
fun ColorsView(onColorSelected: (CustomColor) -> Unit) {
    val columns = 5
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val spacing = 16.dp
    val cellSize = remember(screenWidth) {
        val totalSpacing = spacing * (columns - 1) + 32.dp
        (screenWidth - totalSpacing) / columns
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalArrangement = Arrangement.spacedBy(spacing),
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
        items(CustomColor.entries.toTypedArray()) { customColor ->
            ColorButton(
                customColor = customColor,
                cellSize = cellSize,
                onClick = { onColorSelected(customColor) }
            )
        }
    }
}

@Composable
fun ColorButton(
    customColor: CustomColor,
    cellSize: Dp,
    onClick: () -> Unit
) {
    val isGradient = customColor == CustomColor.Silver
            || customColor == CustomColor.Gold
            || customColor == CustomColor.Bronze
    val backgroundBrush = if (isGradient) {
        Brush.linearGradient(
            colors = customColor.colors,
            start = Offset(0f, 0f),
            end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
        )
    } else {
        null
    }

    Box(
        modifier = Modifier
            .size(cellSize)
            .background(
                brush = backgroundBrush ?: SolidColor(customColor.color),
                shape = RoundedCornerShape(3.dp)
            )
            .border(0.5.dp, colorResource(R.color.accent), RoundedCornerShape(3.dp))
            .clickable(onClick = onClick)
    )
}