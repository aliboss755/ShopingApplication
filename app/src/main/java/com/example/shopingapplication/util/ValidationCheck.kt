package com.example.shopingapplication.util

import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Patterns
import java.util.regex.Pattern

fun validateEmail(email: String): RegisterVolition {
    if (email.isEmpty())
        return RegisterVolition.Failed("Email cannot be empty")
    if (Patterns.EMAIL_ADDRESS.equals(email))
        return RegisterVolition.Failed("Wrong email format")
    return RegisterVolition.Success
}

fun validatePassword(password: String): RegisterVolition {
    if (password.isEmpty())
        return RegisterVolition.Failed("password cannot be empty")
    if (password.length < 6)
        return RegisterVolition.Failed("Password should contains char")
    return RegisterVolition.Success
}