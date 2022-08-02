package com.wtmcodex.samplerates.data.base

sealed class ScreenState {
    object Loading : ScreenState()
    object Success : ScreenState()
    object Error : ScreenState()
}
