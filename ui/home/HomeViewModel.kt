package com.coffee_order.ui.home

import android.database.Observable
import android.view.View
import android.widget.ImageButton
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coffee_order.R
import com.coffee_order.data.Extras
import com.coffee_order.data.Item
import com.coffee_order.data.ItemSize
import com.coffee_order.data.ItemType
import java.util.*
import kotlin.collections.ArrayList

class HomeViewModel : ViewModel() {
    var text: String = "Hello world"
    val userName:String = "James"
    val loremText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras tincidunt dictum purus, eget varius arcu porta ac. Integer eget tempus magna. In sed diam at purus consectetur tincidunt. Mauris vulputate nisl nibh, eu semper nunc fermentum at. Pellentesque interdum, nulla vitae blandit dapibus, felis leo dictum arcu, id vestibulum dolor nisl a quam. Aliquam vel aliquam turpis. Proin pretium leo in justo fermentum, at pellentesque quam suscipit. Nullam scelerisque vulputate dolor, ut viverra arcu tincidunt in. Cras rutrum efficitur ex id ultricies. Fusce luctus vitae nisl sed aliquet. Sed ac egestas erat. Donec massa nibh, consectetur faucibus iaculis non, scelerisque vitae nisi. Mauris id libero sit amet dolor semper aliquam eu pharetra lectus."
    val isSearching:MutableLiveData<Boolean> = MutableLiveData(false)
    fun changeVisibility(view:View){
        isSearching.value = !isSearching.value!!
    }
    val items:MutableLiveData<ArrayList<Item>> = MutableLiveData(ArrayList())
    fun populateLists(){
        val  extras = Extras(5,3)
        items.value?.add(Item(UUID.randomUUID().toString(),R.drawable.latte_1,"Latte","Coffee with some extra milk",1,true,loremText,5.0,3500,"4.7",ItemSize.MEDIUM,ItemType.HOT_COFFEE,extras))
        items.value?.add(Item(UUID.randomUUID().toString(),R.drawable.cappucino,"Cappucino","Prepared with equal parts double espresso and steamed coffee",1,false,loremText,30.0,2400,"4.9",ItemSize.SMALL,ItemType.HOT_COFFEE,null))
        items.value?.add(Item(UUID.randomUUID().toString(),R.drawable.luwak_coffee,"Luwak Coffee","Coffee having a lot of things in it.",1,true,loremText,7.0,2400,"4.9",ItemSize.SMALL,ItemType.HOT_COFFEE,null))
        items.value?.add(Item(UUID.randomUUID().toString(),R.drawable.muffin,"Muffin","Muffin with some chocolate chips",1,false,loremText,7.0,3500,"5.0",ItemSize.MEDIUM,ItemType.FOOD_ITEMS,null))
        items.value?.add(Item(UUID.randomUUID().toString(),R.drawable.croissant,"Croissant","Muffin with some chocolate chips",1,true,loremText,7.0,2400,"3.0",ItemSize.MEDIUM,ItemType.FOOD_ITEMS,null))
        items.value?.add(Item(UUID.randomUUID().toString(),R.drawable.baggel,"Baggel","Dense Chewy and tasty bagel",1,true,loremText,7.0,2400,"3.0",ItemSize.MEDIUM,ItemType.FOOD_ITEMS,null))
    }
}