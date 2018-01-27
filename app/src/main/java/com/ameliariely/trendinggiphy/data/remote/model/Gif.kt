package com.ameliariely.trendinggiphy.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Converted directly from API response.
 * This class represents the Gif as sent from the network.
 */

class Gif {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("images")
    @Expose
    var imagesObject: ImagesObject? = null
}