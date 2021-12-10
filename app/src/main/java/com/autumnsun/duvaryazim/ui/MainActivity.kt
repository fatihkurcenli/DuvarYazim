package com.autumnsun.duvaryazim.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.autumnsun.duvaryazim.R
import com.autumnsun.duvaryazim.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var disconnectApp: Boolean = false
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    lateinit var toolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_DuvarYazim)
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toolBar = binding.viewAppBar.mainToolbar
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.homeFragment,
                R.id.searchFragment,
                R.id.favoriteFragment
            )
        )

        binding.navBottomNavigationView.setupWithNavController(navController)

/*        setupActionBarWithNavController(
            navController = navController,
            configuration = appBarConfiguration
        )*/


        binding.fabButton.animate()
        binding.fabButton.setOnClickListener {
            navController.navigate(R.id.addWallStreetFragment)
        }

        setSupportActionBar(binding.viewAppBar.mainToolbar)
        binding.viewAppBar.mainToolbar.setupWithNavController(
            navController,
            appBarConfiguration
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

/*    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_menu, menu)
        binding.navBottomNavigationView.setupWithNavController(menu!!, navController)
        return false
    }*/

    override fun onBackPressed() {
        if (disconnectApp) {
            super.onBackPressed()
        } else {
            Toast.makeText(this, getString(R.string.disconnect_app), Toast.LENGTH_SHORT)
                .show()
            disconnectApp = true
            lifecycleScope.launch(Dispatchers.IO) {
                delay(2000L)
                disconnectApp = false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}