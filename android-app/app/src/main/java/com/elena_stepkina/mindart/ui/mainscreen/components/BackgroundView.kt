package com.elena_stepkina.mindart.ui.mainscreen.components

import Elena.Stepkina.MindArt.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.elena_stepkina.mindart.ui.mainscreen.MainViewModel

@Composable
fun BackgroundView(
    screenState: MainViewModel.ScreenState,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val imageRes = when(screenState) {
        MainViewModel.ScreenState.CreatedContentSelectionScreen -> R.drawable.bg1
        MainViewModel.ScreenState.DisclaimerScreen -> R.drawable.bg2
        MainViewModel.ScreenState.ColorSelectionScreenFirst -> R.drawable.bg3
        MainViewModel.ScreenState.ColorSelectionScreenSecond -> R.drawable.bg4
        MainViewModel.ScreenState.ColorSelectionScreenThird -> R.drawable.bg5
        MainViewModel.ScreenState.PictureStyleSelectionScreen -> R.drawable.bg6
        MainViewModel.ScreenState.ResultScreen -> R.drawable.bg3
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Box(modifier = modifier.fillMaxSize()) {
            content()
        }
    }
}