package com.example.firstexercise

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : ComponentActivity() {

    private val fullNameInputLayout: TextInputLayout
        get() = findViewById(R.id.fullNameEdit) //Full Name Layout
    private val validEmailInputLayout: TextInputLayout
        get() = findViewById(R.id.emailEdit) //Valid Email Layout
    private val phoneNumberInputLayout: TextInputLayout
        get() = findViewById(R.id.phoneEdit) //Phone Number Layout
    private val strongPasswordInputLayout: TextInputLayout
        get() = findViewById(R.id.password) //Strong Password Layout

    private val fullNameInputText: String
        get() = findViewById<TextInputEditText>(R.id.fullNameInputText).text.toString() //Full Name Text
    private val validEmailInputText: String
        get() = findViewById<TextInputEditText>(R.id.validEmailInputText).text.toString() //Valid Email Text
    private val phoneNumberInputText: String
        get() = findViewById<TextInputEditText>(R.id.phoneNumberInputText).text.toString() //Phone Number Text
    private val strongPasswordInputText: String
        get() = findViewById<TextInputEditText>(R.id.strongPasswordInputText).text.toString() //Strong Password Text

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.register_activity)

        val loginLink = findViewById<TextView>(R.id.bottomText)
        loginLink.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        val credentialsManager = CredentialsManager()

        findViewById<Button>(R.id.nextButton).setOnClickListener {

            val fullNameInputText = fullNameInputText
            val validEmailInputText = validEmailInputText
            val phoneNumberInputText = phoneNumberInputText
            val strongPasswordInputText = strongPasswordInputText

            if(!credentialsManager.fullNameIsNotEmpty(fullNameInputText)) {
                fullNameInputLayout.error = "Fullname is empty"
                fullNameInputLayout.isErrorEnabled = true
            }
            else {
                fullNameInputLayout.isErrorEnabled = false
            }

            if(!credentialsManager.isEmailValid(validEmailInputText)) {
                validEmailInputLayout.error = "Wrong email"
                validEmailInputLayout.isErrorEnabled = true
            }
            else {
                validEmailInputLayout.isErrorEnabled = false
            }

            if(!credentialsManager.phoneNumberIsNotEmpty(phoneNumberInputText)) {
                phoneNumberInputLayout.error = "Phone number is empty"
                phoneNumberInputLayout.isErrorEnabled = true
            }
            else {
                phoneNumberInputLayout.isErrorEnabled = false
            }

            if(!credentialsManager.passwordIsNotEmpty(strongPasswordInputText)) {
                strongPasswordInputLayout.error = "Password is empty"
                strongPasswordInputLayout.isErrorEnabled = true
            }
            else {
                strongPasswordInputLayout.isErrorEnabled = false
            }
        }

    }
}