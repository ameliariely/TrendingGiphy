package com.ameliariely.trendinggiphy

import android.app.Application
import com.ameliariely.trendinggiphy.data.AppDataManager
import com.ameliariely.trendinggiphy.data.DataManager
import com.ameliariely.trendinggiphy.data.remote.GiphyClient

/**
 * Application class holding reference to DataManager.
 * Could replace with dependency injection for testability.
 */
class GiphyApp : Application() {

    lateinit var dataManager: DataManager

    override fun onCreate() {
        super.onCreate()

        val authToken = resources.getString(R.string.giphy_auth)
        dataManager = AppDataManager(GiphyClient(authToken).getGiphyClient())
    }

}