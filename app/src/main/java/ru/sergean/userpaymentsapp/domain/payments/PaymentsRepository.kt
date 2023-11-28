package ru.sergean.userpaymentsapp.domain.payments

interface PaymentsRepository {
    suspend fun getPayments(): Result<List<Payment>>
}
