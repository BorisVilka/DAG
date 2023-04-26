package com.advertisment.advertisementboard.presentation.cities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.advertisment.advertisementboard.domain.model.City
import com.advertisment.advertisementboard.presentation.cities.components.CityItem
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceExtraSmall
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceLarge
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceNormal
import com.advertisment.advertisementboard.presentation.util.Screen
import com.advertisment.advertisementboard.R

@Composable
fun CitiesScreen(
    navController: NavController,
    userType: String?,
    id: String?
) {

    val context = LocalContext.current

    val scrollState = rememberScrollState()

    val cities = listOf(
        City.Makhachkala,
        City.Derbent,
        City.Kaspiysk,
        City.Izberbash,
        City.DagOgni,
        City.Dubki,
        City.Buynask,
        City.Hasavrut,
        City.Kizlar,
        City.Kizilurt,
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(SpaceLarge))

            cities.forEach { city ->
                CityItem(
                    name = city.name,
                    crest = city.crest,
                    onClick = {
                        navController.navigate(
                            Screen.PropertyScreen.withArgs(userType!!, context.getString(city.name),id!!)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .background(color = MaterialTheme.colors.surface)
                        .padding(vertical = SpaceNormal)
                )
                Spacer(modifier = Modifier.height(SpaceNormal))
            }
        }
    }
}