package com.advertisment.advertisementboard.domain.repository

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.advertisment.advertisementboard.domain.model.Property
import com.google.firebase.firestore.QuerySnapshot

interface AdvBoardRepository {

    suspend fun validateAdminPassword(password: String): QuerySnapshot

    suspend fun validateClientAdminPassword(password: String): QuerySnapshot

    suspend fun addProperty(property: Property)

    suspend fun getProperty(
        city: String,
        priceBy: String,
        offerTypeBy: String
    ): SnapshotStateList<Property>

    suspend fun uploadImageToStorage(filename: String, uri: Uri)

    suspend fun downloadImageFromStorage(filename: String): Bitmap

    suspend fun getLastPropertyId(): String

    fun deleteProperty(id: String)
}