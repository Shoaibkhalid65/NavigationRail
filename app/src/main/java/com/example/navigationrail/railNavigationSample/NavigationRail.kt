package com.example.navigationrail.railNavigationSample

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuOpen
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.WideNavigationRail
import androidx.compose.material3.WideNavigationRailItem
import androidx.compose.material3.WideNavigationRailValue
import androidx.compose.material3.rememberWideNavigationRailState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun NavigationRailSample(navController: NavController){
    var selectedIndex by remember { mutableIntStateOf(0) }
    val state= rememberWideNavigationRailState()
    val scope = rememberCoroutineScope()
    WideNavigationRail(
        modifier = Modifier,
        state=state,
        header = {
            val isCollapsed=state.targetValue== WideNavigationRailValue.Collapsed
            IconButton(
                modifier = Modifier.padding(start = if(!isCollapsed) 50.dp else 20.dp),
                onClick = {
                    scope.launch {
                        if(isCollapsed){
                            state.expand()
                        }else{
                            state.collapse()
                        }
                    }
                }
            ) {
                Icon(
                    imageVector = if(isCollapsed) Icons.Default.Menu else Icons.AutoMirrored.Default.MenuOpen,
                    contentDescription = null
                )
            }
        }
    ) {
        Destination.entries.forEachIndexed { index, destination ->
            val isSelected=selectedIndex==index
            WideNavigationRailItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(destination.route)
                    selectedIndex=index
                },
                icon = {
                    Icon(
                        imageVector = if(isSelected) destination.filledVector else destination.outlinedVector,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = destination.label
                    )
                },
                railExpanded = state.targetValue== WideNavigationRailValue.Expanded
            )
        }
    }
}