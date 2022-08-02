package com.wtmcodex.samplerates.ui.features

import androidx.navigation.NavHostController
import com.wtmcodex.samplerates.ui.features.Destinations.Search

object Destinations {
    const val Home = "home"
    const val Search = "search"
}

class Actions(navHostController: NavHostController) {
    val openSearch: () -> Unit = {
        navHostController.navigate(Search)
    }
}