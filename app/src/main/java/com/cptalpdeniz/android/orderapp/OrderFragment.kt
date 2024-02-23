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
    private lateinit var adapter2: FoodAdapter2
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
        val datesArray = getDatesArrayWithoutWeekends()
        datesArray.forEach { date->
            Log.d("dates>>>",date)
        }
        binding.tvDate1.text =
            datesArray[0].substring(0, 3) + "\n" + datesArray[0].substring(4, datesArray[0].length)
        binding.tvDate2.text =
            datesArray[1].substring(0, 3) + "\n" + datesArray[1].substring(4, datesArray[1].length)
        binding.tvDate3.text =
            datesArray[2].substring(0, 3) + "\n" + datesArray[2].substring(4, datesArray[2].length)
        binding.tvDate4.text =
            datesArray[3].substring(0, 3) + "\n" + datesArray[3].substring(4, datesArray[3].length)
        binding.tvDate5.text =
            datesArray[4].substring(0, 3) + "\n" + datesArray[4].substring(4, datesArray[4].length)
        initAdapter()
        initAdapter2()
        viewPagerHandler()
        viewPagerHandler2()
        lunchButtonHandler()
        dinnerButtonHandler()
        saveButtonHandler()
    }

    private fun initAdapter() {
        adapter = FoodsAdapter(
            foodList = foodItemMutableLists,
            onClickListener = { foods -> onItemSelected(foods) })
        binding.viewpager.adapter = adapter
    }

    private fun initAdapter2() {
        adapter2 = FoodAdapter2(
            foodList = foodItemMutableLists,
            onClickListener = { foods -> onItemSelected2(foods) })
        binding.viewpager2.adapter = adapter2
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun onItemSelected(foods: FoodItem) {
        foods.isflipped = !foods.isflipped
        adapter.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun onItemSelected2(foods: FoodItem) {
        foods.isflipped = !foods.isflipped
        adapter2.notifyDataSetChanged()
    }

    private fun viewPagerHandler() {
        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler(position)
            }
        })
    }

    private fun viewPagerHandler2() {
        binding.viewpager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler2(position)
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

    private fun sliderHandler2(position: Int) {
        if (position == 0) {
            Glide.with(binding.leftIndicator2.context)
                .load(R.drawable.baseline_arrow_left_black_24).into(binding.leftIndicator2)
        }
        if (position == foodItemMutableLists.size - 1) {
            Glide.with(binding.rightIndicator2.context)
                .load(R.drawable.baseline_arrow_right_black_24).into(binding.rightIndicator2)
        } else if (position > 0 && position < foodItemMutableLists.size - 1) {
            Glide.with(binding.leftIndicator2.context)
                .load(R.drawable.baseline_arrow_left_yellow_24).into(binding.leftIndicator2)
            Glide.with(binding.rightIndicator2.context)
                .load(R.drawable.baseline_arrow_right_yellow_24)
                .into(binding.rightIndicator2)
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

    private fun getDatesArrayWithoutWeekends(): Array<String> {
        val datesArray = arrayOfNulls<String>(14)
        val calendar = Calendar.getInstance()
        // Get current date
        val currentDate = calendar.time
        datesArray[0] = getDateInFormat(currentDate)
        // Get next fourteen dates excluding weekends
        var count = 1
        while (count < 14) {
            calendar.add(Calendar.DATE, 1)
            val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
            if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
                datesArray[count] = getDateInFormat(calendar.time)
                count++
            }
        }
        return datesArray.requireNoNulls()
    }

}