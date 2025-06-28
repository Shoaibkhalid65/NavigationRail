package com.example.navigationrail.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MenuOpen
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.WideNavigationRail
import androidx.compose.material3.WideNavigationRailDefaults
import androidx.compose.material3.WideNavigationRailItem
import androidx.compose.material3.WideNavigationRailItemDefaults
import androidx.compose.material3.WideNavigationRailValue
import androidx.compose.material3.rememberWideNavigationRailState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun WideRailSample1(){
    val filledVectors=listOf(Icons.Default.Home, Icons.AutoMirrored.Default.Message, Icons.Default.Search,
        Icons.Default.Person)
    val outlinedVectors=listOf(Icons.Outlined.Home, Icons.AutoMirrored.Outlined.Message, Icons.Outlined.Search,
        Icons.Outlined.Person)
    val labels=listOf("Home","Chats","Search","Profile")
    var selectedIndex by remember { mutableIntStateOf(0) }
    val state= rememberWideNavigationRailState()
    val scope= rememberCoroutineScope()
    val context= LocalContext.current
    Row (modifier = Modifier.fillMaxWidth()){
    WideNavigationRail (
        modifier = Modifier,
        state = state,
        shape = WideNavigationRailDefaults.containerShape,
        colors = WideNavigationRailDefaults.colors(
            containerColor = Color.Cyan,
            contentColor = Color.Magenta,
            modalContainerColor = Color.DarkGray,
            modalScrimColor = Color.LightGray
        ),
        header = {
            FloatingActionButton(

                onClick = {
                    scope.launch {
                        if(state.targetValue== WideNavigationRailValue.Expanded)
                            state.collapse()
                        else
                            state.expand()
                    }
                },
                modifier = Modifier.padding(start = 20.dp)
            ){
                Icon(
                    imageVector = if(state.targetValue== WideNavigationRailValue.Expanded) Icons.Default.MenuOpen else Icons.Default.Menu,
                    contentDescription = null
                )
            }
        },
        windowInsets = WideNavigationRailDefaults.windowInsets,
        arrangement = Arrangement.Top
    ) {
        outlinedVectors.forEachIndexed { index, outlinedVector ->
            val isSelected = selectedIndex == index
            WideNavigationRailItem(
                selected = isSelected,
                onClick = {
                    selectedIndex = index
                },
                icon = {
                    Icon(
                        imageVector = if (isSelected) filledVectors[index] else outlinedVector,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = labels[index]
                    )
                },
                modifier = Modifier,
                enabled = true,
                railExpanded = state.targetValue == WideNavigationRailValue.Expanded,
                iconPosition = WideNavigationRailItemDefaults.iconPositionFor(state.targetValue == WideNavigationRailValue.Expanded),
                colors = WideNavigationRailItemDefaults.colors(
                    selectedIconColor = Color.Green,
                    selectedTextColor = Color.Blue
                )
            )
        }
    }
        val textString=if(state.targetValue== WideNavigationRailValue.Expanded){
            "Expanded"
        }else{
            "Collapse"
        }
        Column {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Is animating : ${state.isAnimating}"
            )
            Text(
                modifier = Modifier.padding(16.dp),
                text = "The rail is $textString"

            )
            Text(
                modifier = Modifier.padding(16.dp),
                text =
                    "Note: The orientation of this demo has been locked to portrait mode, because" +
                            " landscape mode may result in a compact height in certain devices. For" +
                            " any compact screen dimensions, use a Navigation Bar instead."
            )

        }


    }
}