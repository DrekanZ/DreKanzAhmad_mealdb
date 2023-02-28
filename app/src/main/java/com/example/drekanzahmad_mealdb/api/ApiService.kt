package com.example.drekanzahmad_mealdb.api

import com.example.drekanzahmad_mealdb.model.FoodDetailResponse
import com.example.drekanzahmad_mealdb.model.FoodResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
        @GET("filter.php")
        fun getFood(@Query("c") category: String
        ): Call<FoodResponse>

        @GET("lookup.php")
        fun getFoodDetail(@Query("i") id: String
        ): Call<FoodDetailResponse>
    }
