package com.cptalpdeniz.android.orderapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.cptalpdeniz.android.orderapp.databinding.FragmentOrderBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class OrderFragment : Fragment(), DateAdapter.OnItemClickListener {
    private lateinit var binding: FragmentOrderBinding

    //fake data source
    private var foodItemMutableLists: MutableList<FoodItem> =
        FoodItemProvider.foodList.toMutableList()

    private lateinit var adapter: FoodsAdapter
    private lateinit var adapter2: FoodAdapter2
    private lateinit var dateAdapter: DateAdapter
    private val flag = Flag(
        isLunchButtonPressed = false,
        isDinnerButtonPressed = false,
        isSaveButtonPressed = false
    )
    private var dateList: List<DateItem>? = null
    private var selectedPosition: Int? = null

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
        dateList = listOf<DateItem>(
            DateItem(datesArray[0], false),
            DateItem(datesArray[1], false),
            DateItem(datesArray[2], false),
            DateItem(datesArray[3], false),
            DateItem(datesArray[4], false),
            DateItem(datesArray[5], false),
            DateItem(datesArray[6], false),
            DateItem(datesArray[7], false),
            DateItem(datesArray[8], false),
            DateItem(datesArray[9], false),
            DateItem(datesArray[10], false),
            DateItem(datesArray[11], false),
            DateItem(datesArray[12], false),
            DateItem(datesArray[13], false)
        )
        datesArray.forEach { date ->
            Log.d("dates>>>", date)
        }
        initDateAdapter()
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

    private fun initDateAdapter() {
        dateAdapter = dateList?.let { it -> DateAdapter(arrList = it, this) }!!
        binding.dateRecyclerview.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.dateRecyclerview.adapter = dateAdapter
    }

    private fun onItemSelected3(it: DateItem) {
        Toast.makeText(requireContext(), it.dateString, Toast.LENGTH_SHORT).show()
        it.isSaved = !it.isSaved
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
            flag.isLunchButtonPressed = !flag.isLunchButtonPressed
            if (!flag.isLunchButtonPressed) {
                binding.btnLunch.setBackgroundResource(R.drawable.button_transparent_bg)
                binding.btnLunch.setTextColor(Color.BLACK)
                flag.isDinnerButtonPressed = true
                binding.btnDinner.setBackgroundResource(R.drawable.button_yellow_bg)
                binding.btnDinner.setTextColor(Color.WHITE)

            } else {
                binding.btnLunch.setBackgroundResource(R.drawable.button_yellow_bg)
                binding.btnLunch.setTextColor(Color.WHITE)
                flag.isDinnerButtonPressed = false
                binding.btnDinner.setBackgroundResource(R.drawable.button_transparent_bg)
                binding.btnDinner.setTextColor(Color.BLACK)
            }
        }
    }

    private fun dinnerButtonHandler() {
        binding.btnDinner.setOnClickListener {
            flag.isDinnerButtonPressed = !flag.isDinnerButtonPressed
            if (!flag.isDinnerButtonPressed) {
                binding.btnDinner.setBackgroundResource(R.drawable.button_transparent_bg)
                binding.btnDinner.setTextColor(Color.BLACK)
                flag.isLunchButtonPressed = true
                binding.btnLunch.setBackgroundResource(R.drawable.button_yellow_bg)
                binding.btnLunch.setTextColor(Color.WHITE)
            } else {
                binding.btnDinner.setBackgroundResource(R.drawable.button_yellow_bg)
                binding.btnDinner.setTextColor(Color.WHITE)
                flag.isLunchButtonPressed = false
                binding.btnLunch.setBackgroundResource(R.drawable.button_transparent_bg)
                binding.btnLunch.setTextColor(Color.BLACK)

            }
        }
    }

    private fun saveButtonHandler() {
        binding.btnSave.setOnClickListener {
            if (selectedPosition != null) {
                toggleCheckMark(selectedPosition!!);
            }
            if (dateList?.get(selectedPosition!!)?.isSaved == true) {
                binding.btnSave.setBackgroundResource(R.drawable.button_green_bg)
                binding.btnSave.text = "Unsave"
            } else if (dateList?.get(selectedPosition!!)?.isSaved == false) {
                binding.btnSave.setBackgroundResource(R.drawable.button_yellow_bg)
                binding.btnSave.text = "Save"
            }
            Log.d(
                "Button Value: >>>>>",
                "${flag.isLunchButtonPressed.toString()},${flag.isDinnerButtonPressed.toString()}"
            )
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

    override fun onItemClick(position: Int) {
        selectedPosition = position
        //Toast.makeText(requireContext(), "$position", Toast.LENGTH_SHORT).show()
        if (dateList?.get(position)?.isSaved == true) {
            binding.btnSave.setBackgroundResource(R.drawable.button_green_bg)
            binding.btnSave.text = "Unsave"
        } else if (dateList?.get(position)?.isSaved == false) {
            binding.btnSave.setBackgroundResource(R.drawable.button_yellow_bg)
            binding.btnSave.text = "Save"
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun toggleCheckMark(position: Int) {
        dateList?.get(position)?.isSaved = !dateList?.get(position)?.isSaved!!
        binding.dateRecyclerview.adapter?.notifyDataSetChanged()
    }

}