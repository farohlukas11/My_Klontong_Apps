package com.dicoding.myklontongapps.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.myklontongapps.data.KlontongRepository
import com.dicoding.myklontongapps.model.KlontongFood
import com.dicoding.myklontongapps.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CartViewModel(private val repository: KlontongRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<KlontongFood>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<KlontongFood>>>
        get() = _uiState

    fun getAddedOrderKlontongFoods() {
        viewModelScope.launch {
            repository.getAddedOrderKlontongFoods()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderFoods ->
                    _uiState.value = UiState.Success(orderFoods)
                }
        }
    }

    fun updateOrderKlontongFood(productId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderKlontongFood(
                foodId = productId,
                newCount = count
            )

            _uiState.value
        }
    }
}