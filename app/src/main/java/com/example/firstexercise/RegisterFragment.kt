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

class RegisterFragment(private val credentialsManager: CredentialsManager) :
    Fragment(R.layout.register_fragment) {

    //RegisterFragment Interface
    interface EventListener {
        fun goToLogin()
        fun goToApp()
    }

    private var listener: EventListener? = null

    private lateinit var fullNameInputLayout: TextInputLayout
    private lateinit var validEmailInputLayout: TextInputLayout
    private lateinit var phoneNumberInputLayout: TextInputLayout
    private lateinit var strongPasswordInputLayout: TextInputLayout

    private lateinit var fullNameInputText: String
    private lateinit var validEmailInputText: String
    private lateinit var phoneNumberInputText: String
    private lateinit var strongPasswordInputText: String

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
        return inflater.inflate(R.layout.register_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fullNameInputLayout = view.findViewById(R.id.fullNameEdit) //Full Name Layout
        validEmailInputLayout = view.findViewById(R.id.emailEdit) //Valid Email Layout
        phoneNumberInputLayout = view.findViewById(R.id.phoneEdit) //Phone Number Layout
        strongPasswordInputLayout = view.findViewById(R.id.password) //Strong Password Layout

        //Call overriden function goToLogin
        view.findViewById<TextView>(R.id.bottomText).setOnClickListener {
            listener?.goToLogin()
        }

        //Call overridden function goToApp if all requirements are met
        view.findViewById<Button>(R.id.nextButton).setOnClickListener {

            fullNameInputText = view.findViewById<TextInputEditText>(R.id.fullNameInputText).text.toString() //Full Name Text
            validEmailInputText = view.findViewById<TextInputEditText>(R.id.validEmailInputText).text.toString() //Valid Email Text
            phoneNumberInputText = view.findViewById<TextInputEditText>(R.id.phoneNumberInputText).text.toString() //Phone Number Text
            strongPasswordInputText = view.findViewById<TextInputEditText>(R.id.strongPasswordInputText).text.toString() //Strong Password Text

            val fullNameInputText = fullNameInputText
            val validEmailInputText = validEmailInputText
            val phoneNumberInputText = phoneNumberInputText
            val strongPasswordInputText = strongPasswordInputText

            if (!credentialsManager.fullNameIsNotEmpty(fullNameInputText)) {
                fullNameInputLayout.error = "Enter your full name"
                fullNameInputLayout.isErrorEnabled = true
            } else {
                fullNameInputLayout.isErrorEnabled = false
            }

            if (!credentialsManager.isEmailValid(validEmailInputText)) {
                validEmailInputLayout.error = "Enter a valid email"
                validEmailInputLayout.isErrorEnabled = true
            } else {
                validEmailInputLayout.isErrorEnabled = false
            }

            if (!credentialsManager.phoneNumberIsNotEmpty(phoneNumberInputText)) {
                phoneNumberInputLayout.error = "Enter your phone number"
                phoneNumberInputLayout.isErrorEnabled = true
            } else {
                phoneNumberInputLayout.isErrorEnabled = false
            }

            if (!credentialsManager.passwordIsNotEmpty(strongPasswordInputText)) {
                strongPasswordInputLayout.error = "Enter your password"
                strongPasswordInputLayout.isErrorEnabled = true
            } else {
                strongPasswordInputLayout.isErrorEnabled = false
            }

            if (!(fullNameInputLayout.isErrorEnabled || validEmailInputLayout.isErrorEnabled || phoneNumberInputLayout.isErrorEnabled || strongPasswordInputLayout.isErrorEnabled)) {
                if (credentialsManager.register(
                        fullNameInputText,
                        validEmailInputText,
                        phoneNumberInputText,
                        strongPasswordInputText
                    )
                ) {
                    listener?.goToApp()
                } else {
                    validEmailInputLayout.error = "This email is already being used"
                    validEmailInputLayout.isErrorEnabled = true
                }
            }

        }

    }
}