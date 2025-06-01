package com.elena_stepkina.mindart.ui.mainscreen

import androidx.lifecycle.ViewModel
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

    fun goToNext() {
        val currentOrdinal = screenState.value.ordinal
        val nextOrdinal = (currentOrdinal + 1) % ScreenState.entries.size
        _screenState.value = ScreenState.entries.toTypedArray()[nextOrdinal]
    }
}