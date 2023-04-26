package com.advertisment.advertisementboard.domain.use_case

import com.advertisment.advertisementboard.domain.repository.AdvBoardRepository

class ValidateAdminPassword(
    private val repository: AdvBoardRepository
) {

    suspend operator fun invoke(password: String) =
        !repository.validateAdminPassword(password).isEmpty
}