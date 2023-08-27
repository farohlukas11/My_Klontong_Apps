package com.dicoding.myklontongapps.ui.screen.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.myklontongapps.di.Injection
import com.dicoding.myklontongapps.ui.ViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.myklontongapps.model.ImageFood
import com.dicoding.myklontongapps.ui.common.UiState
import com.dicoding.myklontongapps.utils.Utils
import com.dicoding.myklontongapps.R
import com.dicoding.myklontongapps.model.KlontongFood
import com.dicoding.myklontongapps.ui.components.*

@Composable
fun DetailScreen(
    productId: Long?,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
    navigateToCart: () -> Unit,
    navigateToDetail: (Long) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        var count = 0
        var totalAvailable = 0
        var orderCount by rememberSaveable { mutableStateOf(count) }

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
                .fillMaxWidth()
        ) {
            HomeMenu(
                productId = productId,
                viewModel = viewModel,
                navigateBack = navigateBack,
                dataCount = { c, a ->
                    count = c
                    totalAvailable = a
                }
            )
            RandomMenu(
                viewModel = viewModel,
                navigateToDetail = navigateToDetail
            )
        }
        OrderFood(
            orderCount = orderCount,
            onProductIncreased = {
                if (orderCount < totalAvailable) {
                    orderCount++
                }
            },
            onProductDecreased = { if (orderCount > 0) orderCount-- },
            toCart = {
                if (productId != null) {
                    viewModel.updateOrderKlontongFood(productId = productId, count = orderCount)
                    navigateToCart()
                }
            }
        )
    }
}

@Composable
fun HomeMenu(
    productId: Long?,
    viewModel: DetailViewModel,
    navigateBack: () -> Unit,
    dataCount: (Int, Int) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        productId?.let { id ->
            when (uiState) {
                is UiState.Loading -> {
                    CircularProgressIndicator()
                    viewModel.getKlontongFoodById(id)
                }
                is UiState.Success -> {
                    val data = uiState.data

                    DetailContent(
                        listImageUrl = data.listImageUrl,
                        nameFood = data.nameFood,
                        priceFood = data.priceFood,
                        descriptionFood = data.descriptionFood,
                        ratingFood = data.ratingFood,
                        totalAvailable = data.totalAvailable,
                        onBackClick = navigateBack,
                        onAddFavourite = {
                            viewModel.updateFavouriteKlontongFood(data.id, it)
                        },
                        fav = data.isFavourite
                    )
                    dataCount(data.count, data.totalAvailable ?: 0)
                }
                is UiState.Error -> {
                    showToast(message = uiState.errorMessage)
                }
            }
        }
    }
}

@Composable
fun DetailContent(
    listImageUrl: List<ImageFood>?,
    nameFood: String?,
    priceFood: Int?,
    descriptionFood: String?,
    ratingFood: Double?,
    totalAvailable: Int?,
    onBackClick: () -> Unit,
    onAddFavourite: (Boolean) -> Unit,
    fav: Boolean
) {
    var checkedState by rememberSaveable { mutableStateOf(fav) }

    HeaderDetailImageSlider(listImageUrl = listImageUrl, onBackClick = onBackClick)
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    text = Utils.rupiahFormatter(priceFood),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .width(200.dp)
                )
                Text(
                    text = nameFood ?: "",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = 24.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Light,
                    ),
                    modifier = Modifier
                        .width(200.dp)
                )
            }
            FavoriteButton(
                isFavorite = checkedState,
                onAddFavourite = {
                    checkedState = it
                    onAddFavourite(checkedState)
                },
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Divider(color = Color(0xFFC4C4C4), thickness = 2.dp, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Tersedia...",
                maxLines = 1,
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Light,
                )
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "${totalAvailable ?: 0} pcs",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                ),
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        RatingCard(
            nameFood = nameFood,
            ratingFood = ratingFood
        )
        Spacer(modifier = Modifier.height(20.dp))
        Divider(color = Color(0xFFC4C4C4), thickness = 2.dp, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Deskripsi Produk",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .width(160.dp)
        )
        Text(
            text = descriptionFood ?: "",
            style = TextStyle(
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Justify
            ),
        )
        Spacer(modifier = Modifier.height(20.dp))
        Divider(color = Color(0xFFC4C4C4), thickness = 2.dp, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun RandomMenu(
    viewModel: DetailViewModel,
    navigateToDetail: (Long) -> Unit
) {
    viewModel.uiStateRandom.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                CircularProgressIndicator()
                viewModel.getAllKlontongFoods()
            }
            is UiState.Success -> {
                DetailRandomMenu(
                    klontongFoods = uiState.data,
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {
                showToast(message = uiState.errorMessage)
            }
        }
    }
}

@Composable
fun DetailRandomMenu(
    klontongFoods: List<KlontongFood>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit
) {
    Text(
        text = "Lainnya",
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = TextStyle(
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .width(160.dp)
    )
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(
            klontongFoods,
            key = { it.id }) { food: KlontongFood ->
            if ((food.listImageUrl != null) && food.listImageUrl.isNotEmpty()) {
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
fun OrderFood(
    orderCount: Int,
    onProductIncreased: () -> Unit,
    onProductDecreased: () -> Unit,
    toCart: () -> Unit

) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(color = Color(0xFFE9E9E9))
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        ProductCounter(
            orderId = 1,
            orderCount = orderCount,
            onProductIncreased = onProductIncreased,
            onProductDecreased = onProductDecreased
        )
        Spacer(modifier = Modifier.height(10.dp))
        OrderButton(
            text = "Order",
            onClick = toCart,
            enabled = orderCount > 0
        )
    }
}

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    onAddFavourite: (Boolean) -> Unit
) {
    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            onAddFavourite(it)
        }) {
        Image(
            painter = painterResource(
                id = if (isFavorite)
                    R.drawable.ic_favorite
                else
                    R.drawable.ic_favorite_border
            ),
            contentDescription = "Favorite",
            modifier = Modifier
                .size(30.dp)
        )
    }
}