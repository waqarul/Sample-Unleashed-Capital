package com.wtmcodex.samplerates.data.mapper

import com.wtmcodex.samplerates.model.network.RateDTO
import com.wtmcodex.samplerates.model.rate.Rate

fun List<RateDTO>.toDomain(): List<Rate> =
    this.map {
        Rate(
            symbol = it.symbol,
            price = it.price
        )
    }
