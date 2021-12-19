package com.android.paysafe.remote

import com.android.paysafe.remote.model.StoreModel
import com.android.paysafe.remote.model.StoresResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface StoreApi {

    @GET("stores")
    fun getNearbyStores(
        @Query("longitude") longitude: Double,
        @Query("latitude") latitude: Double,
        @Query("radius") radius: Int,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("clientApplicationKey") appKey: String
    ): Single<StoresResponse>
}