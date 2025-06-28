package com.example.navigationrail.railNavigationSample

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

enum class Destination (
    val route: String,
    val filledVector: ImageVector,
    val outlinedVector: ImageVector,
    val label: String
){
    HOME(
        route = "home_route",
        filledVector= Icons.Default.Home,
        outlinedVector= Icons.Outlined.Home,
        label = "Home"
    ),
    CHATS(
        route = "chats_route",
        filledVector = Icons.AutoMirrored.Default.Message,
        outlinedVector = Icons.AutoMirrored.Outlined.Message,
        label = "Chats"
    ),
    SEARCH(
        route = "search_route",
        filledVector = Icons.Default.Search,
        outlinedVector = Icons.Outlined.Search,
        label = "Search"
    ),
    PROFILE(
        route = "profile_route",
        filledVector = Icons.Default.Person,
        outlinedVector = Icons.Outlined.Person,
        label = "Profile"
    )
}