package com.example.dashboard.base

import com.google.gson.annotations.SerializedName

data class BaseResponseModel<T>(
    @SerializedName("http_code") var httpCode: Int? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("data") val data: T? = null
)
