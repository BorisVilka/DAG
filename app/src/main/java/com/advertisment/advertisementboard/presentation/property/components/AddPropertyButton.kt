package com.advertisment.advertisementboard.presentation.property.components

import androidx.compose.foundation.Image
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter

@Composable
fun AddPropertyButton(
    onClick: () -> Unit,
    text: String
) {
    Button(
        onClick = {
            onClick()
        }
    ) {
        Image(
            imageVector = Icons.Default.Add,
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.White)
        )

        Text(
            text = text,
            style = MaterialTheme.typography.button
        )
    }
}