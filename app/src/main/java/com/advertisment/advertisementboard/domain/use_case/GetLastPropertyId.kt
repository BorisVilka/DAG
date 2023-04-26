package com.advertisment.advertisementboard.domain.use_case

import com.advertisment.advertisementboard.domain.repository.AdvBoardRepository

class GetLastPropertyId(
    private val repository: AdvBoardRepository
) {

    suspend operator fun invoke() = repository.getLastPropertyId()
}