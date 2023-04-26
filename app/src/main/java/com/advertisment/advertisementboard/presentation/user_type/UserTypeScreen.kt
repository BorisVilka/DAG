package com.advertisment.advertisementboard.presentation.user_type

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.advertisment.advertisementboard.domain.model.UserType
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceLarge
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceNormal
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceSmall
import com.advertisment.advertisementboard.presentation.user_type.components.UserTypeItem
import com.advertisment.advertisementboard.presentation.util.Screen
import com.advertisment.advertisementboard.R
import com.advertisment.advertisementboard.presentation.components.CustomEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun UserTypeScreen(
    navController: NavController,
 ) {

    val scrollState = rememberScrollState()

    val userTypes = listOf(
        UserType.Client,
        UserType.Buyer,
        UserType.ClientAdmin,
        UserType.Admin,
        UserType.Realtor,
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

        Spacer(modifier = Modifier.height(SpaceLarge))

        userTypes.forEach { type ->

            UserTypeItem(
                text = stringResource(id = type.text),
                onClick = {
                    navController.navigate(
                        when (type.type) {
                            UserType.Admin.type -> Screen.AuthScreen.withArgs(type.type)
                            UserType.ClientAdmin.type -> Screen.ClientScreen.withArgs(type.type)
                            UserType.Realtor.type -> Screen.RealtorScreen.route
                            else -> Screen.CitiesScreen.withArgs(type.type,"client")
                        }
                    )
                },
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            Spacer(modifier = Modifier.height(SpaceLarge))
        }


        Text(
            text = stringResource(id = R.string.app_description),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = SpaceSmall)
        )

        Spacer(modifier = Modifier.height(SpaceLarge))
    }
}