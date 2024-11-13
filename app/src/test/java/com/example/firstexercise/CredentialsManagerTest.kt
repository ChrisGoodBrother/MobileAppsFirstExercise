package com.example.firstexercise

import org.junit.Assert.*
import org.junit.Test

class CredentialsManagerTest {

    @Test
    //fun `Give empty email, Then return false`() {
    fun givenEmptyEmail_thenReturnFalse() {
        val credentialsManager = CredentialsManager()

        assertEquals(
            false,
            credentialsManager.isEmailValid("")
        )
    }

    @Test
    fun givenProperEmailFormat_thenReturnTrue() {
        val credentialsManager = CredentialsManager()

        assertEquals(
            false,
            credentialsManager.isEmailValid("")
        )
    }

    @Test
    fun givenWrongFormatEmail_thenReturnFalse() {
        val credentialsManager = CredentialsManager()

        assertEquals(
            false,
            credentialsManager.isEmailValid("not an email")
        )
    }
}