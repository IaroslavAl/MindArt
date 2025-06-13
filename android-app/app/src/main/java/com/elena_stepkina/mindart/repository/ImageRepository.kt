package com.elena_stepkina.mindart.repository

import android.util.Log
import com.elena_stepkina.mindart.network.NetworkService
import kotlinx.coroutines.delay
import java.io.IOException

class ImageRepository {
    companion object {
        private const val TAG = "ImageRepository"
    }

    suspend fun generateImage(
        color1: String,
        color2: String,
        color3: String,
        style: String
    ): ByteArray {
        val maxAttempts = 2

        repeat(maxAttempts + 1) { attempt ->
            try {
                Log.d(TAG, "Попытка #$attempt: Запрос на генерацию изображения с параметрами: " +
                        "color1=$color1, color2=$color2, color3=$color3, style=$style")

                val response = NetworkService.api.generateImage(color1, color2, color3, style)
                if (response.isSuccessful && response.body() != null) {
                    return response.body()!!.bytes()  // Получаем PNG байты
                } else {
                    throw IOException("HTTP error: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Ошибка при загрузке изображения на попытке #$attempt: ${e.localizedMessage}")
                if (attempt == maxAttempts) {
                    Log.e(TAG, "Превышено число попыток ($maxAttempts), бросаем исключение")
                    throw IOException("Failed to load image after $maxAttempts attempts", e)
                }
                delay(1000)
            }
        }

        Log.e(TAG, "Невозможно достичь этого кода")
        throw IOException("Unreachable code")
    }
}