package com.coffee_order.ui.home

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.coffee_order.R
import com.coffee_order.data.Item
import com.coffee_order.data.ItemType
import com.coffee_order.databinding.FragmentHomeBinding
import com.coffee_order.helper.ItemsAdapter
import com.google.gson.Gson

class HomeFragment : Fragment() {

    private lateinit var hotCoffees: ArrayList<Item>
    private lateinit var foodItems: ArrayList<Item>
    private lateinit var binding: FragmentHomeBinding
    private lateinit var coffeeAdapter:ItemsAdapter
    private lateinit var foodAdapter:ItemsAdapter
    private val viewModel: HomeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { // Array list to hold the food and coffee items
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        hotCoffees = ArrayList()
        foodItems = ArrayList()

        coffeeAdapter = ItemsAdapter(hotCoffees)
        foodAdapter = ItemsAdapter(foodItems)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        viewModel.populateLists()
        viewModel.items.observe(viewLifecycleOwner){
            for (item in it){
                if(item.itemType == ItemType.FOOD_ITEMS){
                    foodItems.add(item)
                }else{
                    hotCoffees.add(item)
                }
            }
                coffeeAdapter.notifyDataSetChanged()
                foodAdapter.notifyDataSetChanged()
        }
        viewModel.isSearching.observe(viewLifecycleOwner) {
            if (it) {
                binding.searchButton.setImageResource(R.drawable.ic_baseline_cancel_24)
                binding.searchBar.visibility = View.VISIBLE
            } else {
                binding.searchButton.setImageResource(R.drawable.ic_baseline_search_24)
                binding.searchBar.visibility = View.GONE
            }
        }
        recyclerViewSetters()
        binding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
            if(checkedId == R.id.allChip){
                binding.allItemView.visibility = View.VISIBLE
                binding.singleItemView.visibility = View.GONE
            }else{
                binding.allItemView.visibility = View.GONE
                binding.singleItemView.visibility = View.VISIBLE
                when (checkedId) {
                    R.id.hotCoffeeChip -> {
                        singleRecyclerViewSetter(ItemType.HOT_COFFEE)
                    }
                    R.id.foodItemsChip -> {
                        singleRecyclerViewSetter(ItemType.FOOD_ITEMS)
                    }
                    else -> {
                        binding.allItemView.visibility = View.GONE
                        binding.singleItemView.visibility = View.GONE
                        Toast.makeText(requireContext(), "No items", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun singleRecyclerViewSetter(itemType: ItemType){
        if(itemType == ItemType.HOT_COFFEE) {
            binding.recyclerView.apply {
                layoutManager =
                    GridLayoutManager(requireContext(),2)
                adapter = coffeeAdapter
            }
            coffeeAdapter.setOnFavouriteTappedListener(object : ItemsAdapter.OnFavouriteTapped{
                override fun onTap(position: Int) {
                    hotCoffees[position].favorite = !hotCoffees[position].favorite
                    coffeeAdapter.notifyDataSetChanged()
                }

            })
            coffeeAdapter.setOnAddTappedListener(object : ItemsAdapter.OnAddTapped{
                override fun onTap(position: Int) {
                    Toast.makeText(requireContext(), "Item added", Toast.LENGTH_SHORT).show()
                }
            })
            coffeeAdapter.setOnItemTappedListener(object : ItemsAdapter.OnItemTapped{
                override fun onTap(position: Int) {
                    saveObject(
                        "current",
                        hotCoffees[position]
                    )
                    findNavController().navigate(R.id.action_home_to_details)
                }
            })
        }else{
            binding.recyclerView.apply {
                layoutManager =
                    GridLayoutManager(requireContext(),2)
                adapter = foodAdapter
            }
            foodAdapter.setOnAddTappedListener(object : ItemsAdapter.OnAddTapped{
                override fun onTap(position: Int) {
                    Toast.makeText(requireContext(), "Item added", Toast.LENGTH_SHORT).show()
                }
            })

            foodAdapter.setOnFavouriteTappedListener(object : ItemsAdapter.OnFavouriteTapped{
                override fun onTap(position: Int) {
                    foodItems[position].favorite = !foodItems[position].favorite
                    foodAdapter.notifyDataSetChanged()
                }
            })
            foodAdapter.setOnItemTappedListener(object : ItemsAdapter.OnItemTapped{
                override fun onTap(position: Int) {
                    saveObject(
                        "current",
                        foodItems[position]
                    )
                    findNavController().navigate(R.id.action_home_to_details)
                }
            })

        }
    }
    private fun recyclerViewSetters() {
        binding.hcRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = coffeeAdapter
        }

        binding.fiRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = foodAdapter
        }
        coffeeAdapter.setOnFavouriteTappedListener(object : ItemsAdapter.OnFavouriteTapped{
            override fun onTap(position: Int) {
                hotCoffees[position].favorite = !hotCoffees[position].favorite
                coffeeAdapter.notifyDataSetChanged()
            }

        })
        coffeeAdapter.setOnAddTappedListener(object : ItemsAdapter.OnAddTapped{
            override fun onTap(position: Int) {
                Toast.makeText(requireContext(), "Item added", Toast.LENGTH_SHORT).show()
            }
        })
        coffeeAdapter.setOnItemTappedListener(object : ItemsAdapter.OnItemTapped{
            override fun onTap(position: Int) {
                saveObject(
                    "current",
                    hotCoffees[position]
                )
                findNavController().navigate(R.id.action_home_to_details)
            }
        })
        foodAdapter.setOnAddTappedListener(object : ItemsAdapter.OnAddTapped{
            override fun onTap(position: Int) {
                Toast.makeText(requireContext(), "Item added", Toast.LENGTH_SHORT).show()
            }
        })

        foodAdapter.setOnFavouriteTappedListener(object : ItemsAdapter.OnFavouriteTapped{
            override fun onTap(position: Int) {
                foodItems[position].favorite = !foodItems[position].favorite
                foodAdapter.notifyDataSetChanged()
            }
        })
        foodAdapter.setOnItemTappedListener(object : ItemsAdapter.OnItemTapped{
            override fun onTap(position: Int) {
                saveObject(
                    "current",
                    foodItems[position]
                )
                findNavController().navigate(R.id.action_home_to_details)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.next_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.next_forward) {
            findNavController().navigate(R.id.action_home_to_dashboard)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun savePreferenceString(key: String?, value: String?) {
        val preferences = requireContext().getSharedPreferences("app_prefs", MODE_PRIVATE)
        preferences!!.edit().putString(key, value).apply()
    }


    internal fun saveObject(key: String?, `object`: Any?) {
        val gson = Gson()
        val json = gson.toJson(`object`)
        savePreferenceString(key, json)
    }

}