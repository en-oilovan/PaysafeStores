package com.android.paysafe.ui.stores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.paysafe.model.Store
import com.android.paysafe.remote.StoreRepository
import com.android.paysafe.utils.BaseViewModel
import com.android.paysafe.utils.addTo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class StoreListViewModel(private val storeRepository: StoreRepository) : BaseViewModel() {

    private val _stores = MutableLiveData<List<Store>>()
    val stores: LiveData<List<Store>> = _stores

    fun getStores() {
//        storeRepository.getNearbyStores(46.7784, 23.6172)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//
//            }, { e -> Timber.e(e) })
//            .addTo(compositeDisposable)
        _stores.value = listOf(
            Store(1, "Profi", "fffsf", "3434", "Cluj-N"),
            Store(2, "Mega", "Nicolae Titulescu 109", "12345", "Cluj-N")
        )
    }
}