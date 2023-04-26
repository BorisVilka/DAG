package com.advertisment.advertisementboard.presentation.add_property.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceLarge
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceNormal
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceSmall
import com.advertisment.advertisementboard.R

@Composable
fun AddPhotoSection(
    onClick: () -> Unit
) {

    Button(
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
        shape = MaterialTheme.shapes.large,
        onClick = { onClick() }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(SpaceNormal)
        ) {
            Image(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(id = R.string.add_photo),
                modifier = Modifier.size(SpaceLarge)
            )

            Spacer(modifier = Modifier.height(SpaceSmall))

            Text(
                text = stringResource(id = R.string.add_photo),
                color = Color.Black
            )
        }
    }
}