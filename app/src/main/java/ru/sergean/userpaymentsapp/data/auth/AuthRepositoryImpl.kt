package ru.sergean.userpaymentsapp.data.auth

import kotlinx.coroutines.flow.Flow
import ru.sergean.userpaymentsapp.data.login.LoginRequest
import ru.sergean.userpaymentsapp.data.network.AppService
import ru.sergean.userpaymentsapp.data.network.models.SuccessStatus
import ru.sergean.userpaymentsapp.domain.auth.AuthRepository
import ru.sergean.userpaymentsapp.domain.exceptions.ApiError
import ru.sergean.userpaymentsapp.domain.exceptions.IncorrectUserDataError
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authPreferences: AuthPreferences,
    private val appService: AppService,
) : AuthRepository {

    private val errors = mapOf(
        INCORRECT_USER_DATA_ERROR to IncorrectUserDataError(),
    )

    override val userLogged: Flow<Boolean> = authPreferences.logged

    override suspend fun login(login: String, password: String): Result<Boolean> = runCatching {
        val request = LoginRequest(login = login, password = password)
        val response = appService.login(request)

        return when {
            response.response != null -> {
                val token = response.response.token
                authPreferences.saveToken(token)
                authPreferences.login()
                Result.success(value = true)
            }

            response.success == SuccessStatus.FALSE && response.error != null -> {
                val exceptions = errors[response.error.code] ?: ApiError()
                Result.failure(exceptions)
            }

            else -> {
                Result.success(value = false)
            }
        }
    }

    override suspend fun logout(): Result<Boolean> = runCatching {
        authPreferences.clearToken()
        authPreferences.logout()
        return Result.success(value = true)
    }

    companion object {
        private const val INCORRECT_USER_DATA_ERROR = 1003
    }
}
