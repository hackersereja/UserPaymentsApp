package ru.sergean.userpaymentsapp.data.payments

import ru.sergean.userpaymentsapp.domain.payments.Payment
import java.util.Date
import kotlin.math.roundToInt

fun PaymentRemoteModel.mapToPayment(): Payment {
    require(value = id != null) { "Id field not found" }
    require(value = created != null) { "Created field not found" }
    require(value = !title.isNullOrEmpty()) { "Incorrect title format" }
    require(value = title.isNotBlank()) { "Incorrect title format" }
    require(value = !amount.isNullOrEmpty()) { "Incorrect amount format" }
    require(value = amount.isNotBlank()) { "Incorrect amount format" }

    val amount = try {
        amount.toDouble().roundToInt()
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException("Incorrect amount format")
    }

    return Payment(
        id = this.id,
        title = this.title,
        amount = amount,
        created = Date(this.created),
    )
}

fun List<PaymentRemoteModel>.mapToPayments(): List<Payment> {
    return mapNotNull { payment ->
        try {
            payment.mapToPayment()
        } catch (e: IllegalArgumentException) {
            null
        }
    }
}
