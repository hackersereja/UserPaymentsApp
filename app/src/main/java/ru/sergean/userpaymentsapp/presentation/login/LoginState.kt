package ru.sergean.userpaymentsapp.presentation.login

data class LoginState(
    val loading: Boolean = false,
    val login: String? = null,
    val password: String? = null,
)
