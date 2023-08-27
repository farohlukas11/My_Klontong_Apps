package com.dicoding.myklontongapps.model

data class KlontongFood(
    val id: Long = 0,
    val listImageUrl: List<ImageFood>?,
    val nameFood: String?,
    val priceFood: Int?,
    val descriptionFood: String?,
    val ratingFood: Double?,
    val totalAvailable: Int?,
    var count: Int = 0,
    var isFavourite: Boolean = false,
    var totalPrice: Int = 0,
)