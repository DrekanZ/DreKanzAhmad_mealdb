package com.example.drekanzahmad_mealdb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.drekanzahmad_mealdb.localdatabase.MealsEntity
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.drekanzahmad_mealdb.localdatabase.MealsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FoodDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val database = Room.databaseBuilder(application, MealsDatabase::class.java, "meal").build()

    fun insertMeal(meal: MealsEntity) = viewModelScope.launch {
        database.mealsDetailsDao().insertMeal(meal)
    }

    fun deleteMealById(id: String) = viewModelScope.launch {
        database.mealsDetailsDao().deleteMealByID(id)
    }

    suspend fun checkFavorited(database: MealsDatabase, id: String): Boolean {
        return withContext(Dispatchers.IO) {
            val meal = database.mealsDetailsDao().getMealByID(id)
            meal != null
        }
    }

}