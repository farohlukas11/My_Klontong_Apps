package com.dicoding.myklontongapps.di

import com.dicoding.myklontongapps.data.KlontongRepository

object Injection {
    fun provideRepository(): KlontongRepository {
        return KlontongRepository.getInstance()
    }
}