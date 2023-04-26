package com.advertisment.advertisementboard.presentation.user_type.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceLarge
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceNormal

@Composable
fun UserTypeItem(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { onClick() },
        modifier = modifier,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button,
            fontSize = 18.sp,
            modifier = Modifier.padding(vertical = SpaceNormal),
            textAlign = TextAlign.Center
        )
    }
}