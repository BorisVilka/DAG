package com.advertisment.advertisementboard.domain.use_case

import com.advertisment.advertisementboard.domain.model.Property
import com.advertisment.advertisementboard.domain.repository.AdvBoardRepository

class AddProperty(
    private val repository: AdvBoardRepository
) {

    suspend operator fun invoke(property: Property) =
        repository.addProperty(property)
}