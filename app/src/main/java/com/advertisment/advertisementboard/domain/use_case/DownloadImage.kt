package com.advertisment.advertisementboard.domain.use_case

import com.advertisment.advertisementboard.domain.repository.AdvBoardRepository

class DownloadImage(
    private val repository: AdvBoardRepository
) {

    suspend operator fun invoke(filename: String) =
        repository.downloadImageFromStorage(filename)

}