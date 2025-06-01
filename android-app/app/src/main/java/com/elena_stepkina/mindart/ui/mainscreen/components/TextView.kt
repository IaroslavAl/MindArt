package com.elena_stepkina.mindart.ui.mainscreen.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit

@Composable
fun TextView(
    text: String,
    fontSize: TextUnit = TextUnit.Unspecified,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    color: Color = LocalContentColor.current
) {
    Text(
        text = text,
        modifier = modifier.fillMaxWidth(),
        color = color,
        fontSize = fontSize,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun TextViewPreview() {
    TextView("PreviewText")
}