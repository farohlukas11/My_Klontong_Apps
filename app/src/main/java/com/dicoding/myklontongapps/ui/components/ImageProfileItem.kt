package com.dicoding.myklontongapps.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.dicoding.myklontongapps.R
import com.dicoding.myklontongapps.model.FakeKlontongDataSource
import com.dicoding.myklontongapps.ui.theme.MyKlontongAppsTheme

@Composable
fun ImageProfileItem(
    image: String?,
    toProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (image != null) {
        AsyncImage(
            model = image,
            error = painterResource(id = R.drawable.image_error),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = modifier
                .clickable {
                    toProfile()
                }
        )
    } else {
        Image(
            painter = painterResource(id = R.drawable.image_error),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ImageProfileItemPreview() {
    MyKlontongAppsTheme {
        val profileUser = FakeKlontongDataSource.profileUser.imageProfile
        ImageProfileItem(image = profileUser, toProfile = {})
    }
}