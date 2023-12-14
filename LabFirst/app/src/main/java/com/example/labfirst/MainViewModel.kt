package com.example.labfirst

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var text by mutableStateOf("")

    fun onTextChanged(newText: String) {
        text = newText
    }
}