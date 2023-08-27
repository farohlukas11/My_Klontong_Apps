package com.dicoding.myklontongapps.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dicoding.myklontongapps.R
import com.dicoding.myklontongapps.model.FakeKlontongDataSource
import com.dicoding.myklontongapps.model.ImageFood
import com.dicoding.myklontongapps.ui.theme.MyKlontongAppsTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HeaderDetailImageSlider(
    listImageUrl: List<ImageFood>?,
    onBackClick: () -> Unit
) {
    if (listImageUrl != null && listImageUrl.isNotEmpty()) {
        val pagerState = rememberPagerState(initialPage = 0)
        val sliderSize = listImageUrl.size

        Box {
            Box(contentAlignment = Alignment.BottomCenter) {
                HorizontalPager(
                    count = sliderSize,
                    state = pagerState,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                ) { page ->
                    if (listImageUrl[page].imageUrl != null) {
                        AsyncImage(
                            model = listImageUrl[page].imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.image_error),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
                DotsIndicator(totalDots = sliderSize, selectedIndex = pagerState.currentPage)
            }
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        onBackClick()
                    }
            )
        }
    }
}

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int
) {
    LazyRow(
        modifier = Modifier
            .padding(bottom = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {

        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color = Color.DarkGray)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color = Color.LightGray)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderDetailImageSliderPreview() {
    MyKlontongAppsTheme {
        val dataPreview = FakeKlontongDataSource.dummyFoods[0].listImageUrl
        HeaderDetailImageSlider(listImageUrl = dataPreview) {}
    }
}