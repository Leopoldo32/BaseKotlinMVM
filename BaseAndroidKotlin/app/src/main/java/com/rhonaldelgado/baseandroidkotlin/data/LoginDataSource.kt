package com.rhonaldelgado.baseandroidkotlin.data

import com.rhonaldelgado.baseandroidkotlin.data.model.LoggedInUser
import java.io.IOException

/**
 * Clase que maneja la autenticación con credenciales / login y recupera información del usuario.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        return try {
            // TODO: handle loggedInUser authentication
            val fakeUser =
                LoggedInUser(java.util.UUID.randomUUID().toString(), "Rhonal Delgado", username)
            Result.Success(fakeUser)
        } catch (e: Throwable) {
            Result.Error(IOException("Error login en: ", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}

