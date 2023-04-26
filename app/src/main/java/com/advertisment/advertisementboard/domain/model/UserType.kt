package com.advertisment.advertisementboard.domain.model

import androidx.annotation.StringRes
import com.advertisment.advertisementboard.R

sealed class UserType(@StringRes val text: Int, val type: String) {
    object Client: UserType(text = R.string.rent_an_apartment, type = "client")
    object Admin: UserType(text = R.string.admin, type = "admin")
    object ClientAdmin: UserType(text = R.string.client_admin, type = "client_admin")
    object Realtor: UserType(text = R.string.add_advertisement, type = "realtor")
    object Buyer: UserType(text = R.string.buy_property, type = "buyer")
}