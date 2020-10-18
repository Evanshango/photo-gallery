package com.evans.unsplash.api

import com.evans.unsplash.data.Photo
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("results")
    val results: List<Photo>
)