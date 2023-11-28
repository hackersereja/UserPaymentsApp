package ru.sergean.userpaymentsapp.data.login

import ru.sergean.userpaymentsapp.data.network.models.Error
import ru.sergean.userpaymentsapp.data.network.models.SuccessStatus

data class LoginResponse(
    val success: SuccessStatus,
    val response: LoginRemoteModel? = null,
    val error: Error? = null,
)
