package com.example.drekanzahmad_mealdb.api

import com.example.drekanzahmad_mealdb.model.FoodResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
        @GET("filter.php")
        fun getFood(@Query("c") category: String
        ): Call<FoodResponse>
    }
