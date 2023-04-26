package com.advertisment.advertisementboard.data.remote

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import com.advertisment.advertisementboard.domain.model.Property
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await


class AdvBoardService(
    private val context: Context
) {

    private val adminsPasswordsCollectionRef =
        Firebase.firestore.collection("adminsPasswords")

    private val clientAdminsPasswordsCollectionRef =
        Firebase.firestore.collection("clientAdminsPasswords")

    private val propertyCollectionRef =
        Firebase.firestore.collection("property")

    private val storageRef = Firebase.storage.reference

    suspend fun validateAdminPassword(password: String): QuerySnapshot =
        adminsPasswordsCollectionRef.whereEqualTo("password", password).get().await()

    suspend fun validateClientAdminPassword(password: String): QuerySnapshot =
        clientAdminsPasswordsCollectionRef.whereEqualTo("password", password).get().await()

    suspend fun getLastPropertyId(): String {
        val document = propertyCollectionRef.document("lastPropertyId").get().await()
        return document.get("lastPropertyId") as String
    }

    private fun setNewLastPropertyId(id: String) {
        propertyCollectionRef.document("lastPropertyId").set(
            hashMapOf("lastPropertyId" to id)
        )
    }

    suspend fun addProperty(property: Property) {
        val id = (getLastPropertyId().toInt() + 1).toString()
        val offerTypeCode = if(property.offerType == "Собственник" ) 1 else 2
        val propertyDocument = hashMapOf(
            "id" to id,
            "special" to property.special,
            "title" to property.title,
            "rentTerm" to property.rentTerm,
            "offerType" to property.offerType,
            "offerTypeCode" to offerTypeCode,
            "name" to property.name,
            "phoneNumber" to property.phoneNumber,
            "city" to property.city,
            "address" to property.address,
            "description" to property.description,
            "price" to property.price,
            "numberOfRooms" to property.numberOfRooms,
            "floor" to property.floor,
            "numberOfFloorsInHouse" to property.numberOfFloorsInHouse,
            "commission" to property.commission,
            "photoUris" to property.photoUris.toTypedArray().map { it.toString() },
            "type" to property.type,
            "author" to property.author
        )

        propertyCollectionRef.document(propertyDocument["id"] as String).set(propertyDocument)
        setNewLastPropertyId((id.toInt()).toString())
    }

    suspend fun getProperty(city: String): QuerySnapshot =
        propertyCollectionRef
            .whereEqualTo("city", city)
            .get()
            .await()

    fun deleteProperty(id: String) {
        propertyCollectionRef.document(id).delete()
    }

    suspend fun uploadImageToStorage(filename: String, uri: Uri) {
        try {
            storageRef.child("images/$filename").putFile(uri).await()
        } catch (e: Exception) {
            Log.d("Upload image error: ", e.localizedMessage ?: "error")
        }
    }

    suspend fun downloadImageFromStorage(filename: String): Bitmap {
        val maxDownloadSize = 5L * 1024 * 1024
        return try {
            val bytes = storageRef.child("images/$filename").getBytes(maxDownloadSize).await()
            BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        } catch (e: Exception) {
            val bytes = storageRef.child("images/$filename").getBytes(maxDownloadSize).await()
            Log.d("Download image error: ", e.localizedMessage ?: "error")
            BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        }
    }

}
