package com.ameliariely.trendinggiphy.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ImagesObject {

    @SerializedName("fixed_width")
    @Expose
    var fixedWidth: ImageObject? = null
}