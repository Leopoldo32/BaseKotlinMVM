package com.rhonaldelgado.baseandroidkotlin.ui.login

/**
 * Los detalles del usuario publican la autenticación que está expuesta a la IU
 */
data class LoggedInUserView(
        val displayName: String,
        val email: String
        //... otros campos de datos que pueden ser accesibles para la interfaz de usuario
)
