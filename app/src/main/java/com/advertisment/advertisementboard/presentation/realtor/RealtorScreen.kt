package com.advertisment.advertisementboard.presentation.realtor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.advertisment.advertisementboard.R
import com.advertisment.advertisementboard.presentation.realtor.components.ContactButton
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceNormal
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalPermissionsApi
@Composable
fun RealtorScreen() {

    val scrollState = rememberScrollState()

    val phoneNumbers = listOf(
        "+7 (963) 408-65-95",
        "+7 (969) 717-71-05"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

        Spacer(modifier = Modifier.height(SpaceNormal))

        Text(
            text = stringResource(id = R.string.contact_with_us),
            style = MaterialTheme.typography.h6,
            color = Color.Yellow
        )

        Spacer(modifier = Modifier.height(SpaceNormal))

        Text(
            text = stringResource(id = R.string.realtor_description),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(SpaceNormal))

        phoneNumbers.forEach { phoneNumber ->
            ContactButton(phoneNumber = phoneNumber)
            Spacer(modifier = Modifier.height(SpaceNormal))
        }
    }
}