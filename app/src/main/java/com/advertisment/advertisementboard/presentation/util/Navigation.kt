package com.advertisment.advertisementboard.presentation.util

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.advertisment.advertisementboard.presentation.about_us.AboutUsScreen
import com.advertisment.advertisementboard.presentation.add_property.AddPropertyScreen
import com.advertisment.advertisementboard.presentation.add_property.FirstScreen
import com.advertisment.advertisementboard.presentation.add_property.SecondScreen
import com.advertisment.advertisementboard.presentation.add_property.ThirdScreen
import com.advertisment.advertisementboard.presentation.auth.AuthScreen
import com.advertisment.advertisementboard.presentation.cities.CitiesScreen
import com.advertisment.advertisementboard.presentation.client.ClientScreen
import com.advertisment.advertismentboard.presentation.property.MainScreen
import com.advertisment.advertisementboard.presentation.realtor.RealtorScreen
import com.advertisment.advertisementboard.presentation.rules.RulesScreen
import com.advertisment.advertisementboard.presentation.splash.SplashScreen
import com.advertisment.advertisementboard.presentation.user_agreements.UserAgreementsScreen
import com.advertisment.advertisementboard.presentation.user_type.UserTypeScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalPermissionsApi
@Composable
fun Navigation(
    navController: NavHostController
) {

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.UserTypeScreen.route) {
            EnterAnimation {
                UserTypeScreen(navController = navController)
            }
        }
        composable(Screen.RealtorScreen.route) {
            EnterAnimation {
                RealtorScreen()
            }
        }
        composable(
            route = Screen.AuthScreen.route + "/{user_type}",
            arguments = listOf(
                navArgument("user_type") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            EnterAnimation {
                AuthScreen(
                    navController = navController,
                    userType = entry.arguments?.getString("user_type")
                )
            }
        }

        composable(
            route = Screen.FirstScreen.route + "/{user_type}/{id}",
            arguments = listOf(
                navArgument("user_type") {
                    type = NavType.StringType
                },
                navArgument("id") {
                    type = NavType.StringType
                },
            )
        ) { entry ->
            EnterAnimation {
                FirstScreen(
                    navController = navController,
                    id = entry.arguments?.getString("id")
                )
            }
        }

        composable(
            route = Screen.SecondScreen.route + "/{user_type}/{id}",
            arguments = listOf(
                navArgument("user_type") {
                    type = NavType.StringType
                },
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            EnterAnimation {
                SecondScreen(
                    navController = navController,
                    type = entry.arguments?.getString("user_type")!!,
                    id = entry.arguments?.getString("id")!!,
                )
            }
        }

        composable(
            route = Screen.ThirdScreen.route + "/{user_type}/{id}"+"/{type1}",
            arguments = listOf(
                navArgument("user_type") {
                    type = NavType.StringType
                },
                navArgument("id") {
                    type = NavType.StringType
                },
                navArgument("type1") {
                    type = NavType.StringType
                },
            )
        ) { entry ->
            EnterAnimation {
                ThirdScreen(
                    navController = navController,
                    type = entry.arguments?.getString("user_type")!!,
                    type1 = entry.arguments?.getString("type1")!!,
                    id = entry.arguments?.getString("id")!!,
                )
            }
        }

        composable(
            route = Screen.ClientScreen.route + "/{user_type}",
            arguments = listOf(
                navArgument("user_type") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            EnterAnimation {
                ClientScreen(
                    navController = navController,
                    userType = entry.arguments?.getString("user_type")
                )
            }
        }
        composable(
            route = Screen.CitiesScreen.route + "/{user_type}"+ "/{id}",
            arguments = listOf(
                navArgument("user_type") {
                    type = NavType.StringType
                },
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            EnterAnimation {
                CitiesScreen(
                    navController = navController,
                    userType = entry.arguments?.getString("user_type"),
                    id = entry.arguments?.getString("id")
                )
            }
        }
        composable(
            route = Screen.PropertyScreen.route + "/{user_type}/{city}/{id}",
            arguments = listOf(
                navArgument("user_type") {
                    type = NavType.StringType
                },
                navArgument("city") {
                    type = NavType.StringType
                },
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            EnterAnimation {
                MainScreen(
                    navController = navController,
                    userType = entry.arguments?.getString("user_type"),
                    city = entry.arguments?.getString("city"),
                    id = entry.arguments?.getString("id"),
                )
            }
        }
        composable(
            route = Screen.AddPropertyScreen.route + "/{user_type}/{id}"+ "/{type}"+ "/{type1}"+ "/{type2}",
            arguments = listOf(
                navArgument("user_type") {
                    type = NavType.StringType
                },
                navArgument("id") {
                    type = NavType.StringType
                },
                navArgument("type") {
                    type = NavType.StringType
                },
                navArgument("type1") {
                    type = NavType.StringType
                },
                navArgument("type2") {
                    type = NavType.StringType
                },
            )
        ) { entry ->
            EnterAnimation {
                AddPropertyScreen(
                    navController = navController,
                    userType = entry.arguments?.getString("user_type"),
                    type1 =  entry.arguments?.getString("type")!!,
                    type2 =  entry.arguments?.getString("type1")!!,
                    type3 =  entry.arguments?.getString("type2")!!,
                    id =  entry.arguments?.getString("id")!!,
                )
            }
        }
        composable(Screen.RulesScreen.route) {
            EnterAnimation {
                RulesScreen()
            }
        }
        composable(Screen.UserAgreementsScreen.route) {
            EnterAnimation {
                UserAgreementsScreen()
            }
        }
        composable(Screen.AboutUsScreen.route) {
            EnterAnimation {
                AboutUsScreen()
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun EnterAnimation(content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = true,
        enter = slideInVertically(
            initialOffsetY = { -40 }
        ) + expandVertically(
            expandFrom = Alignment.Top
        ) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut(),
        content = content,
        initiallyVisible = false
    )
}