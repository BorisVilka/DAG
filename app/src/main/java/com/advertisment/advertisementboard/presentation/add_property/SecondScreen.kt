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
fun SecondScreen(
    navController: NavController,
    type: String,
    id: String?
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
            text = if(type=="Сдать") "Посуточно" else "Квартиру",
            onClick = {
                navController.navigate(
                    Screen.ThirdScreen.withArgs(type,id!!,if(type=="Сдать") "Посуточно" else "Квартиру")
                )
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(SpaceLarge))


        UserTypeItem(
            text = if(type=="Сдать") "На длительный срок" else "Дом",
            onClick = {
                navController.navigate(
                    Screen.ThirdScreen.withArgs(type,id!!,if(type=="Сдать") "На длительный срок" else "Дом")
                )
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        
        Spacer(modifier = Modifier.height(SpaceLarge))

    }
}