package com.cptalpdeniz.android.orderapp.models

/*
data class for recycler view items
 */
data class FoodItem(
    val foodName: String,
    val origin: String,
    val symbol: Int,
    val details: String,
    val image: Int,
    val ingredient:String,
    var isflipped:Boolean = true
)
