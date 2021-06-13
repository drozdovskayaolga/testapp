package com.example.testapplication.ui.map

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.R
import com.example.testapplication.model.Places

class PlaceListAdapter : ListAdapter<Places, PlaceListAdapter.MyViewHolder>(
    DIFF_CALLBACK
) {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val place: TextView = itemView.findViewById(R.id.tv_place)

        fun bind(item: Places) {
            place.text = "${item.id}. ${item.name}"
            itemView.setOnClickListener {
                Toast.makeText(it.context, "Click on ${place.text}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_in_list, parent, false)
        return MyViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = getItem(position)
        holder.bind(listItem)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Places>() {
            override fun areItemsTheSame(oldItem: Places, newItem: Places): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Places, newItem: Places): Boolean {
                return oldItem.lat == newItem.lat && oldItem.lng == newItem.lng
            }

        }
    }

}