package com.example.drekanzahmad_mealdb.localdatabase

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "seafood_meal")
data class MealsEntity(
    @PrimaryKey val idMeal: String,
    val strArea: String?,
    val strInstructions: String?,
    val strMealThumb: String?,
    val strMeal: String?
) : Parcelable