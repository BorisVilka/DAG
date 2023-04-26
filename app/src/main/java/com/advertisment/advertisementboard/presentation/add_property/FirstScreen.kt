package com.advertisment.advertisementboard.presentation.add_property

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.advertisment.advertisementboard.R
import com.advertisment.advertisementboard.domain.model.UserType
import com.advertisment.advertisementboard.presentation.user_type.components.UserTypeItem
import com.advertisment.advertisementboard.presentation.util.Screen
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceLarge
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceSmall

@ExperimentalCoilApi
@Composable
fun FirstScreen(
    navController: NavController,
    id: String?,
) {
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

        Spacer(modifier = Modifier.height(SpaceLarge))

        UserTypeItem(
            text = "Сдать",
            onClick = {
                navController.navigate(
                    Screen.SecondScreen.withArgs("Сдать",id!!)
                )
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(SpaceLarge))


        UserTypeItem(
            text = "Продать",
            onClick = {
                navController.navigate(
                    Screen.SecondScreen.withArgs("Продать",id!!)
                )
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(SpaceLarge))
    }
}