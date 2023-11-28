package ru.sergean.userpaymentsapp.presentation.payments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sergean.userpaymentsapp.databinding.ItemPaymentBinding

class PaymentAdapter : ListAdapter<PaymentUiModel, PaymentViewHolder>(PaymentDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPaymentBinding.inflate(inflater, parent, false)
        return PaymentViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        holder.bind(payment = getItem(position))
    }
}

class PaymentViewHolder(private val binding: ItemPaymentBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(payment: PaymentUiModel) {
        binding.run {
            itemPaymentTitle.text = payment.title
            itemPaymentAmount.text = payment.amount
            itemPaymentDate.text = payment.created
        }
    }
}
