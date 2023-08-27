package com.dicoding.myklontongapps.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.dicoding.myklontongapps.ui.ViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.myklontongapps.model.KlontongFood
import com.dicoding.myklontongapps.model.User
import com.dicoding.myklontongapps.ui.common.UiState
import com.dicoding.myklontongapps.ui.components.FoodOptionItem
import com.dicoding.myklontongapps.ui.components.FoodRatingItem
import com.dicoding.myklontongapps.ui.components.HeaderHome
import com.dicoding.myklontongapps.ui.components.showToast

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    navigateToDetail: (Long) -> Unit,
    navigateToProfile: () -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        HeaderHomeContent(viewModel = viewModel) {
            navigateToProfile()
        }
        HomeMenu(
            viewModel = viewModel,
            navigateToDetail = navigateToDetail,
            modifier = modifier
        )
    }
}

@Composable
fun HeaderHomeContent(
    viewModel: HomeViewModel,
    toProfile: () -> Unit,
) {
    viewModel.uiStateUser.collectAsState(initial = UiState.Loading).value.let { uiStateUser: UiState<User> ->
        when (uiStateUser) {
            is UiState.Loading -> {
                CircularProgressIndicator()
                viewModel.getUser()
            }
            is UiState.Success -> {
                val dataUser = uiStateUser.data
                HeaderHome(
                    username = dataUser.username,
                    image = dataUser.imageProfile,
                    toProfile = toProfile,
                )
            }
            is UiState.Error -> {
                showToast(message = uiStateUser.errorMessage)
            }
        }
    }
}

@Composable
fun HomeMenu(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    navigateToDetail: (Long) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->

        when (uiState) {
            is UiState.Loading -> {
                CircularProgressIndicator()
                viewModel.getAllKlontongFoods()
            }
            is UiState.Success -> {
                HomeOptionsMenu(
                    klontongFoods = uiState.data,
                    navigateToDetail = navigateToDetail,
                    modifier = modifier
                )
            }
            is UiState.Error -> {
                showToast(message = uiState.errorMessage)
            }
        }
    }

    viewModel.uiStateRating.collectAsState(initial = UiState.Loading).value.let { uiStateRating ->

        when (uiStateRating) {
            is UiState.Loading -> {
                viewModel.getFavouriteRatingFood()
            }
            is UiState.Success -> {
                HomeRatingMenu(
                    klontongFoods = uiStateRating.data,
                    navigateToDetail = navigateToDetail,
                    modifier = modifier
                )
            }
            is UiState.Error -> {
                showToast(message = uiStateRating.errorMessage)
            }
        }
    }
}

@Composable
fun HomeOptionsMenu(
    klontongFoods: List<KlontongFood>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit
) {
    Text(
        text = "Beberapa Pilihan Menu",
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black
        ),
        modifier = Modifier.padding(horizontal = 16.dp)
    )
    Spacer(modifier = Modifier.height(5.dp))
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        items(
            klontongFoods,
            key = { it.id }) { food: KlontongFood ->
            if (!food.listImageUrl.isNullOrEmpty()) {
                FoodOptionItem(
                    imageUrl = food.listImageUrl[0].imageUrl,
                    nameFood = food.nameFood,
                    priceFood = food.priceFood,
                    modifier = Modifier.clickable {
                        navigateToDetail(food.id)
                    }
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}


@Composable
fun HomeRatingMenu(
    klontongFoods: List<KlontongFood>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit
) {
    Text(
        text = "Beberapa Menu Terlaris",
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black
        ),
        modifier = Modifier.padding(horizontal = 16.dp)
    )
    Spacer(modifier = Modifier.height(5.dp))
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        items(
            klontongFoods,
            key = { it.id }) { food: KlontongFood ->
            if ((food.listImageUrl != null) && food.listImageUrl.isNotEmpty()) {
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
    Spacer(modifier = Modifier.height(10.dp))
}