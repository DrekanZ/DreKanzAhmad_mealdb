package com.example.drekanzahmad_mealdb

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.drekanzahmad_mealdb.databinding.ItemSeafoodMainBinding
import com.example.drekanzahmad_mealdb.localdatabase.MealsEntity

class FavoritesAdapter() : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    private var dataFood :List<MealsEntity> = listOf()


    inner class FavoritesViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemSeafoodMainBinding.bind(view)

        fun bind(meal: MealsEntity) {
            binding.apply {
                foodName.text=meal.strMeal

                Glide.with(foodImage)
                    .load(meal.strMealThumb)
                    .into(foodImage)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, FoodDetail::class.java)
                    intent.putExtra("id", meal.idMeal.toString())
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_seafood_main,parent,false)
        return FavoritesViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        return holder.bind(dataFood[position])
    }

    override fun getItemCount(): Int {
        return dataFood.size
    }

    fun setData(data : List<MealsEntity>) {
        dataFood = data
        notifyDataSetChanged()
    }
}
