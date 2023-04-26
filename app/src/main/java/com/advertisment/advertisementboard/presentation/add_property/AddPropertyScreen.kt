package com.advertisment.advertisementboard.presentation.add_property

import android.graphics.Bitmap
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.advertisment.advertisementboard.R
import com.advertisment.advertisementboard.domain.model.Property
import com.advertisment.advertisementboard.domain.model.UserType
import com.advertisment.advertisementboard.presentation.add_property.components.AddPhotoSection
import com.advertisment.advertisementboard.presentation.add_property.components.CustomDropdownMenu
import com.advertisment.advertisementboard.presentation.components.CustomEditText
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceExtraLarge
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceLarge
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceNormal
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceSmall
import com.advertisment.advertisementboard.presentation.util.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@ExperimentalCoilApi
@Composable
fun AddPropertyScreen(
    navController: NavController,
    userType: String?,
    type1:String,
    type2:String,
    type3:String,
    id: String,
    viewModel: AddPropertyViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    val cityDropdownMenuItems = listOf(
        stringResource(id = R.string.makhachkala),
        stringResource(id = R.string.derbent),
        stringResource(id = R.string.kaspiysk),
        stringResource(id = R.string.izberbash),
        stringResource(id = R.string.dagogni),
        stringResource(id = R.string.dubki),
        stringResource(id = R.string.buynaysk),
        stringResource(id = R.string.khasavyurt),
        stringResource(id = R.string.kizlar),
        stringResource(id = R.string.kizilurt),
    )

    val commissionDropdownMenuItems = listOf("0%", "25%", "50%", "100%")

    val offerTypesDropdownMenuItems = listOf(
        stringResource(id = R.string.owner),
        stringResource(id = R.string.realtor)
    )

    val rentTerm = listOf(
        stringResource(id = R.string.day_term),
        stringResource(id = R.string.long_term)
    )

    val propertyType = listOf(
        stringResource(id = R.string.apartments),
        stringResource(id = R.string.house),
      //  stringResource(id = R.string.area)
    )

    val type = listOf(
        "Продать",
        "Снять"
    )

    viewModel.setType(type1)
    viewModel.setRentTerm(type2)
    viewModel.setOfferType(type3)


    val selectImageLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult = { uri ->
               try {
                   val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                   resizeImage(bitmap)
                   viewModel.addPhoto(bitmap)
//                if (Build.VERSION.SDK_INT < 28) {
//                    viewModel.addPhoto()
//                } else {
//                    val source = ImageDecoder.createSource(context.contentResolver, uri!!)
//
//                    viewModel.addPhoto(ImageDecoder.decodeBitmap(source))
//                }

                   viewModel.addPhotoUri(uri!!)
               } catch (e: Exception) {
                   e.printStackTrace()
               }
            }
        )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = SpaceNormal)
    ) {

        Spacer(modifier = Modifier.height(SpaceNormal))

        AddPhotoSection(onClick = { selectImageLauncher.launch("image/*") })

        Spacer(modifier = Modifier.height(SpaceNormal))

        LazyRow {
            items(items = viewModel.photos) { item ->

                Image(
                    bitmap = item.asImageBitmap(),
                    contentDescription = stringResource(id = R.string.apartments_photo),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(SpaceExtraLarge*3)
                )

                Spacer(modifier = Modifier.width(SpaceSmall))
            }
        }

        Spacer(modifier = Modifier.height(SpaceNormal))

        /*CustomDropdownMenu(
            items = type,
            labelText = "Тип объявления",
            selectedItem = viewModel.type.value,
            isShowing = viewModel.typeDropdownIsShowing.value,
            onMenuClick = { viewModel.setTypeDropdownIsShowing(true) },
            onItemClick = { viewModel.setType(it) },
            onDismissRequest = { viewModel.setTypeDropdownIsShowing(false) },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(SpaceNormal))

        Row {

            if (viewModel.type.value=="Сдать") {
                CustomDropdownMenu(
                    items = rentTerm,
                    labelText = stringResource(id = R.string.rent_term),
                    selectedItem = viewModel.rentTerm.value,
                    isShowing = viewModel.rentTermDropdownIsShowing.value,
                    onMenuClick = { viewModel.setRentTermDropdownIsShowing(true) },
                    onItemClick = { viewModel.setRentTerm(it) },
                    onDismissRequest = { viewModel.setRentTermDropdownIsShowing(false) },
                    modifier = Modifier.fillMaxWidth(0.5f)
                )
            } else {
                CustomDropdownMenu(
                    items = propertyType,
                    labelText = stringResource(id = R.string.property_type),
                    selectedItem = viewModel.rentTerm.value,
                    isShowing = viewModel.rentTermDropdownIsShowing.value,
                    onMenuClick = { viewModel.setRentTermDropdownIsShowing(true) },
                    onItemClick = { viewModel.setRentTerm(it) },
                    onDismissRequest = { viewModel.setRentTermDropdownIsShowing(false) },
                    modifier = Modifier.fillMaxWidth(0.5f)
                )
            }

            Spacer(modifier = Modifier.width(SpaceNormal))

            CustomDropdownMenu(
                items = offerTypesDropdownMenuItems,
                labelText = stringResource(id = R.string.offer_type),
                selectedItem = viewModel.offerType.value,
                isShowing = viewModel.offerTypeDropdownIsShowing.value,
                onMenuClick = { viewModel.setOfferTypeDropdownIsShowing(true) },
                onItemClick = { viewModel.setOfferType(it) },
                onDismissRequest = { viewModel.setOfferTypeDropdownIsShowing(false) },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(SpaceNormal))*/

        CustomEditText(
            value = viewModel.title.value,
            onValueChange = { viewModel.setTitle(it) },
            labelText = stringResource(id = R.string.title),
            hintText = stringResource(id = R.string.title)
        )

        Spacer(modifier = Modifier.height(SpaceNormal))

        CustomEditText(
            value = viewModel.name.value,
            onValueChange = { viewModel.setName(it) },
            labelText = stringResource(id = R.string.name),
            hintText = stringResource(id = R.string.name)
        )

        Spacer(modifier = Modifier.height(SpaceNormal))

        CustomEditText(
            value = viewModel.phoneNumber.value,
            onValueChange = { viewModel.setPhoneNumber(it) },
            labelText = stringResource(id = R.string.phone_number_label),
            hintText = stringResource(id = R.string.phone_number_label),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )

        Spacer(modifier = Modifier.height(SpaceNormal))

        Row {

            CustomEditText(
                value = viewModel.price.value,
                onValueChange = { viewModel.setPrice(it) },
                labelText = stringResource(id = R.string.price),
                hintText = stringResource(id = R.string.price),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(0.5f)
            )

            Spacer(modifier = Modifier.width(SpaceNormal))

            CustomEditText(
                value = viewModel.numberOfRooms.value,
                onValueChange = { viewModel.setNumberOfRooms(it) },
                labelText = stringResource(id = R.string.numberOfRooms),
                hintText = stringResource(id = R.string.numberOfRooms),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(SpaceNormal))

        CustomEditText(
            value = viewModel.address.value,
            onValueChange = { viewModel.setAddress(it) },
            labelText = stringResource(id = R.string.address),
            hintText = stringResource(id = R.string.address)
        )

        Spacer(modifier = Modifier.height(SpaceNormal))

        CustomEditText(
            value = viewModel.description.value,
            onValueChange = { viewModel.setDescription(it) },
            labelText = stringResource(id = R.string.description),
            hintText = stringResource(id = R.string.description),
            singleLine = false,
            modifier = Modifier.height(128.dp)
        )

        Spacer(modifier = Modifier.height(SpaceNormal))

        Row {

            CustomEditText(
                value = viewModel.floor.value,
                onValueChange = { viewModel.setFloor(it) },
                labelText = stringResource(id = R.string.floor),
                hintText = stringResource(id = R.string.floor),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(0.5f)
            )

            Spacer(modifier = Modifier.width(SpaceNormal))

            CustomEditText(
                value = viewModel.numberOfFloorsInHouse.value,
                onValueChange = { viewModel.setNumberOfFloorsInHouse(it) },
                labelText = stringResource(id = R.string.numberOfFloorsInHouse),
                hintText = stringResource(id = R.string.numberOfFloorsInHouse),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(SpaceNormal))

        Row {
            CustomDropdownMenu(
                items = cityDropdownMenuItems,
                labelText = stringResource(id = R.string.city),
                selectedItem = viewModel.city.value,
                isShowing = viewModel.cityDropdownIsShowing.value,
                onMenuClick = { viewModel.setCityDropdownIsShowing(true) },
                onItemClick = { viewModel.setCity(it)
                    viewModel.setCityDropdownIsShowing(false)
                              },
                onDismissRequest = { viewModel.setCityDropdownIsShowing(false) },
                modifier = Modifier.fillMaxWidth(0.5f)
            )

            Spacer(modifier = Modifier.width(SpaceNormal))

            CustomDropdownMenu(
                items = commissionDropdownMenuItems,
                labelText = stringResource(id = R.string.commission),
                selectedItem = viewModel.commission.value,
                isShowing = viewModel.commissionDropdownIsShowing.value,
                onMenuClick = { viewModel.setCommissionDropdownIsShowing(true) },
                onItemClick = {
                    viewModel.setCommissionDropdownIsShowing(false)
                    viewModel.setCommission(it)

                              },
                onDismissRequest = { viewModel.setCommissionDropdownIsShowing(false) },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(SpaceNormal))

        val snackbarVisible by remember { mutableStateOf(false) }

            if (snackbarVisible)
                Snackbar(
                    backgroundColor = MaterialTheme.colors.error,
                ) {
                    Text(text = stringResource(id = R.string.enter_all_fields))
                }

        Button(
            onClick = {
                if(viewModel.name.value.isEmpty()) {
                    Toast.makeText(context,"Введите имя",Toast.LENGTH_SHORT).show()
                }   else if(viewModel.phoneNumber.value.isEmpty()) {
                Toast.makeText(context,"Введите номер телефона",Toast.LENGTH_SHORT).show()
                } else if(viewModel.city.value=="Город") {
                    Toast.makeText(context,"Выберите город",Toast.LENGTH_SHORT).show()
                } else {
                    coroutineScope.launch(Dispatchers.IO) {
                        var list = mutableListOf<String>()
                        for(i in viewModel.photoUris.indices) {
                            list.add("${viewModel.id.value.toInt()+1}_${i}")
                        }
                        Log.d("TAG",userType!!)
                        viewModel.addProperty(
                            Property(
                                id = viewModel.id.value,
                                special = viewModel.special.value,
                                title = viewModel.title.value,
                                rentTerm = viewModel.rentTerm.value,
                                offerType = viewModel.offerType.value,
                                phoneNumber = viewModel.phoneNumber.value,
                                name = viewModel.name.value,
                                city = viewModel.city.value,
                                address = viewModel.address.value,
                                description = viewModel.description.value,
                                price =
                                if (viewModel.price.value.isNotEmpty()) viewModel.price.value.toLong()
                                else 0L,
                                numberOfRooms = viewModel.numberOfRooms.value,
                                floor = viewModel.floor.value,
                                numberOfFloorsInHouse = viewModel.numberOfFloorsInHouse.value,
                                commission = viewModel.commission.value,
                                photoUris = list,
                                type = if(viewModel.type.value==type[0]) 0 else 1,
                                author = id
                            )
                        )
                        /*viewModel.photoUris.forEach { uri ->
                            viewModel.uploadImageToStorage(uri.toString(), uri)
                        }*/
                        for(i in viewModel.photoUris.indices) {
                            viewModel.uploadImageToStorage("", viewModel.photoUris[i],i)
                        }
                        withContext(Dispatchers.Main) {
                            Toast.makeText(context,"Объявление добавлено",Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                            navController.popBackStack()
                            navController.popBackStack()
                            navController.popBackStack()
                            navController.popBackStack()
                            //navController.navigate(Screen.AddPropertyScreen.withArgs(userType!!))
                        }
                    }
                }
            }

        ) {
            Text(
                text = stringResource(id = R.string.add),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = SpaceSmall)
            )
        }

        Spacer(modifier = Modifier.height(SpaceLarge))
    }
}

fun resizeImage(image: Bitmap): Bitmap {

    val width = image.width
    val height = image.height

    val scaleWidth = width / 10
    val scaleHeight = height / 10

    if (image.byteCount <= 1000000)
        return image

    return Bitmap.createScaledBitmap(image, scaleWidth, scaleHeight, false)
}