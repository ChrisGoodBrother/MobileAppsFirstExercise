package com.example.firstexercise

import android.app.Application

class MyApp: Application() {

    lateinit var credentialsManager: CredentialsManager

    override fun onCreate() {
        super.onCreate()

        credentialsManager = CredentialsManager()
    }
}