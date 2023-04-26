package com.advertisment.advertisementboard.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.advertisment.advertisementboard.domain.model.NavDrawerItem
import com.advertisment.advertismentboard.presentation.ui.theme.Gray
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Drawer(
    navController: NavController,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {

    val items = listOf(
        NavDrawerItem.PrivacyPolicy,
        NavDrawerItem.UserAgreements,
        NavDrawerItem.AboutUs,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Column {
        items.forEach { item ->
            DrawerItem(
                item = item,
                selected = currentRoute == item.route,
                onItemClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                }
            )
            Divider(modifier = Modifier.height(1.dp).background(Gray))
        }
    }
}