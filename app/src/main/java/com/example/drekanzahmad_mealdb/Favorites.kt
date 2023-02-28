package com.example.drekanzahmad_mealdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.drekanzahmad_mealdb.databinding.ActivityFavoritesBinding
import com.example.drekanzahmad_mealdb.localdatabase.MealsDatabase
import com.example.drekanzahmad_mealdb.localdatabase.MealsEntity
import com.example.drekanzahmad_mealdb.viewmodel.FoodDetailViewModel
import kotlinx.coroutines.launch

class Favorites : AppCompatActivity() {

    private lateinit var viewModel : FoodDetailViewModel
    private lateinit var binding : ActivityFavoritesBinding
//    private lateinit var meal : MealsEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(FoodDetailViewModel::class.java)

        refreshRecycler()

        binding.backButtonFav.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        refreshRecycler()
    }

    private fun refreshRecycler() {
        lifecycleScope.launch {
            val mealsList = viewModel.getMealList()
            val favAdapter = FavoritesAdapter()
            favAdapter.setData(mealsList)
            binding.rvFoodFavorite.apply {
                layoutManager = LinearLayoutManager(this@Favorites)
                setHasFixedSize(true)
                adapter = favAdapter
            }
        }
    }

    }
