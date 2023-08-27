package com.dicoding.myklontongapps.ui.screen.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.myklontongapps.di.Injection
import com.dicoding.myklontongapps.ui.ViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.myklontongapps.model.KlontongFood
import com.dicoding.myklontongapps.ui.common.UiState
import com.dicoding.myklontongapps.ui.components.CartItem
import com.dicoding.myklontongapps.ui.components.EmptyScreen
import com.dicoding.myklontongapps.ui.components.OrderButton
import com.dicoding.myklontongapps.ui.components.showToast
import com.dicoding.myklontongapps.ui.screen.popup.PopUpScreen
import com.dicoding.myklontongapps.utils.Utils

@Composable
fun CartScreen(
    viewModel: CartViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateToDetail: (Long) -> Unit,
) {
    var data: List<KlontongFood>? = null
    var popUpControl by rememberSaveable {
        mutableStateOf(false)
    }

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->

        when (uiState) {
            is UiState.Loading -> {
                CircularProgressIndicator()
                viewModel.getAddedOrderKlontongFoods()
            }
            is UiState.Success -> {
                data = uiState.data
            }
            is UiState.Error -> {
                showToast(message = uiState.errorMessage)
            }
        }
    }

    data?.let {
        val orderList = remember {
            it.toMutableStateList()
        }

        val totalPrice = orderList.sumOf { food ->
            food.totalPrice
        }

        if (orderList.isNotEmpty()) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    ListCart(
                        cartFoods = orderList,
                        navigateToDetail = navigateToDetail,
                        onProductCountChanged = { id, count ->
                            viewModel.updateOrderKlontongFood(id, count)
                            orderList.forEach {
                                if (it.id == id) {
                                    it.count = count
                                    it.totalPrice = count * (it.priceFood ?: 0)
                                }
                            }
                        },
                        onRemove = { orderDelete ->
                            orderList.remove(orderDelete)
                        }
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(color = Color(0xFFE9E9E9))
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Total Harga",
                            style = TextStyle(
                                fontSize = 20.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Light
                            )
                        )
                        Text(
                            text = Utils.rupiahFormatter(totalPrice),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                fontSize = 20.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.SemiBold
                            ),
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                                .width(140.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    OrderButton(
                        text = "CheckOut",
                        enabled = orderList.isNotEmpty(),
                    ) {
                        popUpControl = true
                    }
                }
            }
        } else {
            EmptyScreen(message = "Belum ada Produk di Keranjang")
        }
    }
    PopUpScreen(
        popUpControl = popUpControl,
        onDismiss = { popUpControl = false }
    )

}

@Composable
fun ListCart(
    cartFoods: List<KlontongFood>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
    onProductCountChanged: (id: Long, count: Int) -> Unit,
    onRemove: (KlontongFood) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(cartFoods.size) { index ->
            val data = cartFoods[index]
            if ((data.listImageUrl != null) && data.listImageUrl.isNotEmpty()) {

                CartItem(
                    imageUrl = data.listImageUrl[0].imageUrl,
                    nameFood = data.nameFood,
                    priceFood = data.priceFood,
                    count = data.count,
                    onProductCountChanged = {
                        onProductCountChanged(data.id, it)
                    },
                    navigateToDetail = { navigateToDetail(data.id) },
                    onRemove = {
                        onRemove(data)
                    },
                )
            }
        }
    }
}

