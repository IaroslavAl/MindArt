package com.elena_stepkina.mindart.ui.mainscreen

import androidx.lifecycle.ViewModel
import com.elena_stepkina.mindart.model.EventType
import com.elena_stepkina.mindart.model.TaskType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel: ViewModel() {
    enum class ScreenState {
        CreatedContentSelectionScreen,
        DisclaimerScreen,
        ColorSelectionScreenFirst,
        ColorSelectionScreenSecond,
        ColorSelectionScreenThird,
        PictureStyleSelectionScreen,
        ResultScreen
    }

    private val _screenState = MutableStateFlow(ScreenState.CreatedContentSelectionScreen)
    val screenState: StateFlow<ScreenState> = _screenState.asStateFlow()

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

    fun goToNext() {
        val currentOrdinal = screenState.value.ordinal
        val nextOrdinal = (currentOrdinal + 1) % ScreenState.entries.size
        _screenState.value = ScreenState.entries.toTypedArray()[nextOrdinal]
    }

    fun setTaskType(taskType: TaskType) {
        _taskType.value = taskType
    }

    fun setEventType(eventType: EventType) {
        _eventType.value = eventType
    }

    fun setColor(color: String) {
        when (_screenState.value) {
            ScreenState.ColorSelectionScreenFirst -> _color1.value = color
            ScreenState.ColorSelectionScreenSecond -> _color2.value = color
            ScreenState.ColorSelectionScreenThird -> _color3.value = color
            else -> {}
        }
    }
}