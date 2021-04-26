package com.nikol412.artroom.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel

class SearchViewModel: ViewModel() {

    override fun onCleared() {
        viewModelScope.cancel()

        super.onCleared()
    }
}