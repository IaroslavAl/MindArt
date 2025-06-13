package com.elena_stepkina.mindart.ui.mainscreen

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.util.Log
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elena_stepkina.mindart.model.EventType
import com.elena_stepkina.mindart.model.MainScreenState
import com.elena_stepkina.mindart.model.ResultScreenState
import com.elena_stepkina.mindart.model.TaskType
import com.elena_stepkina.mindart.repository.ImageRepository
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.output.ByteArrayOutputStream
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MainViewModel: ViewModel() {
    private val repository = ImageRepository()

    private val _screenState = MutableStateFlow(MainScreenState.SelectContent)
    val screenState: StateFlow<MainScreenState> = _screenState.asStateFlow()

    private val _taskType = MutableStateFlow(TaskType.Goal)
    val taskType: StateFlow<TaskType> = _taskType.asStateFlow()

    private val _eventType = MutableStateFlow(EventType.Light)
    val eventType: StateFlow<EventType> = _eventType.asStateFlow()

    private val _color1 = MutableStateFlow("")
    val color1: StateFlow<String> = _color1.asStateFlow()

    private val _color2 = MutableStateFlow("")
    val color2: StateFlow<String> = _color2.asStateFlow()

    private val _color3 = MutableStateFlow("")
    val color3: StateFlow<String> = _color3.asStateFlow()

    private val _imageData = MutableStateFlow<ByteArray?>(null)
    val imageData: StateFlow<ByteArray?> = _imageData.asStateFlow()

    private val _resultState = MutableStateFlow(ResultScreenState.Loading)
    val resultState: StateFlow<ResultScreenState> = _resultState.asStateFlow()

    private val _savedFilePath = MutableStateFlow<String?>(null)
    val savedFilePath: StateFlow<String?> = _savedFilePath.asStateFlow()

    fun goToNext() {
        val currentOrdinal = screenState.value.ordinal
        val nextOrdinal = (currentOrdinal + 1) % MainScreenState.entries.size
        _screenState.value = MainScreenState.entries.toTypedArray()[nextOrdinal]
    }

    fun setTaskType(taskType: TaskType) {
        _taskType.value = taskType
    }

    fun setEventType(eventType: EventType) {
        _eventType.value = eventType
    }

    fun setColor(color: String) {
        when (_screenState.value) {
            MainScreenState.SelectFirstColor -> _color1.value = color
            MainScreenState.SelectSecondColor -> _color2.value = color
            MainScreenState.SelectThirdColor -> _color3.value = color
            else -> {}
        }
    }

    fun loadImage(context: Context) {
        Log.d("ImageRepository", "Запуск loadImage()")
        _resultState.value = ResultScreenState.Loading
        viewModelScope.launch {
            try {
                Log.d("ImageRepository", "Начинаем генерацию изображения с параметрами: " +
                        "color1=${_color1.value}, color2=${_color2.value}, color3=${_color3.value}, style=${_eventType.value.name.lowercase()}")

                val data = repository.generateImage(
                    color1 = _color1.value,
                    color2 = _color2.value,
                    color3 = _color3.value,
                    style = _eventType.value.name.lowercase()
                )

                Log.d("ImageRepository", "Изображение успешно сгенерировано. Размер данных: ${data.size} байт")

                _imageData.value = data

                Log.d("ImageRepository", "Сохраняем изображение во временное хранилище")
                saveImageToStorage(
                    context = context,
                    imageData = data
                )

                _resultState.value = ResultScreenState.Loaded
                Log.d("ImageRepository", "Состояние установлено: Loaded")
            } catch (e: Exception) {
                _resultState.value = ResultScreenState.Error
                Log.e("ImageRepository", "Ошибка при генерации изображения: ${e.localizedMessage}", e)
            }
        }
    }


    fun saveImageToStorage(context: Context, imageData: ByteArray) {
        val fileName = "wallpaper.png"
        val file = File(context.cacheDir, fileName)

        try {
            FileOutputStream(file).use { fos ->
                fos.write(imageData)
                fos.flush()
            }
            _savedFilePath.value = file.absolutePath
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun savePlaceholderImage(context: Context, name: String) {
        Log.d("ImageRepository", "Начинаем сохранение изображения: $name")

        val resId = context.resources.getIdentifier(name, "drawable", context.packageName)
        if (resId == 0) {
            Log.e("ImageRepository", "Ресурс с именем '$name' не найден!")
            return
        }
        Log.d("ImageRepository", "Найден ресурс с id: $resId")

        val bitmap = BitmapFactory.decodeResource(context.resources, resId)
        if (bitmap == null) {
            Log.e("ImageRepository", "Не удалось декодировать ресурс в Bitmap")
            return
        }
        Log.d("ImageRepository", "Bitmap успешно создан")

        val stream = ByteArrayOutputStream()
        val compressed = bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        if (!compressed) {
            Log.e("ImageRepository", "Ошибка при сжатии Bitmap в PNG")
            return
        }
        Log.d("ImageRepository", "Bitmap сжат в PNG, размер: ${stream.size()} байт")

        val byteArray = stream.toByteArray()
        saveImageToStorage(context, byteArray)

        Log.d("ImageRepository", "Сохранение изображения завершено")
    }

    fun saveImageAndShare(context: Context, imageData: ByteArray, fileName: String = "wallpaper.png") {
        val picturesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (picturesDir == null) {
            Log.e("ImageRepository", "Не удалось получить папку Pictures")
            return
        }

        val file = File(picturesDir, fileName)
        try {
            FileOutputStream(file).use { it.write(imageData) }
            Log.d("ImageRepository", "Файл сохранён: ${file.absolutePath}")

            // Для доступа из других приложений нужно FileProvider
            val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)

            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "image/png"
                putExtra(Intent.EXTRA_STREAM, uri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }

            val chooser = Intent.createChooser(shareIntent, "Поделиться изображением")
            context.startActivity(chooser)

        } catch (e: IOException) {
            Log.e("ImageRepository", "Ошибка при сохранении изображения: ${e.localizedMessage}")
        }
    }

    fun saveAndSharePlaceholderImage(context: Context, name: String) {
        Log.d("ImageRepository", "Начинаем сохранение изображения: $name")

        val resId = context.resources.getIdentifier(name, "drawable", context.packageName)
        if (resId == 0) {
            Log.e("ImageRepository", "Ресурс с именем '$name' не найден!")
            return
        }
        Log.d("ImageRepository", "Найден ресурс с id: $resId")

        val bitmap = BitmapFactory.decodeResource(context.resources, resId)
        if (bitmap == null) {
            Log.e("ImageRepository", "Не удалось декодировать ресурс в Bitmap")
            return
        }
        Log.d("ImageRepository", "Bitmap успешно создан")

        try {
            // Путь для сохранения — папка Pictures внутри внешнего хранилища приложения
            val picturesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            if (picturesDir == null) {
                Log.e("ImageRepository", "Не удалось получить папку Pictures")
                return
            }

            val fileName = "$name.png"
            val file = File(picturesDir, fileName)

            FileOutputStream(file).use { fos ->
                val compressed = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
                if (!compressed) {
                    Log.e("ImageRepository", "Ошибка при сжатии Bitmap в PNG")
                    return
                }
                Log.d("ImageRepository", "Bitmap сжат и записан в файл: ${file.absolutePath}")
            }

            // Получаем URI для файла через FileProvider
            val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)

            // Создаём Intent для системного окна "Поделиться"
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "image/png"
                putExtra(Intent.EXTRA_STREAM, uri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }

            val chooser = Intent.createChooser(shareIntent, "Поделиться изображением")
            chooser.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(chooser)

            Log.d("ImageRepository", "Запущено окно 'Поделиться'")

        } catch (e: Exception) {
            Log.e("ImageRepository", "Ошибка при сохранении и показе окна: ${e.localizedMessage}")
        }
    }
}