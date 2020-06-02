package com.rhonaldelgado.baseandroidkotlin.ui.menuprincial.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Mensaje enviado desde el modelo Home"
    }
    val text: LiveData<String> = _text
}