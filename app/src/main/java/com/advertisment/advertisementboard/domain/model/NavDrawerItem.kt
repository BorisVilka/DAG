package com.advertisment.advertisementboard.domain.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.advertisment.advertisementboard.R
import com.advertisment.advertisementboard.presentation.util.Screen

sealed class NavDrawerItem(val route: String, @StringRes val title: Int, val icon: ImageVector) {

    object PrivacyPolicy : NavDrawerItem(
        Screen.RulesScreen.route, R.string.rules, Icons.Default.List
    )

    object UserAgreements : NavDrawerItem(
        Screen.UserAgreementsScreen.route, R.string.user_agreements, Icons.Default.Person
    )

    object AboutUs : NavDrawerItem(
        Screen.AboutUsScreen.route, R.string.about_us, Icons.Default.Info
    )
}