package com.advertisment.advertisementboard.domain.use_case

import android.net.Uri
import com.advertisment.advertisementboard.domain.repository.AdvBoardRepository

class UploadImage(
    private val repository: AdvBoardRepository
) {

    suspend operator fun invoke(filename: String, uri: Uri) {
        repository.uploadImageToStorage(filename, uri)
    }
}