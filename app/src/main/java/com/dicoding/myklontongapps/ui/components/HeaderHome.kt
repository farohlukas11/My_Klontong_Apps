package com.dicoding.myklontongapps.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.myklontongapps.ui.theme.MyKlontongAppsTheme

@Composable
fun HeaderHome(
    image: String?,
    username: String?,
    toProfile: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.width(200.dp)
        ) {
            Text(
                text = "Selamat Datang di My Klontong Apps",
                maxLines = 2,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Light
                )
            )
            Text(
                text = "Hallo, @${username ?: ""}",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
        ImageProfileItem(
            image = image,
            toProfile = toProfile,
            modifier = Modifier
                .size(100.dp)
                .shadow(
                    elevation = 3.dp, shape = CircleShape, clip = true
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderHomePreview() {
    MyKlontongAppsTheme {
        HeaderHome("", "Faroh11") {}
    }
}