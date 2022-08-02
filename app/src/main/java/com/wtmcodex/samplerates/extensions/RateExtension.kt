package com.wtmcodex.samplerates.extensions

import com.wtmcodex.samplerates.model.rate.Rate

fun Rate.getFormattedPrice(): String = String.format("%.4f", price)