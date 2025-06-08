package com.elena_stepkina.mindart.ui.mainscreen.components

import Elena.Stepkina.MindArt.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elena_stepkina.mindart.ui.mainscreen.extensions.swiftShadow

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clickable(onClick = onClick)
            .swiftShadow(
                color = Color.Black,
                alpha = 0.15f,
                offsetX = 5.dp,
                offsetY = 5.dp,
                blurRadius = 8.dp,
                cornerRadius = 9.dp
            )
            .swiftShadow(
                color = Color.Black,
                alpha = 0.1f,
                offsetX = 10.dp,
                offsetY = 10.dp,
                blurRadius = 15.dp,
                cornerRadius = 9.dp
            )
            .swiftShadow(
                color = Color.White,
                alpha = 0.7f,
                offsetX = (-5).dp,
                offsetY = (-5).dp,
                blurRadius = 10.dp,
                cornerRadius = 9.dp
            )
            .background(Color.White, RoundedCornerShape(9.dp))
            .padding(20.dp)
    ) {
        Text(
            text = text.lowercase(),
            style = typography.headlineLarge,
            letterSpacing = 3.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            color = colorResource(R.color.accent)
        )
    }
}