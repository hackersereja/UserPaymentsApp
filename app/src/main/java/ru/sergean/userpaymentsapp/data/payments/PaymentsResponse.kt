package ru.sergean.userpaymentsapp.data.payments

import ru.sergean.userpaymentsapp.data.network.models.Error
import ru.sergean.userpaymentsapp.data.network.models.SuccessStatus

data class PaymentsResponse(
    val success: SuccessStatus,
    val error: Error?,
    val response: List<PaymentRemoteModel>?,
)
