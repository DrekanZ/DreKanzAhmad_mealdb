package com.example.drekanzahmad_mealdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.example.drekanzahmad_mealdb.api.ApiConfig
import com.example.drekanzahmad_mealdb.databinding.ActivityFoodDetailBinding
import com.example.drekanzahmad_mealdb.model.FoodDetailResponse
import com.example.drekanzahmad_mealdb.model.MealsDetailsItem
import com.example.drekanzahmad_mealdb.model.MealsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodDetail : AppCompatActivity() {

    private lateinit var binding : ActivityFoodDetailBinding
    private lateinit var meal : MealsItem

    companion object{
        const val EXTRA_FOOD = "extra_food"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        meal = intent.getParcelableExtra(EXTRA_FOOD)!!

        ApiConfig.getService().getFoodDetail(meal.idMeal.toString()).enqueue(object: Callback<FoodDetailResponse>
        {
            override fun onResponse(
                call: Call<FoodDetailResponse>,
                response: Response<FoodDetailResponse>
            ) {
                if (response.isSuccessful) {
                    val mealResponse = response.body()
                    val dataMeal = mealResponse?.mealsdetail?.get(0)
                    binding.areaDetail.text = dataMeal?.strArea
                    binding.foodTitle.text = dataMeal?.strMeal
                    binding.instructionDetail.text = dataMeal?.strInstructions

                    Glide.with(binding.imageDetail)
                        .load(dataMeal?.strMealThumb)
                        .into(binding.imageDetail)
                }
            }

            override fun onFailure(call: Call<FoodDetailResponse>, t: Throwable) {
            }

        })


    }
}