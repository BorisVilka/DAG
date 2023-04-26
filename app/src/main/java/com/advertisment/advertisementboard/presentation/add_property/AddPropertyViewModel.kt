package com.advertisment.advertisementboard.presentation.add_property

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advertisment.advertisementboard.domain.model.Property
import com.advertisment.advertisementboard.domain.use_case.AdvBoardUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPropertyViewModel @Inject constructor(
    private val useCases: AdvBoardUseCases
): ViewModel() {

    private val _id = mutableStateOf("")
    val id: State<String> = _id

    private val _type = mutableStateOf("Сдать")
    val type: State<String> = _type

    private val _typeDropdownIsShowing = mutableStateOf(false)
    val typeDropdownIsShowing: State<Boolean> = _typeDropdownIsShowing

    private val _propertyType = mutableStateOf("")
    val propertyType: State<String> = _propertyType

    private val _special = mutableStateOf(false)
    val special: State<Boolean> = _special

    private val _title = mutableStateOf("")
    val title: State<String> = _title

    private val _rentTerm = mutableStateOf("Посуточно")
    val rentTerm: State<String> = _rentTerm

    private val _name = mutableStateOf("")
    val name: State<String> = _name

    private val _phoneNumber = mutableStateOf("")
    val phoneNumber: State<String> = _phoneNumber

    private val _city = mutableStateOf("Город")
    val city: State<String> = _city

    private val _address = mutableStateOf("")
    val address: State<String> = _address

    private val _description = mutableStateOf("")
    val description: State<String> = _description

    private val _price = mutableStateOf("")
    val price: State<String> = _price

    private val _numberOfRooms = mutableStateOf("")
    val numberOfRooms: State<String> = _numberOfRooms

    private val _floor = mutableStateOf("")
    val floor: State<String> = _floor

    private val _numberOfFloorsInHouse = mutableStateOf("")
    val numberOfFloorsInHouse: State<String> = _numberOfFloorsInHouse

    private val _offerType = mutableStateOf("Собственник")
    val offerType: State<String> = _offerType

    private val _commission = mutableStateOf("0%")
    val commission: State<String> = _commission

    private var _photos = mutableStateListOf<Bitmap>()
    val photos: SnapshotStateList<Bitmap> = _photos


    private var _photoS = mutableStateListOf<String>()
    val photoS: SnapshotStateList<String> = _photoS

    private var _photoUris = mutableStateListOf<Uri>()
    val photoUris: SnapshotStateList<Uri> = _photoUris

    private val _cityDropdownIsShowing = mutableStateOf(false)
    val cityDropdownIsShowing: State<Boolean> = _cityDropdownIsShowing

    private val _commissionDropdownIsShowing = mutableStateOf(false)
    val commissionDropdownIsShowing: State<Boolean> = _commissionDropdownIsShowing

    private val _offerTypeDropdownIsShowing = mutableStateOf(false)
    val offerTypeDropdownIsShowing: State<Boolean> = _offerTypeDropdownIsShowing

    private val _rentTermDropdownIsShowing = mutableStateOf(false)
    val rentTermDropdownIsShowing: State<Boolean> = _rentTermDropdownIsShowing

    private val _propertyTypeDropdownIsShowing = mutableStateOf(false)
    val propertyTypeDropdownIsShowing: State<Boolean> = _propertyTypeDropdownIsShowing

    init {
        viewModelScope.launch {
            _id.value = useCases.getLastPropertyId()
        }
    }

    fun setPropertyType(value: String) {
        _propertyType.value = value
    }

    fun setSpecial(value: Boolean) {
        _special.value = value
    }

    fun setTitle(value: String) {
        _title.value = value
    }

    fun setRentTerm(value: String) {
        _rentTerm.value = value
    }

    fun setName(value: String) {
        _name.value = value
    }

    fun setPhoneNumber(value: String) {
        _phoneNumber.value = value
    }

    fun setCity(value: String) {
        _city.value = value
    }

    fun setAddress(value: String) {
        _address.value = value
    }

    fun setDescription(value: String) {
        _description.value = value
    }

    fun setPrice(value: String) {
        _price.value = value
    }

    fun setNumberOfRooms(value: String) {
        _numberOfRooms.value = value
    }

    fun setFloor(value: String) {
        _floor.value = value
    }

    fun setNumberOfFloorsInHouse(value: String) {
        _numberOfFloorsInHouse.value = value
    }

    fun setOfferType(value: String) {
        _offerType.value = value
    }

    fun setCommission(value: String) {
        _commission.value = value
    }

    fun addPhoto(value: Bitmap) {
        _photos.add(value)
    }

    fun addPhotoUri(value: Uri) {
        _photoUris.add(value)
    }

    fun setCityDropdownIsShowing(value: Boolean) {
        _cityDropdownIsShowing.value = value
    }

    fun setCommissionDropdownIsShowing(value: Boolean) {
        _commissionDropdownIsShowing.value = value
    }

    fun setTypeDropdownIsShowing(value: Boolean) {
        _typeDropdownIsShowing.value = value
    }

    fun setType(value: String) {
        _type.value = value
    }

    fun setOfferTypeDropdownIsShowing(value: Boolean) {
        _offerTypeDropdownIsShowing.value = value
    }

    fun setRentTermDropdownIsShowing(value: Boolean) {
        _rentTermDropdownIsShowing.value = value
    }

    fun setPropertyTypeDropdownIsShowing(value: Boolean) {
        _propertyTypeDropdownIsShowing.value = value
    }

    suspend fun addProperty(property: Property) = useCases.addProperty(property)

    suspend fun uploadImageToStorage(filename: String, uri: Uri, ind: Int) {
        Log.d("TAG","${id.value.toInt()+1}_${ind} ${uri.toString()}")
        viewModelScope.launch {
            useCases.uploadImage("${id.value.toInt()+1}_${_photoUris.indexOf(uri)}", uri)
        }
    }
}