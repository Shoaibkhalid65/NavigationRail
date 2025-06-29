package com.example.navigationrail.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.automirrored.outlined.Assignment
import androidx.compose.material.icons.filled.AutoAwesomeMotion
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Dataset
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Dataset
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalWideNavigationRail
import androidx.compose.material3.ModalWideNavigationRailDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigationrail.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun CustomSample1(){
    val filledIcons=listOf(Icons.Default.Dashboard, Icons.Default.Description, Icons.AutoMirrored.Default.Assignment,
        Icons.Default.Dataset, Icons.Default.Settings)
    val outlineIcons=listOf(Icons.Outlined.Dashboard, Icons.Outlined.Description, Icons.AutoMirrored.Outlined.Assignment,
        Icons.Outlined.Dataset, Icons.Outlined.Settings)
    val labels=listOf("Dashboard","Automations","Permissions","Databases","Administration")
    var selectedIndex by remember { mutableIntStateOf(0) }
    val state= rememberWideNavigationRailState()
    val scope= rememberCoroutineScope()
    ModalWideNavigationRail (
        modifier = Modifier,
        state=state,
        hideOnCollapse = false,
        colors = WideNavigationRailDefaults.colors(
            containerColor = colorResource(R.color.dark_background),
            modalContainerColor = colorResource(R.color.dark_background)
        ),
        header = {
            val isCollapsed = state.targetValue == WideNavigationRailValue.Collapsed
            Row {
                FloatingActionButton(
                    modifier = Modifier.padding(start = if(!isCollapsed) 15.dp else 30.dp),
                    onClick = {
                        scope.launch {
                            if (isCollapsed) {
                                state.expand()
                            } else {
                                state.collapse()
                            }
                        }
                    },
                    containerColor = Color.Black
                ) {
                    Text(
                        text = "A",
                        color = colorResource(R.color.header_color),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                AnimatedVisibility(
                    visible = !isCollapsed,
                    enter = expandHorizontally(animationSpec = tween(300)) + fadeIn(),
                    exit = shrinkHorizontally(animationSpec = tween(300)) + fadeOut()
                ) {
                    Text(
                        text = "Alexander",
                        color = Color.White,
                        modifier = Modifier.padding(10.dp),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    ){
        filledIcons.forEachIndexed { index, filledVector ->
            val isSelected=selectedIndex==index
            val isCollapsed=state.targetValue== WideNavigationRailValue.Collapsed
            WideNavigationRailItem(
                selected = isSelected,
                onClick = {
                    selectedIndex=index
                },
                icon = {
                    IconButton(
                        modifier = Modifier.size(50.dp),
                        onClick = {selectedIndex=index},
                        shape = RoundedCornerShape(16.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = if(isSelected) colorResource(R.color.blue_item_background) else Color.Transparent
                        )
                    ) {
                        Icon(
                            imageVector = if(isSelected) filledVector else outlineIcons[index],
                            contentDescription = null
                        )
                    }
                },
                label = {
                    if(state.currentValue== WideNavigationRailValue.Expanded){
                        Text(
                            text = labels[index]
                        )
                    }
                },
                enabled = true,
                railExpanded = !isCollapsed,
                colors = WideNavigationRailItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    selectedIndicatorColor = Color.Transparent,
                    unselectedTextColor = colorResource(R.color.unselected_icon_color),
                    unselectedIconColor = colorResource(R.color.unselected_icon_color)
                )
            )
        }
    }
}
