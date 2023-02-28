package com.example.drekanzahmad_mealdb

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.drekanzahmad_mealdb.databinding.ItemSeafoodMainBinding
import com.example.drekanzahmad_mealdb.model.MealsItem

class FoodAdapter() : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    private var dataFood :List<MealsItem> = listOf()

    inner class FoodViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemSeafoodMainBinding.bind(view)

        fun bind(meal: MealsItem) {
            binding.apply {
                foodName.text=meal.strMeal

                Glide.with(foodImage)
                    .load(meal.strMealThumb)
                    .into(foodImage)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, FoodDetail::class.java)
                    intent.putExtra(FoodDetail.EXTRA_FOOD, meal)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_seafood_main,parent,false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        return holder.bind(dataFood[position])
    }

    override fun getItemCount(): Int {
        return dataFood.size
    }

    fun setData(data : List<MealsItem>) {
        dataFood = data
        notifyDataSetChanged()
    }
}