package com.dicoding.myklontongapps.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Favourite: Screen("favourite")
    object Cart: Screen("cart")
    object Profile: Screen("profile")
    object DetailProduct: Screen("home/{productId}") {
        fun createRoute(productId: Long) = "home/$productId"
    }
}
