package ru.sergean.userpaymentsapp.data.network.models

import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("error_code") val code: Int,
    @SerializedName("error_msg") val message: String,
)
