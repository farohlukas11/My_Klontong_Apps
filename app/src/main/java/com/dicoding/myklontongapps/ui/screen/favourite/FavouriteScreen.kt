package com.dicoding.myklontongapps.ui.screen.favourite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.myklontongapps.di.Injection
import com.dicoding.myklontongapps.model.KlontongFood
import com.dicoding.myklontongapps.ui.ViewModelFactory
import com.dicoding.myklontongapps.ui.common.UiState
import com.dicoding.myklontongapps.ui.components.EmptyScreen
import com.dicoding.myklontongapps.ui.components.FoodRatingItem
import com.dicoding.myklontongapps.ui.components.showToast

@Composable
fun FavouriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavouriteViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateToDetail: (Long) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                CircularProgressIndicator()
                viewModel.getFavouriteKlontongFoods()
            }
            is UiState.Success -> {
                if (uiState.data.isNotEmpty()) {
                    ListFavouriteMenu(
                        favouriteFoods = uiState.data,
                        navigateToDetail = navigateToDetail,
                        modifier = modifier
                    )
                } else {
                    EmptyScreen(message = "Belum Ada Produk Favorit")
                }
            }
            is UiState.Error -> {
                showToast(message = uiState.errorMessage)
            }
        }
    }
}

@Composable
fun ListFavouriteMenu(
    favouriteFoods: List<KlontongFood>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit
) {
    val spanCount = 2
    LazyVerticalGrid(
        columns = GridCells.Adaptive(130.dp),
        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        item(
            span = {
                GridItemSpan(spanCount)
            }
        ) {
            Text(
                text = "Menu Favorit Kamu...",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                ),
            )
        }
        items(favouriteFoods) { food ->
            if (food.listImageUrl != null && food.listImageUrl.isNotEmpty()) {
                FoodRatingItem(
                    imageUrl = food.listImageUrl[0].imageUrl,
                    nameFood = food.nameFood,
                    priceFood = food.priceFood,
                    ratingFood = food.ratingFood,
                    modifier = Modifier.clickable {
                        navigateToDetail(food.id)
                    }
                )
            }
        }
    }
}