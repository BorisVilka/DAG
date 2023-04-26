package com.advertisment.advertisementboard.presentation.cities.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.advertisment.advertismentboard.presentation.ui.theme.Gray
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceExtraLarge
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceNormal

@Composable
fun CityItem(
    name: Int,
    crest: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        elevation = 5.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            Image(
                painter = painterResource(id = crest),
                contentDescription = stringResource(id = name),
                modifier = Modifier.size(SpaceExtraLarge)
            )
            Divider(
                color = Gray,
                modifier = Modifier
                    .padding(vertical = SpaceNormal)
                    .height(1.dp)
            )
            Button(
                onClick = { onClick() }
            ) {
                Text(
                    text = stringResource(id = name)
                )
            }
        }
    }
}