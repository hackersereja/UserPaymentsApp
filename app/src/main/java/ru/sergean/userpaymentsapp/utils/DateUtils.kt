package ru.sergean.userpaymentsapp.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.format(): String {
    val dateFormat = SimpleDateFormat("hh:mm dd.MM.yyyy", Locale("ru", "RU"))
    return dateFormat.format(this)
}
