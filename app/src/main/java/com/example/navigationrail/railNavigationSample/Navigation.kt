package com.example.navigationrail.railNavigationSample

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(
    navHostController: NavHostController,
    startDestination: Destination,
){
    NavHost(
        navHostController,
        startDestination.route
    ){
        Destination.entries.forEach { destination ->
            composable(destination.route) {
                when(destination){
                    Destination.HOME -> HomeScreen()
                    Destination.CHATS -> ChatsScreen()
                    Destination.SEARCH -> SearchScreen()
                    Destination.PROFILE -> ProfileScreen()
                }
            }
        }
    }
}