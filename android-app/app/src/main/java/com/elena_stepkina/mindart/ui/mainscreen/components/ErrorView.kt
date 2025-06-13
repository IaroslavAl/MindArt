package com.elena_stepkina.mindart.ui.mainscreen.components

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ErrorView(
    imageName: String,
    onDownload: () -> Unit,
    onRepeat: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = getDrawableIdByName(imageName)),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

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

@Composable
fun getDrawableIdByName(name: String): Int {
    val context = LocalContext.current
    return remember(name) {
        context.resources.getIdentifier(name, "drawable", context.packageName)
    }
}
