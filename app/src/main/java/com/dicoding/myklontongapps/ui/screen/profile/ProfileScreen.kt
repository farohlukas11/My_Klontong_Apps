package com.dicoding.myklontongapps.ui.screen.profile

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.dicoding.myklontongapps.di.Injection
import com.dicoding.myklontongapps.model.FakeKlontongDataSource
import com.dicoding.myklontongapps.ui.ViewModelFactory
import com.dicoding.myklontongapps.ui.common.UiState
import com.dicoding.myklontongapps.ui.components.showToast
import com.dicoding.myklontongapps.ui.theme.MyKlontongAppsTheme
import com.dicoding.myklontongapps.R
import com.dicoding.myklontongapps.ui.components.ImageProfileItem

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
) {
    viewModel.uiStateUser.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                CircularProgressIndicator()
                viewModel.getUser()
            }
            is UiState.Success -> {
                val dataUser = uiState.data

                ProfileContent(
                    imageUrl = dataUser.imageUrl,
                    imageProfile = dataUser.imageProfile,
                    name = dataUser.name,
                    username = dataUser.username,
                    description = dataUser.description,
                    email = dataUser.email,
                    modifier = modifier
                )
            }
            is UiState.Error -> {
                showToast(message = uiState.errorMessage)
            }
        }
    }
}


@Composable
fun ProfileContent(
    imageUrl: String?,
    imageProfile: String?,
    name: String?,
    username: String?,
    description: String?,
    email: String?,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val intent =
        remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/farohmuhl/")) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        val modifierImageUrl = Modifier
            .fillMaxWidth()
            .height(220.dp)

        Box(modifier = Modifier.fillMaxWidth()) {
            if (imageUrl != null) {
                AsyncImage(
                    model = imageUrl,
                    error = painterResource(id = R.drawable.image_error),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifierImageUrl
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.image_error),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifierImageUrl
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                ImageProfileItem(
                    image = imageProfile,
                    toProfile = { },
                    modifier = Modifier
                        .padding(top = 145.dp)
                        .size(150.dp)
                        .shadow(
                            elevation = 3.dp, shape = CircleShape, clip = true
                        )
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = name ?: "",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontSize = 24.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.width(120.dp), horizontalAlignment = Alignment.Start) {
                    Text(
                        text = "E-Mail",
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium,
                        ),
                    )
                    Text(
                        text = "Username",
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium,
                        ),
                    )
                }
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                    Text(
                        text = ": ${email ?: ""}",
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Medium,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Text(
                        text = ": @${username ?: ""}",
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Medium,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(10.dp),
                    )
                    .padding(10.dp)
            ) {
                Text(
                    text = description ?: "",
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Justify
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Selengkapnya....", style = TextStyle(
                    fontSize = 22.sp,
                    color = Color.Green,
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier.clickable {
                    context.startActivity(intent)
                }
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun ProfileContentPreview() {
    MyKlontongAppsTheme {
        val dataUser = FakeKlontongDataSource.profileUser
        ProfileContent(
            imageUrl = dataUser.imageUrl,
            imageProfile = dataUser.imageProfile,
            name = dataUser.name,
            username = dataUser.username,
            description = dataUser.description,
            email = dataUser.email
        )
    }
}