package com.advertisment.advertisementboard.presentation.client

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.advertisment.advertisementboard.domain.model.UserType
import com.advertisment.advertisementboard.presentation.components.CustomEditText
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceNormal
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceSmall
import com.advertisment.advertisementboard.presentation.util.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.advertisment.advertisementboard.R
import com.advertisment.advertisementboard.presentation.client.components.ContactButton
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ClientScreen(
    navController: NavController,
    userType: String?,
    viewModel: ClientViewModel = hiltViewModel()
) {
    val phoneNumbers = listOf(
        "+7 (963) 408-65-95",
    )
    val coroutineScope = rememberCoroutineScope()


    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        Spacer(modifier = Modifier.height(SpaceNormal))

        Text(
            text = stringResource(id = R.string.contact_with_us),
            style = MaterialTheme.typography.h6,
            color = Color.Yellow
        )

        Spacer(modifier = Modifier.height(SpaceNormal))

        Text(
            text = stringResource(id = R.string.client),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(SpaceSmall)
        )

        Spacer(modifier = Modifier.height(SpaceNormal))

        phoneNumbers.forEach { phoneNumber ->
            ContactButton(phoneNumber = phoneNumber)
            Spacer(modifier = Modifier.height(SpaceNormal))
        }
        Spacer(modifier = Modifier.height(SpaceNormal))
        CustomEditText(
            value = viewModel.clientAdminPassword.value,
            onValueChange = {
                viewModel.setClientAdminPassword(it)
                viewModel.setClientAdminError(false)
            },
            labelText = "Клиентский доступ",
            hintText = stringResource(id = R.string.password),
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(SpaceSmall))

        if (viewModel.clientAdminError.value) {
            Text(
                text = stringResource(id = R.string.wrong_password),
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.error
            )
        }

        Spacer(modifier = Modifier.height(SpaceNormal))

        Button(
            onClick = {
                coroutineScope.launch(Dispatchers.IO) {
                    val passwordIsCorrect = viewModel.validateClientAdminPassword()
                    withContext(Dispatchers.Main) {
                        if (!passwordIsCorrect.isEmpty) {
                            Log.d("TAG",passwordIsCorrect.documents[0].id )
                            navController.navigate(Screen.CitiesScreen.withArgs(UserType.ClientAdmin.type, passwordIsCorrect.documents[0].id))
                        } else {
                            viewModel.setClientAdminError(true)
                        }
                    }
                }

            }
        ) {
            Text(text = stringResource(id = R.string.sign_up))
        }
    }
}