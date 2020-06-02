package com.rhonaldelgado.baseandroidkotlin.data

import com.rhonaldelgado.baseandroidkotlin.data.model.LoggedInUser

/**
 * Clase que solicita autenticación e información del usuario de la fuente de datos remota y
 * mantiene una memoria caché en memoria del estado de inicio de sesión y la información de las credenciales del usuario.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    // caché en memoria del objeto loginInUser
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // Si las credenciales del usuario se almacenarán en caché en el almacenamiento local, se recomienda que se cifre
        // @ver https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    fun login(username: String, password: String): Result<LoggedInUser> {
        // manejo inicio de sesión
        val result = dataSource.login(username, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser


        // Si las credenciales del usuario se almacenarán en caché en el almacenamiento local, se recomienda que se cifre
        // @see https://developer.android.com/training/articles/keystore
    }
}
