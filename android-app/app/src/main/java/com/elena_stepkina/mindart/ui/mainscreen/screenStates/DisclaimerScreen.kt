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
import com.elena_stepkina.mindart.ui.mainscreen.MainViewModel
import com.elena_stepkina.mindart.ui.mainscreen.components.CustomButton
import com.elena_stepkina.mindart.ui.mainscreen.components.TextView

@Composable
fun DisclaimerScreen(viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier)
        TextView(stringResource(R.string.disclaimer_info))
        CustomButton(
            text = stringResource(R.string.disclaimer_continue),
            onClick = {
                viewModel.goToNext()
            }
        )
        Spacer(modifier = Modifier)
    }
}