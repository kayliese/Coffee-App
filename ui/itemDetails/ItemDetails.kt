package com.coffee_order.ui.itemDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.coffee_order.R
import com.coffee_order.data.Item
import com.coffee_order.databinding.ActivityItemDetailsBinding
import com.google.gson.Gson

class ItemDetails : AppCompatActivity() {
    private lateinit var currentItem: Item
    private lateinit var binding: ActivityItemDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_item_details)
        currentItem = getObject("current",Item::class.java)
        binding.item = currentItem
        binding.image.setImageResource(currentItem.photoPath)
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
        //Handling click on remove button of quantity
        binding.qRem.setOnClickListener {
            if(currentItem.quantity<=1){
                currentItem.quantity = 1
            }else{
                currentItem.quantity--
            }
            binding.item = currentItem
        }
        //Handling click on add function of quantity
        binding.qAdd.setOnClickListener {
            currentItem.quantity++
            binding.item = currentItem
        }
        //Handling click on remove button of milk
        binding.milkRem.setOnClickListener {
            if(currentItem.extras?.milk!!<=1){
                currentItem.extras?.milk = 1
            }else{
                currentItem.extras?.milk!!-1
            }
            binding.item = currentItem
        }
        //Handling click on add function of milk
        binding.milkAdd.setOnClickListener {
            currentItem.quantity++
            binding.item = currentItem
        }

        //Handling click on remove button of sugar
        binding.sugarRem.setOnClickListener {
            if(currentItem.extras?.sugar!!<=1){
                currentItem.extras?.sugar = 1
            }else{
                currentItem.extras?.sugar!!-1
            }
            binding.item = currentItem
        }
        //Handling click on add function of sugar
        binding.milkAdd.setOnClickListener {
            currentItem.extras?.sugar!!+1
            binding.item = currentItem
        }
    }

    private fun getPreferenceString(key: String?): String? {
        val preferences = getSharedPreferences("app_prefs", MODE_PRIVATE)
        return preferences!!.getString(key, "nullString")
    }
    private fun <T> getObject(key: String?, `object`: Class<T>): T {
        val gson = Gson()
        val json = getPreferenceString(key)
        return gson.fromJson(json, `object`)
    }
}