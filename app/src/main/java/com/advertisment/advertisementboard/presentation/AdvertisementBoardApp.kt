package com.advertisment.advertisementboard.presentation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.RectangleShape
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.advertisment.advertisementboard.presentation.components.Drawer
import com.advertisment.advertisementboard.presentation.components.TopBar
import com.advertisment.advertisementboard.presentation.ui.theme.AdvertisementBoardTheme
import com.advertisment.advertisementboard.presentation.util.Screen

@Composable
fun AdvertisementBoardApp(
    navController: NavController,
    content: @Composable () -> Unit
) {

    val scope = rememberCoroutineScope()
    val scaffoldState =
        rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry.value?.destination?.route

    AdvertisementBoardTheme {
        Surface(color = MaterialTheme.colors.background) {
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    if (currentDestination != Screen.SplashScreen.route) {
                        TopBar(
                            navController = navController,
                            scope = scope,
                            scaffoldState = scaffoldState
                        )
                    }
                },
                drawerShape = RectangleShape,
                drawerContent = {
                    Drawer(
                        navController = navController,
                        scope = scope,
                        scaffoldState = scaffoldState
                    )
                },
                drawerGesturesEnabled = currentDestination != Screen.SplashScreen.route,
                content = { content() }
            )
        }
    }
}