package ru.sergean.userpaymentsapp.presentation.base

import androidx.annotation.StringRes
import ru.sergean.userpaymentsapp.R
import ru.sergean.userpaymentsapp.domain.exceptions.ApiError
import ru.sergean.userpaymentsapp.domain.exceptions.IncorrectUserDataError
import ru.sergean.userpaymentsapp.domain.exceptions.TokenNotFoundError
import ru.sergean.userpaymentsapp.domain.exceptions.ValidateError

@StringRes
fun Throwable.getErrorRes(): Int {
    return when (this) {
        is IncorrectUserDataError -> R.string.incorrect_credentials
        is TokenNotFoundError -> R.string.token_error
        is ValidateError -> R.string.validate_error
        is ApiError -> R.string.api_error
        else -> R.string.unknown_error
    }
}
