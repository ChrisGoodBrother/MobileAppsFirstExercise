package com.example.firstexercise

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class AuthenticationActivity : AppCompatActivity(
    R.layout.activity_authentication),
    RegisterFragment.EventListener,
    LoginFragment.EventListener
{
    private val credentialsManager = CredentialsManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //If there's no Instance State then place RegisterFragment
        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.authentication_fragment_container_view, RegisterFragment(credentialsManager))
                .commit()
        }
    }

    //Overriding Interface Function
    //When function is called, LoginFragment will be added to the Fragment Container
    override fun goToLogin() {
        Log.d("PREVIEW", "Login Pressed")
        supportFragmentManager.beginTransaction()
            .replace(R.id.authentication_fragment_container_view, LoginFragment(credentialsManager))
            .addToBackStack(null)
            .commit()
    }

    //Overriding Interface Function
    //When function is called, RegisterFragment will be added to the Fragment Container
    override fun goToRegister() {
        Log.d("PREVIEW", "Register Pressed")
        supportFragmentManager.beginTransaction()
            .replace(R.id.authentication_fragment_container_view, RegisterFragment(credentialsManager))
            .addToBackStack(null)
            .commit()
    }

    //Overriding Interface Function
    //When function is called, MainActivity will replace AuthenticationActivity
    override fun goToApp() {
        Log.d("PREVIEW", "App Pressed")
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }
}