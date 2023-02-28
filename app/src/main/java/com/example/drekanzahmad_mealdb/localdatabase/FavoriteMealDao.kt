package com.example.drekanzahmad_mealdb.localdatabase

import androidx.room.*

@Dao
interface FavoriteMealDao {
    @Query("SELECT * FROM seafood_meal")
    fun getAllMeals(): List<MealsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: MealsEntity)

    @Query("SELECT * FROM seafood_meal WHERE idMeal = :id")
    suspend fun getMealByID(id: String): MealsEntity

    @Query("DELETE FROM seafood_meal WHERE idMeal = :id")
    suspend fun deleteMealByID(id: String)

//    @Delete
//    suspend fun deleteMeal(meal: MealsEntity)
}