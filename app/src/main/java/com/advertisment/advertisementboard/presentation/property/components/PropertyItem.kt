package com.advertisment.advertisementboard.presentation.property.components

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.advertisment.advertisementboard.R
import com.advertisment.advertisementboard.domain.model.Property
import com.advertisment.advertisementboard.domain.model.UserType
import com.advertisment.advertisementboard.presentation.property.PropertyViewModel
import com.advertisment.advertismentboard.presentation.ui.theme.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@ExperimentalPermissionsApi
@Composable
fun PropertyItem(
    property: Property,
    id: String,
    modifier: Modifier = Modifier,
    userType: String,
    starIcon: MutableState<Boolean>,
    onDelete: () -> Unit,
    onPin: () -> Unit,
    viewModel: PropertyViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val intent = Intent(
        Intent.ACTION_DIAL,
        Uri.parse("tel:${property.phoneNumber}")
    )

    val permissionsState = rememberPermissionState(permission = Manifest.permission.CALL_PHONE)

    val bitmaps = remember { mutableStateListOf<Bitmap>() }

    LaunchedEffect(key1 = true) {
        property.photoUris.forEach { uri ->
            try {
                bitmaps.add(viewModel.downloadImageFromStorage(uri))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    Box {
        Row(
            modifier = Modifier
                .padding(start = SpaceNormal)
                .zIndex(10f)
        ) {
            Text(
                text = property.price.toString() + "₽",
                color = White,
                modifier = Modifier
                    .background(color = Green, shape = MaterialTheme.shapes.large)
                    .padding(vertical = SpaceSmall, horizontal = SpaceNormal)
            )

            if (userType == UserType.Admin.type) {
                Spacer(modifier = Modifier.width(SpaceNormal))

                IconButton(
                    onClick = { onPin() },
                    modifier = Modifier
                        .size(38.dp)
                        .background(color = Green, shape = MaterialTheme.shapes.large)
                ) {
                    Icon(
                        painter = painterResource(id = if (starIcon.value) R.drawable.ic_star else R.drawable.ic_star_outlined),
                        contentDescription = stringResource(R.string.pin),
                        tint = White
                    )
                }
            }
        }

        Card(
            elevation = 5.dp,
            modifier = Modifier.padding(top = SpaceLarge)
        ) {
            Column(
                modifier = modifier
                    .background(
                        color = MaterialTheme.colors.surface,
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(SpaceSmall)
            ) {
                Text(
                    text = property.title,
                    style = MaterialTheme.typography.h5
                )

                Spacer(modifier = Modifier.height(SpaceSmall))

                Text(
                    text = property.address,
                    style = MaterialTheme.typography.body2
                )

                Spacer(modifier = Modifier.height(SpaceSmall))

                LazyRow {
                    items(items = bitmaps) { bitmap ->

                        Image(
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = stringResource(id = R.string.apartments_photo),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(168.dp)
                                .clip(shape = MaterialTheme.shapes.medium),
                        )

                        Spacer(modifier = Modifier.width(SpaceExtraSmall))
                    }
                }

                Spacer(modifier = Modifier.height(SpaceSmall))

                Text(text = property.description)

                Spacer(modifier = Modifier.height(SpaceSmall))

                Button(
                    onClick = {
                        ContextCompat.startActivity(context, intent, null)
                    }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = stringResource(id = R.string.call),
                            tint = White,
                        )

                        Spacer(modifier = Modifier.height(SpaceNormal))

                        Text(
                            text = stringResource(R.string.call)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(SpaceSmall))

                Text(
                    text = buildString {
                        append("${if(property.type==1) "Срок сдачи" else "Тип недвижимости"}: ${property.rentTerm} \n")
                        append("Предложение от: ${property.offerType}а \n")
                        append("Контактное имя: ${property.name} \n")
                        append("Количество комнат: ${property.numberOfRooms} \n")
                        append("Этаж: ${property.floor} \n")
                        append("Количество этажей в доме: ${property.numberOfFloorsInHouse} \n")
                        append("Комиссия: ${property.commission} \n")
                    },
                    style = MaterialTheme.typography.body2
                )

                if (userType == UserType.Admin.type || property.author==id) {
                    Button(
                        onClick = { onDelete()
                                  },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                    ) {
                        Text(text = stringResource(id = R.string.delete_advertisement))
                    }
                }
            }
        }
    }
}