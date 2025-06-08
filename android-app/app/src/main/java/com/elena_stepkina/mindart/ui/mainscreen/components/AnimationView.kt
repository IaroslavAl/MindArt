package com.elena_stepkina.mindart.ui.mainscreen.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.elena_stepkina.mindart.R

@Composable
fun AnimationView() {
    val context = LocalContext.current
    val rawUri = "android.resource://${context.packageName}/${R.raw.loading}".toUri()

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(rawUri)
            .decoderFactory(GifDecoder.Factory())
            .build(),
        contentDescription = "GIF from raw"
    )
}