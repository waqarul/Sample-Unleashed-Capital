package com.wtmcodex.samplerates.ui.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wtmcodex.samplerates.R
import com.wtmcodex.samplerates.data.base.ScreenState
import com.wtmcodex.samplerates.extensions.getFormattedPrice
import com.wtmcodex.samplerates.model.rate.Rate
import com.wtmcodex.samplerates.ui.common.ErrorItem
import com.wtmcodex.samplerates.ui.common.HomeAppBar
import com.wtmcodex.samplerates.ui.common.LoadingView

@Composable
fun HomeScreen(
    openSearch: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()
    Scaffold(topBar = {
        HomeAppBar(
            title = stringResource(id = R.string.home_app_bar_title),
            searchClick = { openSearch.invoke() }
        )
    },
        scaffoldState = scaffoldState,
        content = {
            HomeScreen(viewModel)
        }
    )
}

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val state by viewModel.uiState.collectAsState()

    when (state.screenState) {
        is ScreenState.Loading -> {
            LoadingView(modifier = Modifier.fillMaxSize())
        }
        is ScreenState.Error -> {
            ErrorItem {
                viewModel.getRates()
            }
        }
        is ScreenState.Success -> {
            state.rates?.let {
                RateDetails(
                    rates = it
                )
            }
        }
    }
}

@Composable
fun RateDetails(
    rates: List<Rate>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(rates) { rate ->
            RateItem(rate = rate)
        }
    }
}


@Composable
fun RateItem(rate: Rate) {
    Card(
        modifier = Modifier
            .padding(
                start = 24.dp,
                end = 24.dp,
                top = 12.dp,
                bottom = 12.dp
            )
            .fillMaxWidth()
            .background(Color.Transparent),
        shape = RoundedCornerShape(15.dp),
        elevation = 12.dp
    ) {
        Column(

        ) {
            TextItem(
                text = rate.symbol,
                modifier = Modifier.padding(all = 8.dp),
                textStyle = typography.displaySmall
            )
            TextItem(
                text = rate.getFormattedPrice(),
                modifier = Modifier.padding(all = 8.dp),
                textStyle = typography.headlineMedium,
                color = if (rate.isIncreased) Color.Green else Color.Red
            )
        }
    }
}

@Composable
fun TextItem(text: String, modifier: Modifier, textStyle: TextStyle, color: Color = Color.Black) {
    Text(
        text = text,
        color = color,
        maxLines = 1,
        style = textStyle,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun RateDetailsPreview() {
    RateItem(Rate("USDPKR", 1.9463045))
}