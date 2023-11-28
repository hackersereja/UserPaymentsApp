package ru.sergean.userpaymentsapp.presentation.payments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.sergean.userpaymentsapp.R
import ru.sergean.userpaymentsapp.databinding.FragmentPaymentBinding
import ru.sergean.userpaymentsapp.presentation.base.getErrorRes
import ru.sergean.userpaymentsapp.presentation.payments.adapter.PaymentAdapter
import ru.sergean.userpaymentsapp.presentation.payments.adapter.PaymentItemDecoration
import ru.sergean.userpaymentsapp.presentation.payments.adapter.PaymentUiModel
import ru.sergean.userpaymentsapp.presentation.payments.adapter.mapToUiModel

@AndroidEntryPoint
class PaymentFragment : Fragment(R.layout.fragment_payment) {

    private val viewModel by viewModels<PaymentViewModel>()
    private val binding by viewBinding(FragmentPaymentBinding::bind)

    private val paymentAdapter = PaymentAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeState()
    }

    private fun initViews() {
        binding.paymentRecyclerView.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = paymentAdapter
            addItemDecoration(PaymentItemDecoration(innerMargin = 8, horizontalMargin = 16))
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            viewModel.state.flowWithLifecycle(lifecycle).collect { state ->
                when (state) {
                    is PaymentState.Loading -> showLoading()
                    is PaymentState.Content -> showContent(state.payments.map { payment -> payment.mapToUiModel() })
                    is PaymentState.Error -> showError(throwable = state.throwable)
                }
            }
        }
    }

    private fun showLoading() {
        binding.run {
            paymentProgress.isVisible = true
            paymentRecyclerView.isVisible = false
            paymentError.isVisible = false
        }
    }

    private fun showContent(payments: List<PaymentUiModel>) {
        binding.run {
            paymentProgress.isVisible = false
            paymentRecyclerView.isVisible = true
            paymentError.isVisible = false
        }
        paymentAdapter.submitList(payments)
    }

    private fun showError(throwable: Throwable) {
        binding.run {
            paymentProgress.isVisible = false
            paymentRecyclerView.isVisible = false
            paymentError.isVisible = true
            paymentError.text = getString(throwable.getErrorRes())
        }
    }
}
