package com.android.paysafe.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.paysafe.ui.stores.model.ScreenState
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleSource
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

open class BaseViewModel: ViewModel() {

    val loadingState = MutableLiveData<ScreenState>()

    val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.clear()
    }
}

fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}