package ru.sergean.userpaymentsapp.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.sergean.userpaymentsapp.domain.auth.LoginUseCase
import ru.sergean.userpaymentsapp.domain.exceptions.ApiError
import ru.sergean.userpaymentsapp.domain.exceptions.ValidateError
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(value = LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    private val _effects = MutableSharedFlow<LoginEffect>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val effects: SharedFlow<LoginEffect> = _effects.asSharedFlow()

    fun onLoginChanged(login: String) {
        _state.value = _state.value.copy(login = login)
    }

    fun onPasswordChanged(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun login() {
        val login = state.value.login
        val password = state.value.password

        if (!login.isNullOrEmpty() && login.isNotBlank() && !password.isNullOrEmpty() && password.isNotBlank()) {
            tryLogin(login, password)
        } else {
            _effects.tryEmit(LoginEffect.Error(throwable = ValidateError()))
        }
    }

    private fun tryLogin(login: String, password: String) {
        _state.changeLoading(loading = true)
        viewModelScope.launch {
            loginUseCase.invoke(login = login, password = password)
                .onSuccess { success ->
                    _state.changeLoading(loading = false)

                    if (success) {
                        _effects.emit(LoginEffect.SuccessLogin)
                    } else {
                        _effects.emit(LoginEffect.Error(throwable = ApiError()))
                    }
                }
                .onFailure { error ->
                    _state.changeLoading(loading = false)
                    _effects.emit(LoginEffect.Error(throwable = error))
                }
        }
    }

    private fun MutableStateFlow<LoginState>.changeLoading(loading: Boolean) {
        value = value.copy(loading = loading)
    }
}
