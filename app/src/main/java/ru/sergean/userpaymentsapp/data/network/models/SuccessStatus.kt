package ru.sergean.userpaymentsapp.data.network.models

import com.google.gson.annotations.SerializedName

enum class SuccessStatus {
    @SerializedName("true")
    TRUE,

    @SerializedName("false")
    FALSE,
}
