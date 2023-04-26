package com.advertisment.advertisementboard.domain.use_case

import com.advertisment.advertisementboard.domain.repository.AdvBoardRepository

class DeleteProperty(
    private val repository: AdvBoardRepository
) {

    operator fun invoke(id: String) {
        repository.deleteProperty(id)
    }
}