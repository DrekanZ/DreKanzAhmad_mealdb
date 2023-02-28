package com.example.drekanzahmad_mealdb.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class FoodDetailResponse(

	@field:SerializedName("meals")
	val mealsdetail: List<MealsDetailsItem?>? = null
) : Parcelable

@Parcelize
data class MealsDetailsItem(

	@field:SerializedName("idMeal")
	val idMeal: String? = null,

	@field:SerializedName("strArea")
	val strArea: String? = null,

	@field:SerializedName("strInstructions")
	val strInstructions: String? = null,

	@field:SerializedName("strMealThumb")
	val strMealThumb: String? = null,

	@field:SerializedName("strMeal")
	val strMeal: String? = null,

) : Parcelable
