package com.evans.unsplash.ui.gallery

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.evans.unsplash.data.Helpers.Companion.CURRENT_QUERY
import com.evans.unsplash.data.Helpers.Companion.DEFAULT_QUERY
import com.evans.unsplash.data.PhotoRepository

class GalleryViewModel @ViewModelInject constructor(
    private val repository: PhotoRepository,
    @Assisted state: SavedStateHandle
) : ViewModel() {

    private val currentQuery = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)

    val photos = currentQuery.switchMap { queryString ->
        repository.getSearchResult(queryString).cachedIn(viewModelScope)
    }

    fun searchPhotos(query: String) {
        currentQuery.value = query
    }
}