package com.dicoding.myklontongapps.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.myklontongapps.R
import com.dicoding.myklontongapps.ui.theme.MyKlontongAppsTheme

@Composable
fun RatingCard(nameFood: String?, ratingFood: Double?) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(color = Color.Gray, width = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Rating Produk",
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_star_rate),
                    contentDescription = "Rating $nameFood",
                    modifier = Modifier.size(25.dp),
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = (ratingFood ?: 0.0).toString(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = 22.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Light
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_2)
@Composable
fun RatingCardPreview() {
    MyKlontongAppsTheme {
        RatingCard(nameFood = "Sate Ayam", ratingFood = 4.8)
    }
}