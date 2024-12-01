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

        val result = credentialsManager.login(email, pass)
        assertFalse(result)
    }

    @Test
    fun givenRightCredentials_thenReturnTrue() {
        val credentialsManager = CredentialsManager()
        val email = "test@g.c"
        val pass = "1234"

        val result = credentialsManager.login(email, pass)
        assertTrue(result)
    }

    //Given proper credentials, when user registers, then create account
    @Test
    fun givenProperCredentials_whenUserRegisters_thenCreateAccount() {
        val credentialsManager = CredentialsManager()

        credentialsManager.register("Fullname", "another@te.st", "600 600 600", "1234")

        val isLoginSuccess = credentialsManager.login("another@te.st", "1234")
        assertTrue(isLoginSuccess)
    }

    //Given already used email, when user registers, then return error
    @Test
    fun alreadyUsedEmail_whenUserRegisters_thenReturnError() {
        val credentialsManager = CredentialsManager()

        val isRegisterSuccess = credentialsManager.register("Fullname", "test@g.c", "600 600 600", "1234")
        assertTrue(!isRegisterSuccess)
    }

    //Given already used email with different casing, when user registers, then return error
    @Test
    fun alreadyUsedEmailDifferentCasing_whenUserRegisters_thenReturnError() {
        val credentialsManager = CredentialsManager()

        val isRegisterSuccess = credentialsManager.register("Fullname", "TeST@G.C", "600 600 600", "1234")
        assertTrue(!isRegisterSuccess)
    }

    //Given used email with different casing, when user logs in, then return success
    @Test
    fun givenUsedEmailDifferentCasing_whenUserLogsIn_thenReturnSuccess() {
        val credentialsManager = CredentialsManager()

        credentialsManager.register("Fullname", "another@te.st", "600 600 600", "1234")

        val isLoginSuccess = credentialsManager.login("anoTHER@te.ST", "1234")
        assertTrue(isLoginSuccess)
    }
}