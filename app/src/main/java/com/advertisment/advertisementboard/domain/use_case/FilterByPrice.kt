package com.advertisment.advertisementboard.domain.use_case

import com.advertisment.advertisementboard.domain.repository.AdvBoardRepository

class FilterByPrice(
    private val repository: AdvBoardRepository
) {

    suspend operator fun invoke() {

    }
}