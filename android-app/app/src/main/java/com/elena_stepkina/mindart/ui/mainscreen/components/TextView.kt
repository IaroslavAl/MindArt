package com.elena_stepkina.mindart.ui.mainscreen.components

import android.annotation.SuppressLint
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.elena_stepkina.mindart.R

@Composable
fun TextView(
    text: String,
    dynamicText: Boolean = false,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val textStyle = if (dynamicText) {
        when {
            screenWidth <= 360.dp -> MaterialTheme.typography.titleLarge
            screenWidth <= 480.dp -> MaterialTheme.typography.headlineLarge
            else -> MaterialTheme.typography.displaySmall
        }
    } else {
        MaterialTheme.typography.headlineLarge
    }

    Text(
        text = text,
        modifier = modifier,
        color = colorResource(R.color.accent),
        style = textStyle,
        textAlign = TextAlign.Center
    )
}