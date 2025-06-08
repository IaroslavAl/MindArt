package com.elena_stepkina.mindart.ui.mainscreen.screenStates

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.elena_stepkina.mindart.R
import com.elena_stepkina.mindart.model.EventType
import com.elena_stepkina.mindart.ui.mainscreen.MainViewModel
import com.elena_stepkina.mindart.ui.mainscreen.components.CustomButton
import com.elena_stepkina.mindart.ui.mainscreen.components.TextView

@Composable
fun PictureStyleSelectionScreen(viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier)
        TextView(stringResource(R.string.picture_style_question))
        Buttons(
            onEventSelected = { eventType ->
                viewModel.setEventType(eventType)
                viewModel.goToNext()
            }
        )
        Spacer(modifier = Modifier)
    }
}

@Composable
fun Buttons(onEventSelected: (EventType) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        EventType.entries.forEach { eventType ->
            val text = when (eventType) {
                EventType.Light -> stringResource(R.string.picture_style_light)
                EventType.Significant -> stringResource(R.string.picture_style_significant)
                EventType.Dynamic -> stringResource(R.string.picture_style_dynamic)
                EventType.Tender -> stringResource(R.string.picture_style_tender)
            }
            CustomButton(
                text = text,
                onClick = { onEventSelected(eventType) }
            )
        }
    }
}