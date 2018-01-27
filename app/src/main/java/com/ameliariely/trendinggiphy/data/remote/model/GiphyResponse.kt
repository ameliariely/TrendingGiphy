package com.ameliariely.trendinggiphy.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Class for GSON to be able to parse the
 * Gif list out of the Giphy API Response
 */

class GiphyResponse {

    @SerializedName("status")
    @Expose
    var status: Int? = null
    @SerializedName("data")
    @Expose
    var gifs: List<Gif>? = null

}