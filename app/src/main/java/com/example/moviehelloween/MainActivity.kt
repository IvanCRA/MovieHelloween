package com.example.moviehelloween

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.moviehelloween.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)

        /*binding.bottomNav.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.homeFragment -> {
                    binding.toolbar.title = item.title
                    true
                }
                R.id.favouritesFragment -> {
                    binding.toolbar.title = item.title
                    true
                }
                R.id.searchFragment -> {
                    binding.toolbar.title = item.title
                    true
                }
                R.id.profileFragment -> {
                    binding.toolbar.title = item.title
                    true
                }
                else -> false
            }
        }*/
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> binding.toolbar.title = getString(R.string.popular_title)
                R.id.favouritesFragment -> binding.toolbar.title = getString(R.string.favourites_icon)
                R.id.searchFragment -> binding.toolbar.title = getString(R.string.search_icon)
                R.id.profileFragment -> binding.toolbar.title = getString(R.string.profile_icon)
            }
        }
    }
}