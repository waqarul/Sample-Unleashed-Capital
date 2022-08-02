package com.wtmcodex.samplerates.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.wtmcodex.samplerates.ui.features.SampleRatesApp
import com.wtmcodex.samplerates.ui.theme.SampleRatesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleRatesTheme {
                SampleRatesApp()
            }
        }
    }
}