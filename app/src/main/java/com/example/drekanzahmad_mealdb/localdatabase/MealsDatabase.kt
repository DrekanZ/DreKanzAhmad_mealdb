package com.example.drekanzahmad_mealdb.localdatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MealsEntity::class], version = 1, exportSchema = false)
abstract class MealsDatabase : RoomDatabase() {
    abstract fun mealsDetailsDao(): FavoriteMealDao
}