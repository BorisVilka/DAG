package com.advertisment.advertisementboard.data.remote.repository

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.advertisment.advertisementboard.data.remote.AdvBoardService
import com.advertisment.advertisementboard.domain.model.Property
import com.advertisment.advertisementboard.domain.repository.AdvBoardRepository

class AdvBoardRepositoryImpl(
    private val service: AdvBoardService
) : AdvBoardRepository {

    override suspend fun validateAdminPassword(password: String) =
        service.validateAdminPassword(password)

    override suspend fun validateClientAdminPassword(password: String) =
        service.validateClientAdminPassword(password)

    override suspend fun addProperty(property: Property) {
        service.addProperty(property)
    }

    override suspend fun getProperty(
        city: String,
        priceBy: String,
        offerTypeBy: String
    ): SnapshotStateList<Property> {
        val documents = service.getProperty(city)
        val property = mutableStateListOf<Property>()
        for (document in documents) {
            val id = document.get("id") as String
            val special = document.get("special") as Boolean
            val title = document.get("title") as String
            val rentTerm = document.get("rentTerm") as String
            val offerType = document.get("offerType") as String
            val name = document.get("name") as String
            val phoneNumber = document.get("phoneNumber") as String
            val cityInDocument = document.get("city") as String
            val address = document.get("address") as String
            val description = document.get("description") as String
            val price = document.get("price") as Long
            val numberOfRooms = document.get("numberOfRooms") as String
            val floor = document.get("floor") as String
            val numberOfFloorsInHouse = document.get("numberOfFloorsInHouse") as String
            val commission = document.get("commission") as String
            val photos = document.get("photoUris") as ArrayList<String>
            val type = document.get("type") as Long
            val author = document.get("author") as String

            property.add(
                Property(
                    id = id,
                    special = special,
                    title = title,
                    rentTerm = rentTerm,
                    offerType = offerType,
                    name = name,
                    phoneNumber = phoneNumber,
                    city = cityInDocument,
                    address = address,
                    description = description,
                    price = price,
                    numberOfRooms = numberOfRooms,
                    floor = floor,
                    numberOfFloorsInHouse = numberOfFloorsInHouse,
                    commission = commission,
                    photoUris = photos.toMutableList(),
                    type = type.toInt(),
                    author = author
                )
            )
        }
        return property
    }

    override suspend fun uploadImageToStorage(filename: String, uri: Uri) {
        service.uploadImageToStorage(filename, uri)
    }

    override suspend fun downloadImageFromStorage(filename: String): Bitmap =
        service.downloadImageFromStorage(filename)

    override suspend fun getLastPropertyId() = service.getLastPropertyId()

    override fun deleteProperty(id: String) {
        service.deleteProperty(id)
    }
}