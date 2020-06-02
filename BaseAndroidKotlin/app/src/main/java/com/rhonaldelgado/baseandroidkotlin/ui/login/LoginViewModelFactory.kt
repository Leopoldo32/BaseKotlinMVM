package com.rhonaldelgado.baseandroidkotlin.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rhonaldelgado.baseandroidkotlin.data.LoginDataSource
import com.rhonaldelgado.baseandroidkotlin.data.LoginRepository

/**
 * Fábrica de proveedores de ViewModel para instanciar LoginViewModel.
 * Obligatorio dado que LoginViewModel tiene un constructor no vacío
 */
class LoginViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                loginRepository = LoginRepository(
                    dataSource = LoginDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Desconocida Clase de ViewModel")
    }
}
