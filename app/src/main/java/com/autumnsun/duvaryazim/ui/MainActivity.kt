package com.autumnsun.duvaryazim.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import com.autumnsun.duvaryazim.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var disconnectApp: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

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
}