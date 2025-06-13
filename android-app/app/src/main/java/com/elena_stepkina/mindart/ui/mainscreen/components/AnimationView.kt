package com.elena_stepkina.mindart.ui.mainscreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.elena_stepkina.mindart.R

@Composable
fun AnimationView(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val rawUri = "android.resource://${context.packageName}/${R.raw.loading}".toUri()

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(rawUri)
                .decoderFactory(GifDecoder.Factory())
                .build(),
            contentDescription = "GIF from raw"
        )
    }
}