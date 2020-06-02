package com.rhonaldelgado.baseandroidkotlin.ui.login

/**
 * Estado de validación de datos del formulario de inicio de sesión.
 */
data class LoginFormState(val usernameError: Int? = null,
                          val passwordError: Int? = null,
                          val isDataValid: Boolean = false)
