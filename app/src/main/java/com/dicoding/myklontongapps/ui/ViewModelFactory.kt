package com.dicoding.myklontongapps.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.myklontongapps.data.KlontongRepository
import com.dicoding.myklontongapps.ui.screen.cart.CartViewModel
import com.dicoding.myklontongapps.ui.screen.detail.DetailViewModel
import com.dicoding.myklontongapps.ui.screen.favourite.FavouriteViewModel
import com.dicoding.myklontongapps.ui.screen.home.HomeViewModel
import com.dicoding.myklontongapps.ui.screen.profile.ProfileViewModel

class ViewModelFactory(private val repository: KlontongRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(FavouriteViewModel::class.java)) {
            return FavouriteViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}