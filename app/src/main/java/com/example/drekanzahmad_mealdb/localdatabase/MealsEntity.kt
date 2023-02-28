package com.example.drekanzahmad_mealdb.localdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "seafood_meal")
data class MealsEntity(
    @PrimaryKey val idMeal: String,
    val strArea: String?,
    val strInstructions: String?,
    val strMealThumb: String?,
    val strMeal: String?
)