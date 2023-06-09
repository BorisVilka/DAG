package com.advertisment.advertisementboard

import android.app.Application
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AdvertisementBoardApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(this@AdvertisementBoardApplication)
    }
}