package com.ameliariely.trendinggiphy.data

import com.ameliariely.trendinggiphy.data.remote.GiphyApi
import com.ameliariely.trendinggiphy.data.remote.model.Gif
import io.reactivex.Single

interface DataManager {
    fun getRemoteGifList(offset: Int = 0): Single<List<Gif>?>
    fun getGifsForSearch(query: String, offset: Int = 0): Single<List<Gif>?>
}

class AppDataManager(private val remoteRepository: GiphyApi) : DataManager {

    override fun getGifsForSearch(query: String, offset: Int): Single<List<Gif>?> = remoteRepository.getGifSearchList(query, offset).map { it.gifs }
    override fun getRemoteGifList(offset: Int): Single<List<Gif>?> = remoteRepository.getGifTrendingResponse(offset).map { it.gifs }

}

