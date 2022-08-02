package com.wtmcodex.samplerates.ui.features.home

import com.wtmcodex.samplerates.data.base.ScreenState
import com.wtmcodex.samplerates.model.rate.Rate

data class HomeScreenState(
    val screenState: ScreenState = ScreenState.Loading,
    val rates: List<Rate>? = null,
    val error: String? = null,
) {
    companion object {
        val Empty = HomeScreenState()
    }
}
