package com.advertisment.advertisementboard.presentation.util

sealed class Screen(val route: String) {
    object SplashScreen: Screen("splash_screen")
    object UserTypeScreen : Screen("user_type_screen")
    object AddPropertyScreen : Screen("add_property_screen")
    object CitiesScreen : Screen("cities_screen")
    object AuthScreen : Screen("auth_screen")
    object PropertyScreen : Screen("property_screen")
    object RulesScreen: Screen("rules_screen")
    object UserAgreementsScreen: Screen("user_agreements_screen")
    object AboutUsScreen: Screen("about_us_screen")
    object RealtorScreen: Screen("realtor_screen")
    object ClientScreen: Screen("client_screen")
    object FirstScreen: Screen("first_screen")
    object SecondScreen: Screen("second_screen")
    object ThirdScreen: Screen("third_screen")

    fun withArgs(vararg args: String) =
        buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
}