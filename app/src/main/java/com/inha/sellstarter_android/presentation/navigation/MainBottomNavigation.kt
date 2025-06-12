package com.inha.sellstarter_android.presentation.navigation

import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.inha.sellstarter_android.presentation.navigation.route.BottomNavType.Companion.items
import com.inha.sellstarter_android.ui.theme.Grey0
import com.inha.sellstarter_android.ui.theme.Grey100
import com.inha.sellstarter_android.ui.theme.Purple200

@Composable
fun MainBottomNavigation(navController: NavController) {
    BottomNavigation(
        backgroundColor = Grey0
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { screen ->
            NavigationBarItem(
                label = {
                    Text(text = stringResource(id = screen.resourceId))
                },
                selected = currentRoute == screen.startDestination,
                onClick = {
                    navController.navigate(screen.startDestination) {
                        popUpTo(0) {
                            inclusive = false
                            saveState = false
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors =
                NavigationBarItemDefaults.colors(
                    selectedTextColor = Purple200,
                    unselectedTextColor = Grey100,
                    selectedIconColor = Purple200,
                    unselectedIconColor = Grey100,
                    indicatorColor = Grey0,
                ),
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(screen.icon),
                        contentDescription = screen.startDestination,
                        modifier = Modifier.size(24.dp)
                    )
                })
        }
    }
}