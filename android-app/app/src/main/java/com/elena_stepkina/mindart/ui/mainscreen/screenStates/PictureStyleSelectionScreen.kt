package com.elena_stepkina.mindart.ui.mainscreen.screenStates

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.elena_stepkina.mindart.model.EventType
import com.elena_stepkina.mindart.ui.mainscreen.MainViewModel
import com.elena_stepkina.mindart.ui.mainscreen.components.ButtonLabel
import com.elena_stepkina.mindart.ui.mainscreen.components.TextView

@Composable
fun PictureStyleSelectionScreen(viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
//            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier)

        TextView(text = "How would you characterize this event:")

        Buttons(
            onEventSelected = { _ ->
                viewModel.goToNext()
            }
        )

        Spacer(modifier = Modifier)
    }
}

@Composable
fun Buttons(onEventSelected: (EventType) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
//        EventType.values().forEach { eventType ->
//            Button(
//                onClick = { onEventSelected(eventType) },
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(text = stringResource(id = eventType.localizedResId))
//            }
//        }
        ButtonLabel(
            text = "Light",
            onClick = { onEventSelected(EventType.Light) }
        )
        ButtonLabel(
            text = "Significant",
            onClick = { onEventSelected(EventType.Significant) }
        )
        ButtonLabel(
            text = "Dynamic",
            onClick = { onEventSelected(EventType.Dynamic) }
        )
        ButtonLabel(
            text = "Tender",
            onClick = { onEventSelected(EventType.Tender) }
        )
    }
}