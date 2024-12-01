package com.example.firstexercise

class CredentialsManager {

    object Data {
        val credentialsMap = mutableMapOf(
            Pair("test@g.c", "1234")
        )
    }

    private val emailPattern = (("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"))

    fun register(fullname: String, email: String, phoneNumber: String, password: String): Boolean {
        if(!Data.credentialsMap.contains(email.lowercase())) {
            Data.credentialsMap.put(email.lowercase(), password)
            return true
        }

        return false
    }

    fun login(email: String, password: String): Boolean {
        return Data.credentialsMap[email.lowercase()].equals(password)
    }

    fun fullNameIsNotEmpty(fullname: String): Boolean {
        return fullname.isNotEmpty()
    }

    fun phoneNumberIsNotEmpty(phoneNumber: String): Boolean {
        return phoneNumber.isNotEmpty()
    }

    fun isEmailValid(email: String): Boolean {
        return Regex(emailPattern).matches(email)
    }

    fun passwordIsNotEmpty(password: String): Boolean {
        return password.isNotEmpty()
    }
}