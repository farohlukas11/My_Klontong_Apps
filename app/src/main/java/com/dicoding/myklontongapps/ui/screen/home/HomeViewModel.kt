package com.dicoding.myklontongapps.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.myklontongapps.data.KlontongRepository
import com.dicoding.myklontongapps.model.KlontongFood
import com.dicoding.myklontongapps.model.User
import com.dicoding.myklontongapps.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: KlontongRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<KlontongFood>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<KlontongFood>>>
        get() = _uiState

    private val _uiStateRating: MutableStateFlow<UiState<List<KlontongFood>>> =
        MutableStateFlow(UiState.Loading)
    val uiStateRating: StateFlow<UiState<List<KlontongFood>>>
        get() = _uiStateRating

    private val _uiStateUser: MutableStateFlow<UiState<User>> = MutableStateFlow(UiState.Loading)
    val uiStateUser: StateFlow<UiState<User>>
        get() = _uiStateUser

    fun getAllKlontongFoods() {
        viewModelScope.launch {
            repository.getAllKlontongFoods()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { klontongFoods ->
                    _uiState.value = UiState.Success(klontongFoods)
                }
        }
    }

    fun getFavouriteRatingFood() {
        viewModelScope.launch {
            repository.getFavouriteRatingFood()
                .catch {
                    _uiStateRating.value = UiState.Error(it.message.toString())
                }
                .collect { ratingFoods ->
                    _uiStateRating.value = UiState.Success(ratingFoods)
                }
        }
    }

    fun getUser() {
        viewModelScope.launch {
            repository.getUser()
                .catch {
                    _uiStateUser.value = UiState.Error(it.message.toString())
                }
                .collect {
                    _uiStateUser.value = UiState.Success(it)
                }
        }
    }
}