package ru.sergean.userpaymentsapp.presentation.payments.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.sergean.userpaymentsapp.domain.payments.Payment
import ru.sergean.userpaymentsapp.utils.format
import ru.sergean.userpaymentsapp.utils.toRubleString

data class PaymentUiModel(
    val id: Int,
    val title: String,
    val amount: String,
    val created: String,
)

class PaymentDiffCallBack : DiffUtil.ItemCallback<PaymentUiModel>() {
    override fun areItemsTheSame(oldItem: PaymentUiModel, newItem: PaymentUiModel) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PaymentUiModel, newItem: PaymentUiModel) = oldItem == newItem
}

fun Payment.mapToUiModel(): PaymentUiModel {
    return PaymentUiModel(
        id = id,
        title = title,
        amount = amount.toRubleString(),
        created = created.format(),
    )
}
