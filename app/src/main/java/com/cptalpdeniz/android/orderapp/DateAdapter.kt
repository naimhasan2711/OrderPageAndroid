package com.cptalpdeniz.android.orderapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class DateAdapter(private val arrList: List<DateItem>,private val listener: OnItemClickListener) :
    RecyclerView.Adapter<DateAdapter.DateViewHolder>() {

    private var selectedItemPosition: Int? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.date_list_item, parent, false)
        return DateViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        val currentDate = arrList[position]
        val dateTitle = currentDate.dateString
        holder.itemView.setOnClickListener {
            selectedItemPosition = position
            listener.onItemClick(position)
            notifyDataSetChanged()
        }
        if (selectedItemPosition == position) {
            holder.layout.setBackgroundResource(R.drawable.button_yellow_bg)
        } else {
            holder.layout.setBackgroundResource(R.drawable.button_transparent_bg)
        }
        holder.dateTitle.text =
            dateTitle.substring(0, 3) + "\n" + dateTitle.substring(4, dateTitle.length)
        if (!currentDate.isSaved) {
            holder.iconImage.visibility = View.GONE
        } else {
            holder.iconImage.visibility = View.VISIBLE
        }

    }

    class DateViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dateTitle: TextView = itemView.findViewById(R.id.tv_date)
        val iconImage: ImageView = itemView.findViewById(R.id.iv_selected_icon)
        val layout: ConstraintLayout = itemView.findViewById(R.id.constraintLayout)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}