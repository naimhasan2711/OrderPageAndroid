package com.cptalpdeniz.android.orderapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cptalpdeniz.android.orderapp.databinding.FoodItemFlipLayoutBinding
import com.cptalpdeniz.android.orderapp.databinding.FoodItemLayoutBinding

/*
Viewholder class for FoodAdapter class
 */
class FoodViewHolder(holder: View) : RecyclerView.ViewHolder(holder) {
    private var binding = FoodItemLayoutBinding.bind(holder)
    fun render(
        foodItem: FoodItem,
        onClickListener: (FoodItem) -> Unit
    ) {
        initValues(foodItem)
        binding.fullDetailsText.setOnClickListener { onClickListener(foodItem) }
    }

    //inflate item of the recyclerview
    private fun initValues(foodModels: FoodItem) {
        Glide.with(binding.imageView.context).load(foodModels.image).into(binding.imageView)
        binding.titleText.text = foodModels.foodName
        binding.descriptionText.text = foodModels.details
        Glide.with(binding.ivFlag.context).load(R.drawable.symbol).into(binding.ivSymbol)
        Glide.with(binding.ivFlag.context).load(foodModels.symbol).into(binding.ivFlag)
    }

}

class FlipViewHolder(holder: View) : RecyclerView.ViewHolder(holder) {
    private var binding = FoodItemFlipLayoutBinding.bind(holder)
    fun render(
        foodItem: FoodItem,
        onClickListener: (FoodItem) -> Unit
    ) {
        initValues(foodItem)
        binding.tvGoBack.setOnClickListener {
            onClickListener(foodItem)
        }
    }

    //inflate item of the recyclerview
    private fun initValues(foodModels: FoodItem) {
        binding.tvIngredient.text = foodModels.ingredient
    }

}