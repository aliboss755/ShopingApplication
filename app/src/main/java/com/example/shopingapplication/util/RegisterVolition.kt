package com.example.shopingapplication.util

sealed class RegisterVolition {
    object Success : RegisterVolition()
    data class Failed(val data: String) : RegisterVolition()
}
data class RegisterFailedState(
    val email :RegisterVolition,
    val password :RegisterVolition
)

