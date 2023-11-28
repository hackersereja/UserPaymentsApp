package ru.sergean.userpaymentsapp.presentation.login

import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.sergean.userpaymentsapp.R
import ru.sergean.userpaymentsapp.databinding.FragmentLoginBinding
import ru.sergean.userpaymentsapp.presentation.base.getErrorRes
import ru.sergean.userpaymentsapp.utils.showSnackbar

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel by viewModels<LoginViewModel>()
    private val binding by viewBinding(FragmentLoginBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeState()
        observeEffects()
    }

    private fun initViews() {
        binding.run {
            loginEditText.doOnTextChanged(viewModel::onLoginChanged)
            passwordEditText.doOnTextChanged(viewModel::onPasswordChanged)
            loginButton.setOnClickListener { viewModel.login() }
        }
    }

    private inline fun TextInputEditText.doOnTextChanged(crossinline action: (String) -> Unit) {
        doOnTextChanged { text, _, _, _ -> action(text.toString()) }
    }

    private fun observeState() {
        lifecycleScope.launch {
            viewModel.state.flowWithLifecycle(lifecycle).collect { state ->
                binding.run {
                    loginProgress.isInvisible = !state.loading
                    loginButton.isEnabled = !state.loading
                    loginInput.isEnabled = !state.loading
                    passwordInput.isEnabled = !state.loading
                }
            }
        }
    }

    private fun observeEffects() {
        lifecycleScope.launch {
            viewModel.effects.flowWithLifecycle(lifecycle).collect { event ->
                when (event) {
                    is LoginEffect.SuccessLogin -> showSuccess()
                    is LoginEffect.Error -> showError(throwable = event.throwable)
                }
            }
        }
    }

    private fun showSuccess() {
        showSnackbar(text = getString(R.string.success_login))
    }

    private fun showError(throwable: Throwable) {
        showSnackbar(text = getString(throwable.getErrorRes()))
    }
}
