package com.android.paysafe.remote

import com.android.paysafe.NEARBY_RADIUS_M
import com.android.paysafe.STORE_COUNT

class StoreRepository(private val api: StoreApi) {

    fun getNearbyStores(latitude: Double, longitude: Double) =
        api.getNearbyStores(latitude, longitude, NEARBY_RADIUS_M, 1, STORE_COUNT, "fgfsgsgf")

}