package com.cptalpdeniz.android.orderapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.cptalpdeniz.android.orderapp.databinding.FragmentOrderBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class OrderFragment : Fragment() {
    private lateinit var binding: FragmentOrderBinding

    //fake data source
    private var foodItemMutableLists: MutableList<FoodItem> =
        FoodItemProvider.foodList.toMutableList()

    private lateinit var adapter: FoodsAdapter
    private val flag = Flag(
        isLunchButtonPressed = false,
        isDinnerButtonPressed = false,
        isSaveButtonPressed = false
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val datesArray = getDatesArray()
        binding.tvDate1.text = datesArray[0].substring(0,3)+"\n"+datesArray[0].substring(4,datesArray[0].length)
        binding.tvDate2.text = datesArray[1].substring(0,3)+"\n"+datesArray[1].substring(4,datesArray[1].length)
        binding.tvDate3.text = datesArray[2].substring(0,3)+"\n"+datesArray[2].substring(4,datesArray[2].length)
        binding.tvDate4.text = datesArray[3].substring(0,3)+"\n"+datesArray[3].substring(4,datesArray[3].length)
        binding.tvDate5.text = datesArray[4].substring(0,3)+"\n"+datesArray[4].substring(4,datesArray[4].length)
        initAdapter()
        viewPagerHandler()
        lunchButtonHandler()
        dinnerButtonHandler()
        saveButtonHandler()
    }

    private fun initAdapter() {
        adapter = FoodsAdapter(
            foodList = foodItemMutableLists,
            onClickListener = { foods -> onItemSelected(foods) })
        binding.viewpager.adapter = adapter
        binding.viewpager.isUserInputEnabled = false
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun onItemSelected(foods: FoodItem) {
        foods.isflipped = !foods.isflipped
        adapter.notifyDataSetChanged()
    }

    private fun viewPagerHandler() {
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
                sliderHandler(position)
            }
        })
    }

    private fun sliderHandler(position: Int) {
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

    private fun lunchButtonHandler() {
        binding.btnLunch.setOnClickListener {
            if (flag.isLunchButtonPressed) {
                binding.btnLunch.setBackgroundResource(R.drawable.button_transparent_bg)
                binding.btnLunch.setTextColor(Color.BLACK)
            } else {
                binding.btnLunch.setBackgroundResource(R.drawable.button_yellow_bg)
                binding.btnLunch.setTextColor(Color.WHITE)
            }
            flag.isLunchButtonPressed = !flag.isLunchButtonPressed
        }
    }

    private fun dinnerButtonHandler() {
        binding.btnDinner.setOnClickListener {
            if (flag.isDinnerButtonPressed) {
                binding.btnDinner.setBackgroundResource(R.drawable.button_transparent_bg)
                binding.btnDinner.setTextColor(Color.BLACK)
            } else {
                binding.btnDinner.setBackgroundResource(R.drawable.button_yellow_bg)
                binding.btnDinner.setTextColor(Color.WHITE)
            }
            flag.isDinnerButtonPressed = !flag.isDinnerButtonPressed
        }
    }

    private fun saveButtonHandler() {
        binding.btnSave.setOnClickListener {
            flag.isSaveButtonPressed = !flag.isSaveButtonPressed
            if (flag.isSaveButtonPressed) {
                binding.btnSave.setBackgroundResource(R.drawable.button_green_bg)
                binding.btnSave.text = "Unsave"
            } else {
                binding.btnSave.setBackgroundResource(R.drawable.button_yellow_bg)
                binding.btnSave.text = "Save"
            }
        }
    }

    private fun getDateInFormat(date: Date): String {
        val dateFormat = SimpleDateFormat("EEE d MMM", Locale.getDefault())
        return dateFormat.format(date)
    }

    private fun getDatesArray(): Array<String> {
        val datesArray = arrayOfNulls<String>(10)
        val calendar = Calendar.getInstance()

        // Get current date
        val currentDate = calendar.time
        datesArray[0] = getDateInFormat(currentDate)

        // Get next four dates
        for (i in 1 until 10) {
            calendar.add(Calendar.DATE, 1)
            datesArray[i] = getDateInFormat(calendar.time)
        }
        return datesArray.requireNoNulls()
    }

}