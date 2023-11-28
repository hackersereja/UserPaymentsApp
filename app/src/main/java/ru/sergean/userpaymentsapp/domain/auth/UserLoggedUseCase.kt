package ru.sergean.userpaymentsapp.domain.auth

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserLoggedUseCase @Inject constructor(
    authRepository: AuthRepository,
) {
    val userLogged: Flow<Boolean> = authRepository.userLogged
}
