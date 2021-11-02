package com.udacity.shoestore.data

import com.udacity.shoestore.data.models.Shoe

/**
 * Object for mock data
 */
object MockData {
    val listOfShoe: List<Shoe> = listOf(
        Shoe(
            name = "Nike Air Max",
            size = 41.0,
            company = "Nike",
            description = "Lightweight, neutral cushioned shoe"
        ),
        Shoe(
            name = "Adidas Ultraboost",
            size = 44.0,
            company = "Adidas",
            description = "Running shoe"
        ),
        Shoe(name = "Air Force", size = 43.0, company = "Nike", description = "Athletic shoes"),
        Shoe(
            name = "Adidas Originals",
            size = 40.0,
            company = "Adidas",
            description = "Casual and sportswear shoe"
        ),
        Shoe(
            name = "New Balance Captain",
            size = 45.0,
            company = "New Balance",
            description = "Shoe with multi-colored paneling"
        ),
        Shoe(
            name = "New Balance Niobium",
            size = 45.5,
            company = "New Balance",
            description = "Outdoor/Trekking shoe"
        )
    )
}
