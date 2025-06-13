package com.elena_stepkina.mindart.ui.mainscreen.screenStates

import android.graphics.BitmapFactory
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.elena_stepkina.mindart.model.ResultScreenState
import com.elena_stepkina.mindart.ui.mainscreen.MainViewModel
import com.elena_stepkina.mindart.ui.mainscreen.components.ErrorView
import com.elena_stepkina.mindart.ui.mainscreen.components.LoadingView

@Composable
fun ResultScreen(viewModel: MainViewModel) {
    val resultState by viewModel.resultState.collectAsState()
    val imageData by viewModel.imageData.collectAsState()
    val context = LocalContext.current
    val randomImageName = remember {
        val fallbackNumber = (0..9).random()
        "image$fallbackNumber"
    }

    LaunchedEffect(Unit) {
        viewModel.loadImage(context)
    }

    AnimatedContent(
        targetState = resultState,
        transitionSpec = {
            fadeIn(animationSpec = tween(250)).togetherWith(fadeOut(animationSpec = tween(250)))
        },
        label = "Result Screen Animation"
    ) { state ->
        when (state) {
            ResultScreenState.Loading -> LoadingView()
            ResultScreenState.Loaded -> LoadedView(
                imageData = imageData,
                onDownload = { imageData?.let { viewModel.saveImageToStorage(context = context, imageData = it) } },
                onRepeat = { viewModel.goToNext() }
            )
            ResultScreenState.Error -> ErrorView(
                imageName = randomImageName,
                onDownload = { viewModel.saveAndSharePlaceholderImage(context, randomImageName) },
                onRepeat = { viewModel.goToNext() }
            )
        }
    }
}

@Composable
fun LoadedView(
    imageData: ByteArray?,
    onDownload: () -> Unit,
    onRepeat: () -> Unit
) {
    val bitmap = remember(imageData) {
        imageData?.let { BitmapFactory.decodeByteArray(it, 0, it.size) }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        bitmap?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "Generated Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = onDownload) {
                Text("Скачать")
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = onRepeat) {
                Text("Повторить")
            }
        }
    }
}
