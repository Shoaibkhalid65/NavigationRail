package com.example.navigationrail.screens

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailDefaults
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

@Composable
fun RailSample1(){
    val filledVectors=listOf(Icons.Default.Home, Icons.AutoMirrored.Default.Message, Icons.Default.Search,
        Icons.Default.Person)
    val outlinedVectors=listOf(Icons.Outlined.Home, Icons.AutoMirrored.Outlined.Message, Icons.Outlined.Search,
        Icons.Outlined.Person)
    val labels=listOf("Home","Chats","Search","Profile")
    var selectedIndex by remember { mutableIntStateOf(0) }
    val context= LocalContext.current
    NavigationRail(
        modifier = Modifier,
        containerColor = Color.Cyan,
        contentColor = Color.Cyan,
        header = {
            FloatingActionButton(
                onClick = {
                    Toast.makeText(context,"FAB clicked!", Toast.LENGTH_SHORT).show()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                )
            }
        },
        windowInsets = NavigationRailDefaults.windowInsets
    ) {
        outlinedVectors.forEachIndexed { index, outlinedVector ->
            val isSelected=selectedIndex==index
            NavigationRailItem(
                selected = isSelected,
                onClick = {
                    selectedIndex=index
                },
                icon = {
                    Icon(
                        imageVector = if(isSelected) filledVectors[index] else outlinedVector,
                        contentDescription = null
                    )
                },
                modifier = Modifier,
                enabled = true,
                label={
                    Text(
                        text = labels[index]
                    )
                },
                alwaysShowLabel = true,
                colors = NavigationRailItemDefaults.colors(
                    selectedIconColor = Color.Blue,
                    selectedTextColor = Color.Black,
                    indicatorColor = Color.Yellow,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.LightGray
                )
            )
        }
    }
}
