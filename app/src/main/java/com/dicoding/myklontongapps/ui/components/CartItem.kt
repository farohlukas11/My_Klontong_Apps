package com.dicoding.myklontongapps.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dicoding.myklontongapps.R
import com.dicoding.myklontongapps.model.FakeKlontongDataSource
import com.dicoding.myklontongapps.ui.theme.MyKlontongAppsTheme
import com.dicoding.myklontongapps.utils.Utils

@Composable
fun CartItem(
    imageUrl: String?,
    nameFood: String?,
    priceFood: Int?,
    count: Int,
    onProductCountChanged: (count: Int) -> Unit,
    modifier: Modifier = Modifier,
    navigateToDetail: () -> Unit,
    onRemove: () -> Unit
) {
    var orderCount by rememberSaveable { mutableStateOf(count) }

    Card(
        backgroundColor = Color.White,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier.fillMaxWidth(),
        elevation = 3.dp,
    ) {
        Row(modifier = modifier.fillMaxWidth()) {
            val modifierImage =
                Modifier
                    .size(150.dp)
                    .clickable {
                        navigateToDetail()
                    }

            if (imageUrl != null) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifierImage
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.image_error),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifierImage
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .clickable {
                            navigateToDetail()
                        },
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = nameFood ?: "",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Light
                        ),
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .width(160.dp)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = Utils.rupiahFormatter(priceFood),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .width(160.dp)
                    )
                    Spacer(modifier = Modifier.height(35.dp))
                    Text(
                        text = "$count Item",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .width(160.dp)
                    )
                }
                IconButton(
                    onClick = {
                        onProductCountChanged(0)
                        onRemove()
                    }
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun CartItemPreview() {
    MyKlontongAppsTheme {
        val dataPreview = FakeKlontongDataSource.dummyFoods[0]
        if (dataPreview.listImageUrl != null && dataPreview.listImageUrl.isNotEmpty() && dataPreview.listImageUrl[0].imageUrl != null) {
            CartItem(
                imageUrl = dataPreview.listImageUrl[0].imageUrl,
                nameFood = dataPreview.nameFood,
                priceFood = dataPreview.priceFood,
                count = dataPreview.count,
                onProductCountChanged = {},
                navigateToDetail = {},
                onRemove = {},
            )
        }
    }
}