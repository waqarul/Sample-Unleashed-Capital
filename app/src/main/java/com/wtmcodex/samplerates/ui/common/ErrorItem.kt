package com.wtmcodex.samplerates.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wtmcodex.samplerates.R
import com.wtmcodex.samplerates.ui.theme.AppColor
import com.wtmcodex.samplerates.ui.theme.Black

@Composable
fun ErrorItem(buttonClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.home_screen_error_message),
            textAlign = TextAlign.Center,
            color = Black,
            style = typography.headlineMedium
        )
        Button(
            modifier = Modifier.padding(24.dp),
            onClick = buttonClick,
            colors = ButtonDefaults.buttonColors(containerColor = AppColor)
        ) {
            Text(
                text = stringResource(id = R.string.home_screen_retry),
                style = typography.labelLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorItemPreview() {
    ErrorItem {
        //do nothing
    }
}