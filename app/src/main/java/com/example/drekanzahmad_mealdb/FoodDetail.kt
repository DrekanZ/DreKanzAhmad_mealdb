package com.example.drekanzahmad_mealdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.drekanzahmad_mealdb.api.ApiConfig
import com.example.drekanzahmad_mealdb.databinding.ActivityFoodDetailBinding
import com.example.drekanzahmad_mealdb.localdatabase.MealsDatabase
import com.example.drekanzahmad_mealdb.localdatabase.MealsEntity
import com.example.drekanzahmad_mealdb.model.FoodDetailResponse
import com.example.drekanzahmad_mealdb.viewmodel.FoodDetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodDetail : AppCompatActivity() {

    private lateinit var binding : ActivityFoodDetailBinding
    private lateinit var idMeal : String
    private lateinit var viewModel: FoodDetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val database =
            Room.databaseBuilder(applicationContext, MealsDatabase::class.java, "meal").build()
        changeStar(database)
        var imageThumb = ""
        idMeal = intent.getStringExtra("id").toString()

        ApiConfig.getService().getFoodDetail(idMeal)
            .enqueue(object : Callback<FoodDetailResponse> {
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

                        imageThumb = dataMeal?.strMealThumb.toString()

                        Glide.with(binding.imageDetail)
                            .load(dataMeal?.strMealThumb)
                            .into(binding.imageDetail)
                    }
                }

                override fun onFailure(call: Call<FoodDetailResponse>, t: Throwable) {
                }

            })


        viewModel = ViewModelProvider(this).get(FoodDetailViewModel::class.java)

        binding.favoriteButton.setOnClickListener {

            lifecycleScope.launch(Dispatchers.Main) {
                val isFavorited = viewModel.checkFavorited(database, idMeal)
                if (isFavorited) {
                    viewModel.deleteMealById(idMeal)
                    Glide.with(binding.favoriteButton)
                        .load(R.drawable.favorite)
                        .into(binding.favoriteButton)
//                    changeStar(database)
                } else {
                    val meal = MealsEntity(
                        idMeal = idMeal,
                        strArea = binding.areaDetail.text.toString(),
                        strInstructions = binding.instructionDetail.text.toString(),
                        strMealThumb = imageThumb,
                        strMeal = binding.foodTitle.text.toString()
                    )
                    viewModel.insertMeal(meal)
                    Glide.with(binding.favoriteButton)
                        .load(R.drawable.favoritex)
                        .into(binding.favoriteButton)
//                    changeStar(database)

                }
            }



        }

        binding.backButtonDetails.setOnClickListener {
            onBackPressed()
        }

    }



    fun changeStar(database : MealsDatabase) {
        lifecycleScope.launch(Dispatchers.Main) {
            val isFavorited = viewModel.checkFavorited(database, idMeal)
            if (isFavorited) {
                Glide.with(binding.favoriteButton)
                    .load(R.drawable.favoritex)
                    .into(binding.favoriteButton)
            } else {
                Glide.with(binding.favoriteButton)
                    .load(R.drawable.favorite)
                    .into(binding.favoriteButton)
            }
        }
    }
}