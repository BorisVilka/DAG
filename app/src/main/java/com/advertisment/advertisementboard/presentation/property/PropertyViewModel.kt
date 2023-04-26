package com.advertisment.advertisementboard.presentation.property

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.advertisment.advertisementboard.domain.model.Property
import com.advertisment.advertisementboard.domain.use_case.AdvBoardUseCases
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class PropertyViewModel @Inject constructor(
    private val useCases: AdvBoardUseCases
) : ViewModel() {

    private var _property = mutableStateListOf<Property>()
    val property: SnapshotStateList<Property> = _property

    private var _propertyToBuy = mutableStateListOf<Property>()
    val propertyToBuy: SnapshotStateList<Property> = _propertyToBuy

    private val _order = mutableStateOf("Нет")
    val order: State<String> = _order

    private val _offerType = mutableStateOf("Нет")
    val offerType: State<String> = _offerType

    private val _orderDropdownIsShowing = mutableStateOf(false)
    val orderDropdownIsShowing: State<Boolean> = _orderDropdownIsShowing

    private val _offerTypeDropdownIsShowing = mutableStateOf(false)
    val offerTypeDropdownIsShowing: State<Boolean> = _offerTypeDropdownIsShowing


    fun setOrder(value: String) {
        _order.value = value
    }

    fun setOfferType(value: String) {
        _offerType.value = value
    }

    fun setOrderDropdownIsShowing(value: Boolean) {
        _orderDropdownIsShowing.value = value
    }

    fun setOfferTypeDropdownIsShowing(value: Boolean) {
        _offerTypeDropdownIsShowing.value = value
    }

    suspend fun downloadImageFromStorage(filename: String) =
        useCases.downloadImage(filename)

    suspend fun getProperty(city: String, user: String) {
        val property = useCases.getProperty(city, _order.value, _offerType.value)
        val specialProperty = mutableListOf<Property>()
        val usualProperty = mutableListOf<Property>()
        if (_order.value == "Нет" && _offerType.value == "Нет") {
            property.forEach {
                if(!_property.contains(it) && it.special) {
                    specialProperty.add(0, it)
                }
            }

            property.forEach {
                if (!_property.contains(it) && !it.special) {
                    usualProperty.add(0, it)
                }
            }
            _property.addAll(usualProperty)
            _property.addAll(0, specialProperty)
        } else {
            property.forEach {
                if (!_property.contains(it)) {
                    _property.add(0, it)
                }
            }
        }
        Log.d("TAG",user)
        val tmp = property.filter {
            Log.d("TAG","${it.type}")
            when (user) {
                "buyer" -> return@filter it.type==0
                "client" -> return@filter it.type==1
                else -> return@filter true
            }
        }.reversed()
            .toMutableList()
        Log.d("TAG","${tmp.size} ${property.size}")
        _property.clear()
        _property.addAll(tmp)
    }

    suspend fun deleteProperty(id: Property) {
        useCases.deleteProperty(id.id)
        val storageRef = Firebase.storage.reference
        id.photoUris.forEach { uri ->
            val imageRef = storageRef.child("images/${uri}")
            Log.d("TAG", uri)
            imageRef.delete().await()
        }
    }
}