package com.wtmcodex.samplerates.model.network

import com.google.gson.annotations.SerializedName


class BaseDTO<T>(
    @SerializedName("rates") var data: T? = null
)