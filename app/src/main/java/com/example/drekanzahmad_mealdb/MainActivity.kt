package com.example.drekanzahmad_mealdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drekanzahmad_mealdb.api.ApiConfig
import com.example.drekanzahmad_mealdb.databinding.ActivityMainBinding
import com.example.drekanzahmad_mealdb.model.FoodResponse
import com.example.drekanzahmad_mealdb.model.MealsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ApiConfig.getService().getFood("Seafood").enqueue(object: Callback<FoodResponse> {
            override fun onResponse(call: Call<FoodResponse>, response: Response<FoodResponse>) {
                if (response.isSuccessful) {
                    val responseFood =response.body()
                    val dataMeal =responseFood?.meals
                    val foodAdapter = FoodAdapter()
                    foodAdapter.setData(dataMeal as List<MealsItem>)
                    binding.rvFood.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        setHasFixedSize(true)
                        adapter = foodAdapter
                    }
                }
                else
                {
                    Log.d("response",response.errorBody().toString())
                }
            }
            override fun onFailure(call: Call<FoodResponse>, t: Throwable) {
                Log.d("response",t.message + " " + t.cause)

            }


        })
    }
}