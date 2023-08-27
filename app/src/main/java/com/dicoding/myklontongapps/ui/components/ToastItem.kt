package com.dicoding.myklontongapps.ui.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun showToast(message: String) {
    Toast.makeText(LocalContext.current, message, Toast.LENGTH_LONG)
        .show()
}