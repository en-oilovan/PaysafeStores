package com.android.paysafe.ui.stores

import android.Manifest
import androidx.annotation.RequiresPermission
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.paysafe.data.StoreRepository
import com.android.paysafe.ui.stores.model.ScreenState
import com.android.paysafe.ui.stores.model.Store
import com.android.paysafe.utils.BaseViewModel
import com.android.paysafe.utils.LocationManager
import com.android.paysafe.utils.addTo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class StoreListViewModel(private val locationManager: LocationManager, private val storeRepository: StoreRepository) : BaseViewModel() {

    private val _stores = MutableLiveData<List<Store>>()
    val stores: LiveData<List<Store>> = _stores

    @RequiresPermission(allOf = [ Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION ])
    fun registerLocationListener() {
        locationManager.registerLocationListener()
    }

    fun getStoresForCurrentLocation() {
        loadingState.value = ScreenState.Loading

        locationManager.getLocation()
            .flatMap { storeRepository.getNearbyStores(it.latitude, it.longitude) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                _stores.value = response.stores.map { Store(it) }
                loadingState.value = ScreenState.Normal
            }, { e ->
                Timber.e(e)
                loadingState.value = ScreenState.Error(e.message ?: "Error")
            })
            .addTo(compositeDisposable)
    }

    fun getStores() {
        storeRepository.getNearbyStores(48.2104844, 16.3728)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                _stores.value = response.stores.map { Store(it) }
            }, { e -> Timber.e(e) })
            .addTo(compositeDisposable)
    }

}