package com.advertisment.advertisementboard.domain.use_case

data class AdvBoardUseCases(
    val validateAdminPassword: ValidateAdminPassword,
    val validateClientAdminPassword: ValidateClientAdminPassword,
    val addProperty: AddProperty,
    val getProperty: GetProperty,
    val filterByPrice: FilterByPrice,
    val uploadImage: UploadImage,
    val downloadImage: DownloadImage,
    val getLastPropertyId: GetLastPropertyId,
    val deleteProperty: DeleteProperty
)