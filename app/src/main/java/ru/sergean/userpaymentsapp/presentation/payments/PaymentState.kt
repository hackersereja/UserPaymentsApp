package ru.sergean.userpaymentsapp.presentation.payments

import ru.sergean.userpaymentsapp.domain.payments.Payment

sealed interface PaymentState {
    data object Loading : PaymentState
    data class Content(val payments: List<Payment>) : PaymentState
    data class Error(val throwable: Throwable) : PaymentState
}
