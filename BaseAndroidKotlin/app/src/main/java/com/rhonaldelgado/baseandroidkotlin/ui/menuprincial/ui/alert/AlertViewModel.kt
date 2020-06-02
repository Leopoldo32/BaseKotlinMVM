package com.rhonaldelgado.baseandroidkotlin.ui.menuprincial.ui.alert

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AlertViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Mensaje enviado desde el modelo Slides"
    }
    val text: LiveData<String> = _text
}