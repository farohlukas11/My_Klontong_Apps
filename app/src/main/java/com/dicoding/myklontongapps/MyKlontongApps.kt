package com.dicoding.myklontongapps

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dicoding.myklontongapps.ui.navigation.NavigationItem
import com.dicoding.myklontongapps.ui.navigation.Screen
import com.dicoding.myklontongapps.ui.screen.cart.CartScreen
import com.dicoding.myklontongapps.ui.screen.detail.DetailScreen
import com.dicoding.myklontongapps.ui.screen.favourite.FavouriteScreen
import com.dicoding.myklontongapps.ui.screen.home.HomeScreen
import com.dicoding.myklontongapps.ui.screen.profile.ProfileScreen

@Composable
fun MyKlontongApps(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailProduct.route) {
                BottomBar(navController = navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { foodId ->
                        navController.navigate(Screen.DetailProduct.createRoute(productId = foodId))
                    },
                    navigateToProfile = {
                        navController.popBackStack()
                        navController.navigate(Screen.Profile.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
            composable(Screen.Cart.route) {
                CartScreen {
                    navController.navigate(Screen.DetailProduct.createRoute(productId = it))
                }
            }
            composable(Screen.Favourite.route) {
                FavouriteScreen { favouriteId ->
                    navController.navigate(Screen.DetailProduct.createRoute(productId = favouriteId))
                }
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(
                route = Screen.DetailProduct.route,
                arguments = listOf(navArgument("productId") {
                    type = NavType.LongType
                })
            ) {
                val id = it.arguments?.getLong("productId") ?: -1L

                DetailScreen(
                    productId = id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToCart = {
                        navController.popBackStack()
                        navController.navigate(Screen.Cart.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    navigateToDetail = { productId ->
                        navController.navigate(Screen.DetailProduct.createRoute(productId = productId))
                    }
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navigationItems = listOf(
        NavigationItem(
            title = stringResource(R.string.Home),
            icon = Icons.Default.Home,
            screen = Screen.Home
        ), NavigationItem(
            title = stringResource(R.string.cart),
            icon = Icons.Default.ShoppingCart,
            screen = Screen.Cart
        ),
        NavigationItem(
            title = stringResource(R.string.Favourite),
            icon = Icons.Default.Favorite,
            screen = Screen.Favourite
        ),
        NavigationItem(
            title = "Profile",
            icon = Icons.Default.AccountCircle,
            screen = Screen.Profile
        )
    )

    BottomNavigation(modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        navigationItems.map { item ->
            BottomNavigationItem(
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.title)
                },
                label = { Text(text = item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}