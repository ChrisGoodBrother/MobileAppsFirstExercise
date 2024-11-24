package com.example.firstexercise

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : ComponentActivity() {

    private val emailInputLayout: TextInputLayout
        get() = findViewById(R.id.EmailAddress) //Email Layout
    private val passwordInputLayout: TextInputLayout
        get() = findViewById(R.id.password) //Password Layout
    private val emailInputText: String
        get() = findViewById<TextInputEditText>(R.id.emailInputText).text.toString() //Email Text
    private val passwordInputText: String
        get() = findViewById<TextInputEditText>(R.id.passwordInputText).text.toString() //Password Text


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login_activity)

        val registerLink = findViewById<TextView>(R.id.bottomText)
        registerLink.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        val credentialsManager = CredentialsManager()

        findViewById<Button>(R.id.nextButton).setOnClickListener {

            val emailInputText = emailInputText
            val passwordInputText = passwordInputText

            if(credentialsManager.credentialsAreCorrect(emailInputText, passwordInputText)) {
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }

            if(!credentialsManager.isEmailValid(emailInputText)) {
                emailInputLayout.error = "Wrong email"
                emailInputLayout.isErrorEnabled = true
            }
            else {
                emailInputLayout.isErrorEnabled = false
            }

            if(!credentialsManager.passwordIsNotEmpty(passwordInputText)) {
                passwordInputLayout.error = "Password is empty"
                passwordInputLayout.isErrorEnabled = true
            }
            else {
                passwordInputLayout.isErrorEnabled = false
            }
        }
    }
}