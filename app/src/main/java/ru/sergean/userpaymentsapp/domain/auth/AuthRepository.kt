package ru.sergean.userpaymentsapp.domain.auth

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val userLogged: Flow<Boolean>

    suspend fun login(login: String, password: String): Result<Boolean>

    suspend fun logout(): Result<Boolean>
}
