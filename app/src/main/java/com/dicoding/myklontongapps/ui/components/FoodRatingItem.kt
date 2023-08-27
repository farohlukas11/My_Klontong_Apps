package com.dicoding.myklontongapps.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.dicoding.myklontongapps.R
import com.dicoding.myklontongapps.model.FakeKlontongDataSource
import com.dicoding.myklontongapps.ui.theme.MyKlontongAppsTheme
import com.dicoding.myklontongapps.utils.Utils

@Composable
fun FoodRatingItem(
    imageUrl: String?,
    nameFood: String?,
    priceFood: Int?,
    ratingFood: Double?,
    modifier: Modifier = Modifier
) {
    Card(
        backgroundColor = Color.White,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier.width(170.dp),
        elevation = 3.dp
    ) {
        Column(modifier = modifier.padding(bottom = 5.dp)) {
            val modifierImage = Modifier
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
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        text = nameFood ?: "",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Light
                        ),
                        modifier = Modifier.width(110.dp)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = Utils.rupiahFormatter(priceFood),
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.width(110.dp)

                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.width(50.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_star_rate),
                        contentDescription = "Rating $nameFood",
                        modifier = Modifier.size(20.dp),
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = (ratingFood ?: 0.0).toString(),
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FoodRatingItemPreview() {
    MyKlontongAppsTheme {
        val dataPreview = FakeKlontongDataSource.dummyFoods[0]

        if (dataPreview.listImageUrl != null && dataPreview.listImageUrl.isNotEmpty() && dataPreview.listImageUrl[0].imageUrl != null && dataPreview.nameFood != null && dataPreview.priceFood != null && dataPreview.ratingFood != null) {
            FoodRatingItem(
                imageUrl = dataPreview.listImageUrl[0].imageUrl,
                nameFood = dataPreview.nameFood,
                priceFood = dataPreview.priceFood,
                ratingFood = dataPreview.ratingFood
            )
        }
    }
}

