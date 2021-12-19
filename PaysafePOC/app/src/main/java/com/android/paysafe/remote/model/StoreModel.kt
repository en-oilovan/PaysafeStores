package com.android.paysafe.remote.model

import com.google.gson.annotations.SerializedName

data class StoreModel(
    @SerializedName("id")
    val id: Int,
    val name: String,
    val address: String,
    val postalCode: String,
    val city: String)