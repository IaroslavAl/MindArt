package com.elena_stepkina.mindart.model

import androidx.compose.ui.graphics.Color

enum class CustomColor {
    Red,
    Coral,
    Pink,
    LightPink,
    Burgundy,
    Plum,
    Purple,
    Lilac,
    Indigo,
    Blue,
    DarkBlue,
    LightBlue,
    Turquoise,
    Mint,
    Green,
    Lime,
    LimeGreen,
    DeepGreen,
    Olive,
    Yellow,
    Caramel,
    Gold,
    Silver,
    Bronze,
    Brown,
    Beige,
    Terracotta,
    Gray,
    White,
    Black;

    val color: Color
        get() = when (this) {
            Red -> Color(0xFFFF0000)
            Coral -> Color(0xFFFF7F50)
            Pink -> Color(0xFFFFC0CB)
            LightPink -> Color(0xFFFF7E93)
            Burgundy -> Color(0xFF800020)
            Plum -> Color(0xFF8E4585)
            Purple -> Color(0xFF800080)
            Lilac -> Color(0xFFC8A2C8)
            Indigo -> Color(0xFF4B0082)
            Blue -> Color(0xFF0000FF)
            DarkBlue -> Color(0xFF00A58B)
            LightBlue -> Color(0xFFADD8E6)
            Turquoise -> Color(0xFF40E0D0)
            Mint -> Color(0xFFBDFCC9)
            Green -> Color(0xFF008000)
            Lime -> Color(0xFF00FF00)
            LimeGreen -> Color(0xFFD1E231)
            DeepGreen -> Color(0xFF00382B)
            Olive -> Color(0xFF808000)
            Yellow -> Color(0xFFFFFF00)
            Caramel -> Color(0xFFFFD59A)
            Gold -> Color(0xFFFFD700)
            Silver -> Color(0xFFC0C0C0)
            Bronze -> Color(0xFFCD7F32)
            Brown -> Color(0xFFA52A2A)
            Beige -> Color(0xFFF5F5DC)
            Terracotta -> Color(0xFFCC4E5C)
            Gray -> Color(0xFF808080)
            White -> Color(0xFFFFFFFF)
            Black -> Color(0xFF000000)
        }

    val colors: List<Color>
        get() = when (this) {
            Silver -> listOf(
                Color(0xFFC0C0C0),
                Color(0xFFE0E0E0),
                Color(0xFFF5F5F5),
                Color(0xFFC8C8C8),
                Color(0xFFA9A9A9),
                Color(0xFFE0E0E0)
            )

            Gold -> listOf(
                Color(0xFFD4AF37),
                Color(0xFFF7D75B),
                Color(0xFFFFEF82),
                Color(0xFFD3AF37),
                Color(0xFFB8860B),
                Color(0xFFF7D75B)
            )

            Bronze -> listOf(
                Color(0xFFCD7F32),
                Color(0xFFD88B4B),
                Color(0xFFC46210),
                Color(0xFF8B4513),
                Color(0xFFCD7F32),
                Color(0xFFC46210)
            )

            else -> listOf(Color.Transparent)
        }
}