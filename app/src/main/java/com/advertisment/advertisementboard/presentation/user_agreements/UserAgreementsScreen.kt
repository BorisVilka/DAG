package com.advertisment.advertisementboard.presentation.user_agreements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.advertisment.advertisementboard.R
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceNormal

@Composable
fun UserAgreementsScreen() {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(SpaceNormal)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = stringResource(R.string.user_agreements_content),
            style = MaterialTheme.typography.body1
        )
    }
}