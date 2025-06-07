package com.elena_stepkina.mindart.ui.mainscreen.screenStates

import Elena.Stepkina.MindArt.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
        TextView(stringResource(R.string.picture_style_question))
        Buttons(
            onEventSelected = { _ ->
                // заменить
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
            text = stringResource(R.string.picture_style_light),
            onClick = { onEventSelected(EventType.Light) }
        )
        ButtonLabel(
            text = stringResource(R.string.picture_style_significant),
            onClick = { onEventSelected(EventType.Significant) }
        )
        ButtonLabel(
            text = stringResource(R.string.picture_style_dynamic),
            onClick = { onEventSelected(EventType.Dynamic) }
        )
        ButtonLabel(
            text = stringResource(R.string.picture_style_tender),
            onClick = { onEventSelected(EventType.Tender) }
        )
    }
}