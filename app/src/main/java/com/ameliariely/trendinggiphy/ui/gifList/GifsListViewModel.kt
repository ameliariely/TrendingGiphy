package com.ameliariely.trendinggiphy.ui.gifList

import android.util.Log
import com.ameliariely.trendinggiphy.data.DataManager
import com.ameliariely.trendinggiphy.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GifsListViewModel(dataManager: DataManager, private val navigator: GifsListNavigator) :
        BaseViewModel(dataManager) {

    var query = ""

    init {
        fetchGifsForQueryAndSetAdapterItems(query)
    }

    private fun fetchTrendingGifsAndSetAdapterItems() {
        disposable.add(dataManager.getRemoteGifList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { navigator.setAdapterItems(it) },
                        {
                            Log.d("View model error", it.message)
                            navigator.showError(it)
                        }))
    }

    fun fetchGifsForQueryAndSetAdapterItems(newQuery: String) {
        query = newQuery
        if (newQuery.isEmpty()) {
            fetchTrendingGifsAndSetAdapterItems()
        } else {
            disposable.add(dataManager.getGifsForSearch(newQuery)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { navigator.setAdapterItems(it) },
                            {
                                Log.d("View model error", it.message)
                                navigator.showError(it)
                            }))
        }
    }

    fun fetchNextGifsForCurrentQueryAndSetAdapterItems(itemCount: Int) {
        if (query.isEmpty()) {
            fetchTrendingGifsAndSetAdapterItems()
        } else {
            disposable.add(dataManager.getGifsForSearch(query, itemCount)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { navigator.addAdapterItems(it) },
                            {
                                Log.d("View model error", it.message)
                                navigator.showError(it)
                            }))
        }
    }
}
