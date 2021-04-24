package com.nikol412.artroom.ui.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikol412.artroom.data.ArtAPI
import com.nikol412.artroom.data.response.ArtworkObject
import com.nikol412.artroom.data.response.toArtworkObjectList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OverviewViewModel : ViewModel() {
    private val artworks = MutableLiveData(mutableListOf<ArtworkObject>())
    val artworksLD: LiveData<MutableList<ArtworkObject>> = artworks

    val isLoading = MutableLiveData(false)

    var currentPage = 1
    var totalPages = -1

    init {
        loadNextPage()
    }

    fun loadNextPage() {
        viewModelScope.launch {
            if (totalPages == -1 || totalPages > currentPage) {
                isLoading.value = true
                val artworksPage = withContext(Dispatchers.IO) {
                    ArtAPI.getApi().getArtsPage(currentPage + 1)
                }
                isLoading.value = false

                currentPage = artworksPage.pagination.currentPage
                totalPages = artworksPage.pagination.totalPages

                val newList = artworks.value
                val resultFilteredList = artworksPage.toArtworkObjectList()
                    .filter { item ->
                        item.imageId.isNullOrBlank().not()
                    }
                newList?.addAll(resultFilteredList)
                artworks.value = newList
            }
        }
    }
}