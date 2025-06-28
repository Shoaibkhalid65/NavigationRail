package com.example.navigationrail.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuOpen
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalWideNavigationRail
import androidx.compose.material3.ModalWideNavigationRailProperties
import androidx.compose.material3.Text
import androidx.compose.material3.WideNavigationRailDefaults
import androidx.compose.material3.WideNavigationRailItem
import androidx.compose.material3.WideNavigationRailItemDefaults
import androidx.compose.material3.WideNavigationRailValue
import androidx.compose.material3.rememberWideNavigationRailState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ModalWideRailSample1(){
    val filledVectors=listOf(Icons.Default.Home, Icons.AutoMirrored.Default.Message, Icons.Default.Search,
        Icons.Default.Person)
    val outlinedVectors=listOf(Icons.Outlined.Home, Icons.AutoMirrored.Outlined.Message, Icons.Outlined.Search,
        Icons.Outlined.Person)
    val labels=listOf("Home","Chats","Search","Profile")
    var selectedIndex by remember { mutableIntStateOf(0) }
    val state= rememberWideNavigationRailState()
    val scope= rememberCoroutineScope()
    Row(modifier = Modifier.fillMaxWidth()) {
    ModalWideNavigationRail(
        modifier = Modifier,
        state=state,
        hideOnCollapse = false,
        collapsedShape = WideNavigationRailDefaults.modalContainerShape,
        expandedShape = WideNavigationRailDefaults.modalContainerShape,
        colors = WideNavigationRailDefaults.colors(
            contentColor = Color.Green,
            containerColor = Color.LightGray
        ),
        header = {
            val isCollapsed=state.targetValue== WideNavigationRailValue.Collapsed
            IconButton(
                onClick = {
                    scope.launch {
                        if(isCollapsed)
                            state.expand()
                        else
                            state.collapse()
                    }
                }
            ) {
                Icon(
                    imageVector = if(isCollapsed) Icons.Default.Menu else Icons.AutoMirrored.Default.MenuOpen,
                    contentDescription = null
                )
            }
        },
        expandedHeaderTopPadding = 100.dp,
        windowInsets = WideNavigationRailDefaults.windowInsets,
        arrangement = Arrangement.SpaceEvenly,
        expandedProperties = ModalWideNavigationRailProperties(true)
    ) {
        filledVectors.forEachIndexed { index, filledVector ->
            val isSelected = selectedIndex == index
            WideNavigationRailItem(
                selected = isSelected,
                onClick = {
                    selectedIndex = index
                },
                icon = {
                    Icon(
                        imageVector = if (isSelected) filledVector else outlinedVectors[index],
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
    }
}