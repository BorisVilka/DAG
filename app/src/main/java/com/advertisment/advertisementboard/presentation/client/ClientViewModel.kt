package com.advertisment.advertisementboard.presentation.client

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.advertisment.advertisementboard.domain.use_case.AdvBoardUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClientViewModel @Inject constructor(
    private val useCases: AdvBoardUseCases
): ViewModel() {

    private val _adminPassword = mutableStateOf("")
    val adminPassword: State<String> = _adminPassword

    private val _clientAdminPassword = mutableStateOf("")
    val clientAdminPassword: State<String> = _clientAdminPassword

    private val _adminError = mutableStateOf(false)
    val adminError: State<Boolean> = _adminError

    private val _clientAdminError = mutableStateOf(false)
    val clientAdminError: State<Boolean> = _clientAdminError

    fun setAdminPassword(value: String) {
        _adminPassword.value = value
    }

    fun setClientAdminPassword(value: String) {
        _clientAdminPassword.value = value
    }

    fun setAdminError(value: Boolean) {
        _adminError.value = value
    }

    fun setClientAdminError(value: Boolean) {
        _clientAdminError.value = value
    }

    suspend fun validateAdminPassword() =
        useCases.validateAdminPassword(password = adminPassword.value)

    suspend fun validateClientAdminPassword() =
        useCases.validateClientAdminPassword(password = clientAdminPassword.value)
}