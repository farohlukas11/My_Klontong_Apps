package com.dicoding.myklontongapps.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dicoding.myklontongapps.model.FakeKlontongDataSource
import com.dicoding.myklontongapps.ui.theme.MyKlontongAppsTheme
import com.dicoding.myklontongapps.R
import com.dicoding.myklontongapps.utils.Utils

@Composable
fun FoodOptionItem(
    imageUrl: String?,
    nameFood: String?,
    priceFood: Int?,
    modifier: Modifier = Modifier
) {
    Card(
        backgroundColor = Color.White,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .width(170.dp),
        elevation = 3.dp,
    ) {
        Column(modifier = modifier.padding(bottom = 5.dp)) {
            val modifierImage =
                Modifier
                    .fillMaxWidth()
                    .height(170.dp)

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
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = nameFood ?: "",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontSize = 16.sp,
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
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .width(160.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FoodItemPreview() {
    MyKlontongAppsTheme {
        val dataPreview = FakeKlontongDataSource.dummyFoods[0]
        if (dataPreview.listImageUrl != null && dataPreview.listImageUrl.isNotEmpty() && dataPreview.listImageUrl[0].imageUrl != null && dataPreview.nameFood != null && dataPreview.priceFood != null) {
            FoodOptionItem(
                imageUrl = dataPreview.listImageUrl[0].imageUrl,
                nameFood = dataPreview.nameFood,
                priceFood = dataPreview.priceFood
            )
        }
    }
}