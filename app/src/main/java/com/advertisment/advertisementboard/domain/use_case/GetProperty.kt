package com.advertisment.advertisementboard.domain.use_case

import com.advertisment.advertisementboard.domain.repository.AdvBoardRepository

class GetProperty(
    private val repository: AdvBoardRepository
) {

    suspend operator fun invoke(city: String, priceBy: String, offerTypeBy: String) =
        repository.getProperty(city, priceBy, offerTypeBy)

}