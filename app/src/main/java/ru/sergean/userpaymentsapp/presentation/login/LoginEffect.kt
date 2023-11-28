package ru.sergean.userpaymentsapp.presentation.login

sealed interface LoginEffect {
    data class Error(val throwable: Throwable) : LoginEffect
    data object SuccessLogin : LoginEffect
}
