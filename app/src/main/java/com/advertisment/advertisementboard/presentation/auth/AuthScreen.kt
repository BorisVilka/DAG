package com.advertisment.advertisementboard.presentation.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.advertisment.advertisementboard.domain.model.UserType
import com.advertisment.advertisementboard.presentation.components.CustomEditText
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceLarge
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceNormal
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceSmall
import com.advertisment.advertisementboard.presentation.util.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.advertisment.advertisementboard.R

@Composable
fun AuthScreen(
    navController: NavController,
    userType: String?,
    viewModel: AuthViewModel = hiltViewModel()
) {

    val coroutineScope = rememberCoroutineScope()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        CustomEditText(
            value = viewModel.adminPassword.value,
            onValueChange = {
                viewModel.setAdminPassword(it)
                viewModel.setAdminError(false)
            },
            labelText = stringResource(id = R.string.admin),
            hintText = stringResource(id = R.string.password),
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(SpaceSmall))

        if (viewModel.adminError.value) {
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
                    val passwordIsCorrect = viewModel.validateAdminPassword()
                    withContext(Dispatchers.Main) {
                        if (passwordIsCorrect) {
                            navController.navigate(Screen.CitiesScreen.withArgs(UserType.Admin.type,"admin"))
                        } else {
                            viewModel.setAdminError(true)
                        }
                    }
                }

            }
        ) {
            Text(text = stringResource(id = R.string.sign_up))
        }

        /*Spacer(modifier = Modifier.height(SpaceLarge))

        CustomEditText(
            value = viewModel.clientAdminPassword.value,
            onValueChange = {
                viewModel.setClientAdminPassword(it)
                viewModel.setClientAdminError(false)
            },
            labelText = stringResource(id = R.string.client_admin),
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
                        if (passwordIsCorrect) {
                            navController.navigate(Screen.CitiesScreen.withArgs(UserType.ClientAdmin.type))
                        } else {
                            viewModel.setClientAdminError(true)
                        }
                    }
                }

            }
        ) {
            Text(text = stringResource(id = R.string.sign_up))
        }*/
    }
}