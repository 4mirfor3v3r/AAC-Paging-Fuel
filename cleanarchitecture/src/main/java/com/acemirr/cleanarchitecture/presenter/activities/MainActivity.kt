package com.acemirr.cleanarchitecture.presenter.activities

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.acemirr.cleanarchitecture.R
import com.acemirr.cleanarchitecture.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupToolbar()
        setupNavigation()
    }
    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.fragmentContainer).navigateUp()
    }
    private fun setupNavigation() {
        binding.bottomNavigationViewMain.itemIconTintList = null
        val navHostFragment =supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.detailGridFragment ->{
                    binding.appBarMain.setExpanded(true)
                    supportActionBar?.hide()
                }
                else ->{
                    binding.toolbarMain.visibility = View.VISIBLE
                }
            }
        }
        setupActionBarWithNavController(navController)
        NavigationUI.setupWithNavController(binding.bottomNavigationViewMain,navController)
    }
    fun hideNavigation(isHidden:Boolean){
        if (isHidden) {
            binding.bottomNavigationViewMain.visibility = View.GONE
        } else {
            binding.bottomNavigationViewMain.visibility = View.VISIBLE
        }
    }
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbarMain)
    }

}
