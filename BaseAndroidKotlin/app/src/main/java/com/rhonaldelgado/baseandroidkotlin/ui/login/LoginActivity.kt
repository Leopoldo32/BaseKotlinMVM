package com.rhonaldelgado.baseandroidkotlin.ui.login

import AlertDialogUtil
import OnDialogOneButtonClickListener
import OnDialogTwoButtonClickListener
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.textfield.TextInputLayout
import com.rhonaldelgado.baseandroidkotlin.R
import com.rhonaldelgado.baseandroidkotlin.ui.menuprincial.menuprincipal

class LoginActivity : AppCompatActivity(), OnDialogTwoButtonClickListener,
    OnDialogOneButtonClickListener {

    private lateinit var loginViewModel: LoginViewModel
    lateinit var alertDialogUtil: AlertDialogUtil


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        //ocultar actionbar
        supportActionBar?.hide()

        //LoginViewModel.hideKeyboard()

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val btnlogin = findViewById<Button>(R.id.btnlogin)
        val layoutuser = findViewById<TextInputLayout>(R.id.textFielduser)
        val layoutpass = findViewById<TextInputLayout>(R.id.textFieldpas)
        val loading = findViewById<ProgressBar>(R.id.loading)


        // Get input text
        val inputTextuser = layoutuser.editText?.text.toString()
        val inputTextpass = layoutpass.editText?.text.toString()


        alertDialogUtil = AlertDialogUtil(this)

        loginViewModel = ViewModelProviders.of(this, LoginViewModelFactory()).get(LoginViewModel::class.java)


        if (username.length() > 0 && password.length() > 4) {
            btnlogin.isEnabled
        }

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // desactiva el botón de inicio de sesión a menos que ambos nombre de usuario / contraseña sean válidos


            btnlogin.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                layoutuser.error = getString(loginState.usernameError) //material design error
            } else {
                layoutuser.error = null
            }
            if (loginState.passwordError != null) {
                layoutpass.error = getString(loginState.passwordError) //material design error

            } else {
                layoutpass.error = null
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)

                val intent = Intent(this, menuprincipal::class.java).apply {
                    putExtra("NameUser", (loginResult.success.displayName))
                    putExtra("EmailUser", (loginResult.success.email))
                }
                startActivity(intent)
            }
            setResult(Activity.RESULT_OK)


        })

        layoutuser.editText?.doOnTextChanged { inputTextuser, _, _, _ ->
            // Respond to input text change
            username.afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()

                )
            }
        }

        layoutpass.editText?.doOnTextChanged { inputTextpass, _, _, _ ->
            // Respond to input text change
            password.apply {
                afterTextChanged {
                    loginViewModel.loginDataChanged(
                        username.text.toString(),
                        password.text.toString()
                    )
                }
            }
            btnlogin.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(username.text.toString(), password.text.toString())
            }
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        val email: String = model.email
        // TODO : initiate successful logged in experience


     /*   alertDialogUtil.openAlertDialogTwoButtons(
            this,
            null, //NO ES OBLIGATORIO
            "este es el mensaje", //NO ES OBLIGATORIO
            null, //NO ES OBLIGATORIO
            null, //NO ES OBLIGATORIO
            AppCompatResources.getDrawable(
                this,
                android.R.drawable.ic_dialog_alert
            ), // escoger el icono a mostrar //NO ES OBLIGATORIO
            AppCompatResources.getColorStateList(
                this,
                android.R.color.holo_orange_light
            ), // escoger el color del icono //NO ES OBLIGATORIO
            false, // ES OBLIGATORIO
            this
        ) // ES OBLIGATORIO*/

/*
        alertDialogUtil.openAlertDialogOneButton(this,
            "Error", //NO ES OBLIGATORIO
            "holasss", //NO ES OBLIGATORIO
            "Aceptar", //NO ES OBLIGATORIO
            AppCompatResources.getDrawable(this, android.R.drawable.ic_dialog_alert), //NO ES OBLIGATORIO
            AppCompatResources.getColorStateList(this, android.R.color.holo_orange_light), //NO ES OBLIGATORIO
            false, // ES OBLIGATORIO
            this) // ES OBLIGATORIO
*/


         Toast.makeText(
                 applicationContext,
                 "$welcome $displayName",
                 Toast.LENGTH_LONG
         ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

    override fun onPositiveButtonClicked() {
        Log.e("aa", "FirstButton")
    }

    override fun onNegativeButtonClicked() {
        Log.e("bb", "SecondButton")
    }

    override fun onOneButtonClicked() {
        Log.e("aa", "ONEButton")
    }

}


/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}
