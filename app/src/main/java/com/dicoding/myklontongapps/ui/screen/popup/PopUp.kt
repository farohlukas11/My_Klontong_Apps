package com.dicoding.myklontongapps.ui.screen.popup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.dicoding.myklontongapps.R
import com.dicoding.myklontongapps.ui.theme.MyKlontongAppsTheme

@Composable
fun PopUpScreen(
    popUpControl: Boolean,
    onDismiss: () -> Unit
) {
    if (popUpControl) {
        Popup(
            alignment = Alignment.Center,
            onDismissRequest = {
                onDismiss()
            },
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .background(color = Color.Gray, shape = RoundedCornerShape(10.dp)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_sukses),
                    contentDescription = "logo sukses",
                    alignment = Alignment.Center,
                    modifier = Modifier.size(200.dp)
                )
                Text(
                    text = "Berhasil Melakukan CheckOut",
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(5.dp)
                )
                Text(
                    text = "Pesanan akan segera diantar, Tunggu yaa!",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun PopUpScreenPreview() {
    MyKlontongAppsTheme {
        PopUpScreen(popUpControl = true) {

        }
    }
}