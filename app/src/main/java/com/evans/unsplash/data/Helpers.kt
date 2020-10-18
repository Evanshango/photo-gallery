package com.evans.unsplash.data

import com.evans.unsplash.BuildConfig
import java.text.SimpleDateFormat
import java.util.*

class Helpers {

    companion object{
        const val UNSPLASH_STARTING_PAGE_INDEX = 1
        const val BASE_URL = "https://api.unsplash.com/"
        const val ACCESS_KEY = BuildConfig.UNSPLASH_KEY
        const val CURRENT_QUERY = "current_query"
        const val DEFAULT_QUERY = "cars"
        private const val SHORT_DATE = "dd MMM, yyyy"

        fun getFormattedDate(publishedAt: String): String? {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
            val date = sdf.parse(publishedAt)
            val dateFormat = SimpleDateFormat(SHORT_DATE, Locale.ENGLISH)
            return dateFormat.format(date!!)
        }
    }
}