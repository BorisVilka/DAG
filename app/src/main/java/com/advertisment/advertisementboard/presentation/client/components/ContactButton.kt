package com.advertisment.advertisementboard.presentation.client.components

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceNormal
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceSmall
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@ExperimentalPermissionsApi
@Composable
fun ContactButton(
    phoneNumber: String
) {
    val context = LocalContext.current

    val intent = Intent(
        Intent.ACTION_DIAL,
        Uri.parse("tel:$phoneNumber")
    )

    val permissionsState = rememberPermissionState(permission = Manifest.permission.CALL_PHONE)

    Button(
        onClick = {
            ContextCompat.startActivity(context, intent, null)
        },
        shape = MaterialTheme.shapes.medium,
        contentPadding = PaddingValues(SpaceSmall),
        modifier = Modifier.fillMaxWidth(0.8f)
    ) {
        Text(
            text = phoneNumber,
            style = MaterialTheme.typography.h5,

        )
    }
}