package ru.sergean.userpaymentsapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.sergean.userpaymentsapp.data.auth.AuthRepositoryImpl
import ru.sergean.userpaymentsapp.data.payments.PaymentRepositoryImpl
import ru.sergean.userpaymentsapp.domain.auth.AuthRepository
import ru.sergean.userpaymentsapp.domain.payments.PaymentsRepository

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun bindPaymentRepository(paymentRepositoryImpl: PaymentRepositoryImpl): PaymentsRepository
}
