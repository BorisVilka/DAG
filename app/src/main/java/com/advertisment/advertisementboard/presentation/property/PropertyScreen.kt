package com.advertisment.advertismentboard.presentation.property

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.advertisment.advertisementboard.R
import com.advertisment.advertisementboard.domain.model.UserType
import com.advertisment.advertisementboard.presentation.add_property.components.CustomDropdownMenu
import com.advertisment.advertisementboard.presentation.property.PropertyViewModel
import com.advertisment.advertisementboard.presentation.property.components.AddPropertyButton
import com.advertisment.advertisementboard.presentation.property.components.PropertyItem
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceLarge
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceNormal
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceSmall
import com.advertisment.advertisementboard.presentation.util.Screen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@ExperimentalPermissionsApi
@Composable
fun MainScreen(
    navController: NavController,
    userType: String?,
    city: String?,
    id: String?,
    viewModel: PropertyViewModel = hiltViewModel()
) {

    val coroutineScope = rememberCoroutineScope()

    val orderDropdownItems = listOf(
        "По возрастанию",
        "По убыванию",
        "Нет"
    )

    val offerTypeDropdownItems = listOf(
        "Собственник",
        "Риэлтор",
        "Нет"
    )

    LaunchedEffect(key1 = true) {
        viewModel.getProperty(city!!, userType!!)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(SpaceNormal))

        if (userType == UserType.Admin.type || userType == UserType.ClientAdmin.type) {
            AddPropertyButton(
                onClick = {
                    navController.navigate(
                        Screen.FirstScreen.withArgs(UserType.Client.type,id!!)
                    )
                },
                text = stringResource(id = R.string.add_rent_property)
            )

//            Spacer(modifier = Modifier.height(SpaceNormal))
//
//            AddPropertyButton(
//                onClick = {
//                    navController.navigate(
//                        Screen.AddPropertyScreen.withArgs(UserType.Buyer.type)
//                    )
//                },
//                text = stringResource(id = R.string.add_buy_property)
//            )

            Spacer(modifier = Modifier.height(SpaceNormal))
        }

        Row(
            modifier = Modifier.padding(horizontal = SpaceNormal)
        ) {
            CustomDropdownMenu(
                items = orderDropdownItems,
                labelText = stringResource(id = R.string.order_by),
                selectedItem = viewModel.order.value,
                isShowing = viewModel.orderDropdownIsShowing.value,
                onMenuClick = { viewModel.setOrderDropdownIsShowing(true) },
                onItemClick = {
                    viewModel.setOrder(it)
                    coroutineScope.launch(Dispatchers.IO) {
                        viewModel.getProperty(city!!, userType!!)
                    }
                },
                onDismissRequest = { viewModel.setOrderDropdownIsShowing(false) },
                modifier = Modifier.fillMaxWidth(0.5f)
            )

            Spacer(modifier = Modifier.width(SpaceNormal))

            CustomDropdownMenu(
                items = offerTypeDropdownItems,
                labelText = stringResource(id = R.string.offer_type),
                selectedItem = viewModel.offerType.value,
                isShowing = viewModel.offerTypeDropdownIsShowing.value,
                onMenuClick = { viewModel.setOfferTypeDropdownIsShowing(true) },
                onItemClick = {
                    viewModel.setOfferType(it)
                    coroutineScope.launch(Dispatchers.IO) {
                        viewModel.getProperty(city!!, userType!!)
                    }
                },
                onDismissRequest = { viewModel.setOfferTypeDropdownIsShowing(false) },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(SpaceNormal))

        LazyColumn(
            contentPadding = PaddingValues(vertical = SpaceSmall)
        ) {
            items(
                items =
                when (viewModel.order.value) {
                    "По возрастанию" -> {
                        when (viewModel.offerType.value) {
                            "Собственник" -> {
                                val property = viewModel.property.sortedBy { it.price }
                                property.sortedByDescending { it.offerType }
                            }
                            "Риэлтор" -> {
                                val property = viewModel.property.sortedBy { it.price }
                                property.sortedBy { it.offerType }
                            }
                            else -> {
                                viewModel.property.sortedBy { it.price }
                            }
                        }
                    }
                    "По убыванию" -> {
                        when (viewModel.offerType.value) {
                            "Собственник" -> {
                                val property =
                                    viewModel.property.sortedByDescending { it.price }
                                property.sortedByDescending { it.offerType }
                            }
                            "Риэлтор" -> {
                                val property =
                                    viewModel.property.sortedByDescending { it.price }
                                property.sortedBy { it.offerType }
                            }
                            else -> {
                                viewModel.property.sortedByDescending { it.price }
                            }
                        }
                    }
                    else -> {
                        when (viewModel.offerType.value) {
                            "Собственник" -> {
                                viewModel.property.sortedByDescending { it.offerType }
                            }
                            "Риэлтор" -> {
                                viewModel.property.sortedBy { it.offerType }
                            }
                            else -> {
                                viewModel.property
                            }
                        }
                    }
                }
            ) { property ->
                val starIcon = remember { mutableStateOf(property.special) }
                PropertyItem(
                    property = property,
                    userType = userType!!,
                    onDelete = {
                        coroutineScope.launch(Dispatchers.IO) {
                          try {
                              viewModel.deleteProperty(property)
                          } catch (e :Exception) {
                              e.printStackTrace()
                          }
                        }
                        navController.popBackStack()
                        navController.navigate(
                            Screen.PropertyScreen.withArgs(userType!!,city!!,id!!)
                        )
                    },
                    onPin = {
                        coroutineScope.launch(Dispatchers.IO) {
                            Firebase.firestore.collection("property").document(property.id)
                                .update("special", !property.special).await()
                            property.special = !property.special
                            starIcon.value = !starIcon.value
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.9f),
                    starIcon = starIcon,
                    id = id!!
                )
                Spacer(modifier = Modifier.height(SpaceLarge))
            }
        }
    }

}