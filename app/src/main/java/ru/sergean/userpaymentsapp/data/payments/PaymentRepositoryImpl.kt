package ru.sergean.userpaymentsapp.data.payments

import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.take
import ru.sergean.userpaymentsapp.data.auth.AuthPreferences
import ru.sergean.userpaymentsapp.data.network.AppService
import ru.sergean.userpaymentsapp.domain.exceptions.ApiError
import ru.sergean.userpaymentsapp.domain.exceptions.TokenNotFoundError
import ru.sergean.userpaymentsapp.domain.payments.Payment
import ru.sergean.userpaymentsapp.domain.payments.PaymentsRepository
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    private val authPreferences: AuthPreferences,
    private val appService: AppService,
) : PaymentsRepository {
    override suspend fun getPayments(): Result<List<Payment>> = runCatching {
        val token = authPreferences.token.take(count = 1).last() ?: return Result.failure(TokenNotFoundError())
        val response = appService.fetchPayments(token).response ?: return Result.failure(ApiError())
        return Result.success(value = response.mapToPayments())
    }
}
