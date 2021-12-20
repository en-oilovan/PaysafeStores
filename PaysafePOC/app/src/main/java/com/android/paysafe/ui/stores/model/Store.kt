package com.android.paysafe.ui.stores.model

import com.android.paysafe.data.model.StoreModel

data class Store(
    val id: String,
    val name: String,
    val address: String,
    val postalCode: String,
    val city: String
) {

    constructor(storeModel: StoreModel) : this(
        storeModel.posId,
        storeModel.name,
        storeModel.address,
        storeModel.postalCode,
        storeModel.city
    )

}