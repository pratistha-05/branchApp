package com.example.branchdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.branchdemo.R
import com.example.branchdemo.data.model.Item

class ItemAdapter(private val itemList: List<Item>, private val itemClickListener: (Item) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

  class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val itemName: TextView = itemView.findViewById(R.id.item_name)
    val itemDescription: TextView = itemView.findViewById(R.id.item_description)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
    return ItemViewHolder(itemView)
  }

  override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
    val item = itemList[position]
    holder.itemName.text = item.name
    holder.itemDescription.text = item.description
    holder.itemView.setOnClickListener {
      itemClickListener(item)
    }
  }

  override fun getItemCount(): Int = itemList.size
}
