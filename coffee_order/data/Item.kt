package com.coffee_order.data

data class Item(
    val itemId: String,
    val photoPath: Int,
    val title: String,
    val subtitle: String,
    var quantity:Int,
    var favorite:Boolean,
    val desc: String,
    val price: Double,
    val totalRatings: Int,
    val averageRating: String,
    val itemSize: ItemSize,
    val itemType: ItemType,
    val extras: Extras?
)
