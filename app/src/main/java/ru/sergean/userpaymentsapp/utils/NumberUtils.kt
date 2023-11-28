package ru.sergean.userpaymentsapp.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun Int.toRubleString(): String {
    val symbols = DecimalFormatSymbols(Locale.getDefault())
    symbols.groupingSeparator = ' '
    val formatter = DecimalFormat("#,###", symbols)
    return "${formatter.format(this)} ₽"
}
