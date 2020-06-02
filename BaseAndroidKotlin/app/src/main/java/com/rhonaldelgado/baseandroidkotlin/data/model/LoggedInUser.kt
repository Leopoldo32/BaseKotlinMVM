package com.rhonaldelgado.baseandroidkotlin.data.model

/**
 * Clase que captura la información del usuario que se recuperaron de LoginRepository
 */
data class LoggedInUser(
        val userId: String,
        val displayName: String,
        val email: String
)

