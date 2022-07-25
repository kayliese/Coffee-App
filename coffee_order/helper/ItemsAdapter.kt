package com.coffee_order.helper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.coffee_order.R
import com.coffee_order.data.Item
import com.coffee_order.databinding.ItemListLayoutBinding

class ItemsAdapter(private val items:ArrayList<Item>):RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder>(){
    private lateinit var onAddTapped: OnAddTapped
    private lateinit var onFavouriteTapped: OnFavouriteTapped
    private lateinit var onItemTapped: OnItemTapped
    interface OnItemTapped{fun onTap(position: Int)}
    interface OnFavouriteTapped{fun onTap(position: Int)}
    interface OnAddTapped{fun onTap(position: Int)}
    class ItemsViewHolder(mBinding:ItemListLayoutBinding):RecyclerView.ViewHolder(mBinding.root){
        val binding = mBinding
    }

    fun setOnItemTappedListener(onItemTapped: OnItemTapped){
        this.onItemTapped = onItemTapped
    }
    fun setOnFavouriteTappedListener(onFavouriteTapped: OnFavouriteTapped){
        this.onFavouriteTapped = onFavouriteTapped
    }
    fun setOnAddTappedListener(onAddTapped: OnAddTapped){
        this.onAddTapped = onAddTapped
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val binding:ItemListLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_list_layout,parent,false)
        return ItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val current = items[position]
        holder.binding.itemImage.setImageResource(current.photoPath)
        holder.binding.itemTitle.text = current.title
        holder.binding.itemDesc.text = current.subtitle
        holder.binding.price.text = String.format("$ %.1f",current.price)
        holder.binding.favouriteCheck.isChecked = current.favorite
        holder.itemView.setOnClickListener {
            onItemTapped.onTap(position)
        }
        holder.binding.addToCart.setOnClickListener {
            onAddTapped.onTap(position)
        }
        holder.binding.favouriteCheck.setOnClickListener {
            onFavouriteTapped.onTap(position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}