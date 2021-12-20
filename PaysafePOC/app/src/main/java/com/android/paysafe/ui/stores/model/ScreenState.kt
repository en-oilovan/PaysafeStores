package com.android.paysafe.ui.stores.model

sealed class ScreenState {
    object Normal : ScreenState()
    object Loading : ScreenState()
    class Error(val msg: String) : ScreenState()
}