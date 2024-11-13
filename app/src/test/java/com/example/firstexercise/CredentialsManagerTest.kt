package com.example.firstexercise

import org.junit.Assert.*
import org.junit.Test

class CredentialsManagerTest {

    @Test
    //fun `Give empty email, Then return false`() {
    fun givenEmptyEmail_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val email = ""

        val result = credentialsManager.isEmailValid(email)
        assertEquals(false, result)
    }

    @Test
    fun givenWrongFormatEmail_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val email = "not an email"

        val result = credentialsManager.isEmailValid(email)
        assertFalse(result)
    }

    @Test
    fun givenProperEmailFormat_thenReturnTrue() {
        val credentialsManager = CredentialsManager()
        val email = "email@email.com"

        val result = credentialsManager.isEmailValid(email)
        assertTrue(result)
    }

    @Test
    fun givenEmptyPassword_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val password = ""

        val result = credentialsManager.passwordIsNotEmpty(password)
        assertFalse(result)
    }

    @Test
    fun givenPassword_thenReturnTrue() {
        val credentialsManager = CredentialsManager()
        val password = "asdf"

        val result = credentialsManager.passwordIsNotEmpty(password)
        assertTrue(result)
    }

    @Test
    fun givenWrongCredentials_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val email = "wrong email"
        val pass = "wrong password"

        val result = credentialsManager.credentialsAreCorrect(email, pass)
        assertFalse(result)
    }

    @Test
    fun givenRightCredentials_thenReturnTrue() {
        val credentialsManager = CredentialsManager()
        val email = "test"
        val pass = "1234"

        val result = credentialsManager.credentialsAreCorrect(email, pass)
        assertTrue(result)
    }

    /*
    fixed credentials test - 1234)
        Error - Wrong credentials
        Error - Connection timeout
        Success - Successful login
     */
}