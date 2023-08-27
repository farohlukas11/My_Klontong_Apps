package com.dicoding.myklontongapps.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.myklontongapps.data.KlontongRepository
import com.dicoding.myklontongapps.model.User
import com.dicoding.myklontongapps.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: KlontongRepository) : ViewModel() {

    private val _uiStateUser: MutableStateFlow<UiState<User>> = MutableStateFlow(UiState.Loading)
    val uiStateUser: StateFlow<UiState<User>>
        get() = _uiStateUser

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