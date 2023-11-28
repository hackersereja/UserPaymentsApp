package ru.sergean.userpaymentsapp.presentation.container

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.sergean.userpaymentsapp.domain.auth.LogoutUseCase
import ru.sergean.userpaymentsapp.domain.auth.UserLoggedUseCase
import javax.inject.Inject

@HiltViewModel
class ContainerViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    getUserLoggedUseCase: UserLoggedUseCase,
) : ViewModel() {

    val userLogged: StateFlow<Boolean> = getUserLoggedUseCase.userLogged
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = false,
        )

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
        }
    }
}
