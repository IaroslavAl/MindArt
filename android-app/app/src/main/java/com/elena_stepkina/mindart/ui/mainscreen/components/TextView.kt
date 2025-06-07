package com.elena_stepkina.mindart.ui.mainscreen.components

import Elena.Stepkina.MindArt.R
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TextView(text: String) {
    Text(
        text = text,
        color = colorResource(R.color.accent),
        style = MaterialTheme.typography.headlineLarge,
        textAlign = TextAlign.Center
    )
}