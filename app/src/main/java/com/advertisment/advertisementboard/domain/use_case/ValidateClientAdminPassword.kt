package com.advertisment.advertisementboard.domain.use_case

import com.advertisment.advertisementboard.domain.repository.AdvBoardRepository

class ValidateClientAdminPassword(
    private val repository: AdvBoardRepository
) {

    suspend operator fun invoke(password: String) =
        repository.validateClientAdminPassword(password)
}