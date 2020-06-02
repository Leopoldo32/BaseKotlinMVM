package com.rhonaldelgado.baseandroidkotlin.ui.login

/**
 * Resultado de autenticación: éxito (detalles del usuario) o mensaje de error.
 */
data class LoginResult(
        val success: LoggedInUserView? = null,
        val error: Int? = null
)
