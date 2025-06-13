package com.elena_stepkina.mindart.ui.mainscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.elena_stepkina.mindart.R
import com.elena_stepkina.mindart.model.MainScreenState

@Composable
fun BackgroundView(
    screenState: MainScreenState,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val imageRes = when(screenState) {
        MainScreenState.SelectContent -> R.drawable.bg1
        MainScreenState.Disclaimer -> R.drawable.bg2
        MainScreenState.SelectFirstColor -> R.drawable.bg3
        MainScreenState.SelectSecondColor -> R.drawable.bg4
        MainScreenState.SelectThirdColor -> R.drawable.bg5
        MainScreenState.SelectPictureStyle -> R.drawable.bg6
        MainScreenState.Result -> null
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (imageRes != null) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            )
        }

        Box(modifier = modifier.fillMaxSize()) {
            content()
        }
    }
}
