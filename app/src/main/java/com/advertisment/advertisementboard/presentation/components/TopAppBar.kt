package com.advertisment.advertisementboard.presentation.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.advertisment.advertismentboard.presentation.ui.theme.Green
import com.advertisment.advertisementboard.presentation.util.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.advertisment.advertisementboard.R

@Composable
fun TopBar(
    navController: NavController,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {

    val currentBackStackEntry = navController.currentBackStackEntryAsState()

    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = Green,
        actions = {
            val currentDestination = currentBackStackEntry.value?.destination?.route
            if (currentDestination != Screen.UserTypeScreen.route) {
                IconButton(
                    onClick = {
                        navController.navigateUp()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.turn_back)
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(id = R.string.menu)
                )
            }
        }
    )
}