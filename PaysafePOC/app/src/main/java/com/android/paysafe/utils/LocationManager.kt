package com.android.paysafe.utils

import android.Manifest
import android.content.Context
import android.location.Location
import androidx.annotation.RequiresPermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.SingleSubject

class LocationManager(private val context: Context) {

    private val locationSubject: SingleSubject<Location> = SingleSubject.create()

    @RequiresPermission(allOf = [ Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION ])
    fun registerLocationListener() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.lastLocation.addOnSuccessListener {
            locationSubject.onSuccess(it)
        }
    }

    fun getLocation(): Single<Location> = locationSubject.hide()
}