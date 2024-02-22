package com.cptalpdeniz.android.orderapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.cptalpdeniz.android.orderapp.databinding.FragmentOrderBinding


class OrderFragment : Fragment() {
    private lateinit var binding: FragmentOrderBinding

    //fake data source
    private var foodItemMutableLists: MutableList<FoodItem> =
        FoodItemProvider.foodList.toMutableList()

    private lateinit var adapter: FoodsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        binding.leftIndicator.setOnClickListener {
            var tab = binding.viewpager.currentItem
            if (tab > 0) {
                tab--;
                binding.viewpager.currentItem = tab
                binding.leftIndicator.setBackgroundResource(R.drawable.baseline_arrow_left_yellow_24)
            } else if (tab == 0) {
                binding.viewpager.currentItem = 0
            }
        }

        binding.rightIndicator.setOnClickListener {
            var tab = binding.viewpager.currentItem
            tab++
            binding.viewpager.currentItem = tab
        }

        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0) {
                    Glide.with(binding.leftIndicator.context)
                        .load(R.drawable.baseline_arrow_left_black_24).into(binding.leftIndicator)
                }
                if (position == foodItemMutableLists.size - 1) {
                    Glide.with(binding.rightIndicator.context)
                        .load(R.drawable.baseline_arrow_right_black_24).into(binding.rightIndicator)
                } else if (position > 0 && position < foodItemMutableLists.size - 1) {
                    Glide.with(binding.leftIndicator.context)
                        .load(R.drawable.baseline_arrow_left_yellow_24).into(binding.leftIndicator)
                    Glide.with(binding.rightIndicator.context)
                        .load(R.drawable.baseline_arrow_right_yellow_24)
                        .into(binding.rightIndicator)
                }
            }
        })
    }

    private fun initAdapter() {
        adapter = FoodsAdapter(
            foodList = foodItemMutableLists,
            onClickListener = { foods -> onItemSelected(foods) })
        binding.viewpager.adapter = adapter
        binding.viewpager.isUserInputEnabled = false
    }

    private fun onItemSelected(foods: FoodItem) {
        foods.isflipped = !foods.isflipped
        adapter.notifyDataSetChanged()
    }

}