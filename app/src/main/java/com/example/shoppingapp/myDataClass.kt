package com.example.shoppingapp

data class myDataClass(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)