package com.evans.unsplash.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    @SerializedName("id")
    val id: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("urls")
    val urls: PhotoUrls,
    @SerializedName("user")
    val user: User
) : Parcelable {

    @Parcelize
    data class PhotoUrls(
        @SerializedName("raw")
        val raw: String,
        @SerializedName("full")
        val full: String,
        @SerializedName("regular")
        val regular: String,
        @SerializedName("small")
        val small: String,
        @SerializedName("thumb")
        val thumb: String
    ): Parcelable

    @Parcelize
    data class User(
        @SerializedName("name")
        val name: String,
        @SerializedName("username")
        val username: String
    ): Parcelable {
        val attributionUrl
            get() = "https://unsplash.com/$username?utm_source=ImageSearchApp&" +
                    "utm_medium=referral"
    }
}