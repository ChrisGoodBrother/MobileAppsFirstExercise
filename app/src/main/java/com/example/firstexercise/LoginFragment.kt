package com.example.firstexercise

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginFragment(private val credentialsManager: CredentialsManager) :
    Fragment(R.layout.login_fragment) {

    //LoginFragment Interface
    interface EventListener {
        fun goToRegister()
        fun goToApp()
    }

    private var listener: EventListener? = null

    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var emailInputText: String
    private lateinit var passwordInputText: String

    //Makes a requirement for the context to be of type EventListener
    override fun onAttach(context: Context) {
        super.onAttach(context)

        require(context is EventListener) {
            "Activity $context must implement fragment's EventListener"
        }

        listener = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emailInputLayout = view.findViewById(R.id.EmailAddress)
        passwordInputLayout = view.findViewById(R.id.password)

        //Call overriden function goToRegister
        val registerLink = view.findViewById<TextView>(R.id.bottomText)
        registerLink.setOnClickListener {
            listener?.goToRegister()
        }

        //Call overridden function goToApp if all requirements are met
        view.findViewById<Button>(R.id.nextButton).setOnClickListener {

            emailInputText = view.findViewById<TextInputEditText>(R.id.emailInputText).text.toString() //Email Text
            passwordInputText = view.findViewById<TextInputEditText>(R.id.passwordInputText).text.toString() //Password Text

            val emailInputText = emailInputText
            val passwordInputText = passwordInputText

            if(credentialsManager.login(emailInputText, passwordInputText)) {
                listener?.goToApp()
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