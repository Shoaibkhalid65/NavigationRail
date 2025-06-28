package com.example.navigationrail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.navigationrail.railNavigationSample.AppNavHost
import com.example.navigationrail.railNavigationSample.Destination
import com.example.navigationrail.railNavigationSample.NavigationRailSample

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController= rememberNavController()
            AppNavHost(navController, Destination.HOME)
            NavigationRailSample(navController)
        }
    }
}