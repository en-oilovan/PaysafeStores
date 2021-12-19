package com.android.paysafe.ui.stores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.paysafe.model.Store
import com.android.paysafe.remote.StoreRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class StoreListViewModel(private val storeRepository: StoreRepository) : ViewModel() {

    private val _stores = MutableLiveData<Store>()
    val stores: LiveData<Store> = _stores

    fun getStores() {
        storeRepository.getNearbyStores(46.7784, 23.6172)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}