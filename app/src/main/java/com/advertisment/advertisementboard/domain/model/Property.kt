package com.advertisment.advertisementboard.domain.model

import android.net.Uri

data class Property(
    val id: String,
    val propertyType: String? = null,
    var special: Boolean,
    val title: String,
    val rentTerm: String,
    val offerType: String,
    val name: String,
    val phoneNumber: String,
    val city: String,
    val address: String,
    val description: String,
    val price: Long,
    val numberOfRooms: String,
    val floor: String,
    val numberOfFloorsInHouse: String,
    val commission: String,
    val photoUris: MutableList<String>,
    val type: Int,
    val author: String
)

