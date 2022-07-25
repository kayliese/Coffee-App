package com.coffee_order

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.coffee_order.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var controller: NavController? = null
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbarMain)
        val navHostFragment =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment?)!!
        controller = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, controller!!)
        binding.navView.setupWithNavController(controller!!)
    }

    override fun onSupportNavigateUp(): Boolean {
        return controller?.navigateUp()!!
    }


}