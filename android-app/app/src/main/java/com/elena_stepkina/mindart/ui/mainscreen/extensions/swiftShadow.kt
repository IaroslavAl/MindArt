package com.elena_stepkina.mindart.ui.mainscreen.extensions

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.asComposePaint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp

fun Modifier.swiftShadow(
    color: Color,
    alpha: Float,
    offsetX: Dp,
    offsetY: Dp,
    blurRadius: Dp,
    cornerRadius: Dp
): Modifier = this.drawBehind {
    val paint = Paint().asFrameworkPaint().apply {
        this.color = color.copy(alpha = alpha).toArgb()
        this.setShadowLayer(
            blurRadius.toPx(),
            offsetX.toPx(),
            offsetY.toPx(),
            this.color
        )
    }

    drawIntoCanvas {
        it.drawRoundRect(
            0f,
            0f,
            size.width,
            size.height,
            cornerRadius.toPx(),
            cornerRadius.toPx(),
            paint.asComposePaint()
        )
    }
}