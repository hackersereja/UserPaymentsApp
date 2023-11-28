package ru.sergean.userpaymentsapp.domain.payments

import java.util.Date

data class Payment(
    val id: Int,
    val title: String,
    val amount: Int,
    val created: Date,
)
