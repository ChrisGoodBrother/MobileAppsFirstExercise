package com.example.firstexercise

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_create_account_1)

        val registerLink = findViewById<TextView>(R.id.bottomText)
        registerLink.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        val credentialsManager = CredentialsManager()

        val emailInputLayout = findViewById<TextInputLayout>(R.id.EmailAddress) //Email Layout
        val passwordInputLayout = findViewById<TextInputLayout>(R.id.password) //Password Layout

        val emailInputText = findViewById<TextInputEditText>(R.id.EmailAddress) //Email Text
        val passwordInputText = findViewById<TextInputEditText>(R.id.password) //Password Text

    }
}