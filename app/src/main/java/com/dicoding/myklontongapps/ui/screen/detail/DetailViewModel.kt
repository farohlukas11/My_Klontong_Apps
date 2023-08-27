package com.dicoding.myklontongapps.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.myklontongapps.data.KlontongRepository
import com.dicoding.myklontongapps.model.KlontongFood
import com.dicoding.myklontongapps.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: KlontongRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<KlontongFood>> =
        MutableStateFlow((UiState.Loading))
    val uiState: StateFlow<UiState<KlontongFood>>
        get() = _uiState

    private val _uiStateRandom: MutableStateFlow<UiState<List<KlontongFood>>> =
        MutableStateFlow(UiState.Loading)
    val uiStateRandom: StateFlow<UiState<List<KlontongFood>>>
        get() = _uiStateRandom

    fun getKlontongFoodById(productId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getKlontongFoodById(productId))
        }
    }

    fun updateOrderKlontongFood(productId: Long, count: Int) {
        viewModelScope.launch {
            repository.apply {
                updateOrderKlontongFood(
                    foodId = productId,
                    newCount = count
                )
                updateTotalPrice(
                    foodId = productId,
                    newCount = count
                )
            }
        }
    }

    fun updateFavouriteKlontongFood(
        productId: Long,
        favourite: Boolean
    ) {
        viewModelScope.launch {
            repository.updateFavouriteKlontongFood(
                foodId = productId,
                newFavourite = favourite
            )
        }
    }

    fun getAllKlontongFoods() {
        viewModelScope.launch {
            repository.getAllKlontongFoods()
                .catch {
                    _uiStateRandom.value = UiState.Error(it.message.toString())
                }
                .collect { klontongFoods ->
                    _uiStateRandom.value = UiState.Success(klontongFoods.shuffled())
                }
        }
    }
}