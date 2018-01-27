package com.ameliariely.trendinggiphy.ui.base

import com.ameliariely.trendinggiphy.data.DataManager
import io.reactivex.disposables.CompositeDisposable


abstract class BaseViewModel(val dataManager: DataManager) {

    /**
     * Cancel subscriptions when activity is destroyed
     */
    val disposable = CompositeDisposable()

    fun onDestroy() {
        disposable.clear()
    }
}
