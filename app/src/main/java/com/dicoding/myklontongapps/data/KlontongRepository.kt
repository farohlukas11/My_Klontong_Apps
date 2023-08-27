package com.dicoding.myklontongapps.data

import com.dicoding.myklontongapps.model.FakeKlontongDataSource
import com.dicoding.myklontongapps.model.KlontongFood
import com.dicoding.myklontongapps.model.User
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class KlontongRepository {

    private val klontongFoods = mutableListOf<KlontongFood>()

    init {
        if (klontongFoods.isEmpty()) {
            FakeKlontongDataSource.dummyFoods.forEach { foods ->
                klontongFoods.add(foods)
            }
        }
    }

    fun getAllKlontongFoods(): Flow<List<KlontongFood>> = flowOf(klontongFoods)

    fun getKlontongFoodById(foodId: Long) = klontongFoods.first { food ->
        food.id == foodId
    }

    fun updateOrderKlontongFood(foodId: Long, newCount: Int): Flow<Boolean> {
        val index = klontongFoods.indexOfFirst { food ->
            food.id == foodId
        }

        val result = if (index >= 0) {
            klontongFoods[index].count = newCount
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderKlontongFoods(): Flow<List<KlontongFood>> =
        getAllKlontongFoods().map { orderKlontongFoods ->
            orderKlontongFoods.filter { food ->
                food.count > 0
            }
        }

    fun updateFavouriteKlontongFood(foodId: Long, newFavourite: Boolean): Flow<Boolean> {
        val index = klontongFoods.indexOfFirst { food ->
            food.id == foodId
        }

        val result = if (index >= 0) {
            klontongFoods[index].isFavourite = newFavourite
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getFavouriteKlontongFoods(): Flow<List<KlontongFood>> =
        getAllKlontongFoods().map { favouriteKlontongFoods ->
            favouriteKlontongFoods.filter { food ->
                food.isFavourite
            }
        }

    fun getFavouriteRatingFood(): Flow<List<KlontongFood>> =
        getAllKlontongFoods().map { favouriteRatingKlontongFood ->
            favouriteRatingKlontongFood.filter { food ->
                food.ratingFood != null && food.ratingFood >= 4.8
            }
        }

    fun updateTotalPrice(foodId: Long, newCount: Int): Flow<Boolean> {
        val index = klontongFoods.indexOfFirst { food ->
            food.id == foodId
        }

        val result = if (index >= 0) {
            klontongFoods[index].totalPrice = newCount * (klontongFoods[index].priceFood ?: 0)
            true
        } else {
            false
        }

        return flowOf(result)
    }

    fun getUser(): Flow<User> = flowOf(FakeKlontongDataSource.profileUser)

    companion object {
        @Volatile
        private var instance: KlontongRepository? = null

        fun getInstance(): KlontongRepository =
            instance ?: synchronized(this) {
                KlontongRepository().apply {
                    instance = this
                }
            }
    }
}