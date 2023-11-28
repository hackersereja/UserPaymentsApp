package ru.sergean.userpaymentsapp.presentation.payments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import ru.sergean.userpaymentsapp.domain.payments.GetPaymentsUseCase
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val getPaymentsUseCase: GetPaymentsUseCase,
) : ViewModel() {

    val state = flow {
        getPaymentsUseCase()
            .onSuccess { emit(PaymentState.Content(it)) }
            .onFailure { emit(PaymentState.Error(it)) }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = PaymentState.Loading,
    )
}
