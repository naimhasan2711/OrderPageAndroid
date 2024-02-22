package com.cptalpdeniz.android.orderapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class FoodsAdapter(
    private var foodList: List<FoodItem>,
    private val onClickListener: (FoodItem) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == 1) {
            FoodViewHolder(
                layoutInflater.inflate(
                    R.layout.food_item_layout,
                    parent,
                    false
                )
            )
        } else {
            FlipViewHolder(
                layoutInflater.inflate(
                    R.layout.food_item_flip_layout,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            1 -> {
                holder as FoodViewHolder
                holder.render(foodList[position], onClickListener )
            }

            2 -> {
                holder as FlipViewHolder
                holder.render(foodList[position], onClickListener)
            }

            else -> holder as FoodViewHolder
        }
    }
    override fun getItemViewType(position: Int): Int {
        return if (foodList[position].isflipped) {
            1
        } else 2
    }
    override fun getItemCount(): Int {
        return foodList.size
    }

}