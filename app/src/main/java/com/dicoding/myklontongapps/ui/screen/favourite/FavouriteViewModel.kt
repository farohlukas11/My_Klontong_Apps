package com.dicoding.myklontongapps.ui.screen.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.myklontongapps.data.KlontongRepository
import com.dicoding.myklontongapps.model.KlontongFood
import com.dicoding.myklontongapps.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavouriteViewModel(private val repository: KlontongRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<KlontongFood>>> =
        MutableStateFlow((UiState.Loading))
    val uiState: StateFlow<UiState<List<KlontongFood>>>
        get() = _uiState

    fun getFavouriteKlontongFoods() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getFavouriteKlontongFoods()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { favouriteFoods ->
                    _uiState.value = UiState.Success(favouriteFoods)
                }
        }
    }
}