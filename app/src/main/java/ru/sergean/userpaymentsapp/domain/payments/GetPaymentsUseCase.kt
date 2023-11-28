package ru.sergean.userpaymentsapp.domain.payments

import javax.inject.Inject

class GetPaymentsUseCase @Inject constructor(
    private val paymentsRepository: PaymentsRepository,
) {
    suspend operator fun invoke(): Result<List<Payment>> {
        return paymentsRepository.getPayments()
    }
}
