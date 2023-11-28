package ru.sergean.userpaymentsapp.data.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import ru.sergean.userpaymentsapp.data.login.LoginRequest
import ru.sergean.userpaymentsapp.data.login.LoginResponse
import ru.sergean.userpaymentsapp.data.payments.PaymentsResponse

interface AppService {

    @POST("/api-test/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET("/api-test/payments")
    suspend fun fetchPayments(@Header("token") token: String): PaymentsResponse
}
