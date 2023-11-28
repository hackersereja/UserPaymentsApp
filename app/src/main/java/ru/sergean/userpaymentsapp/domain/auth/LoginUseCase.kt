package ru.sergean.userpaymentsapp.domain.auth

import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(login: String, password: String): Result<Boolean> {
        return authRepository.login(login = login, password = password)
    }
}
