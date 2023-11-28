package ru.sergean.userpaymentsapp.domain.auth

import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(): Result<Boolean> {
        return authRepository.logout()
    }
}
