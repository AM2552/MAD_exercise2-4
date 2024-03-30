package com.example.movieappmad24.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.movieappmad24.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopAppBar(barTitle: String, navController: NavController, navigationButton: Boolean) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        // conditional appearance of the back button
        navigationIcon = {
            if (navigationButton) {
                run {
                    IconButton(onClick = { navController.popBackStack() }) { // use of the backstack
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            } else null
        },
        title = { Text(barTitle) }
    )
}

@Composable
fun SimpleBottomAppBar(navController: NavController) {
    // use of the backstack as a stateholder
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    //highlighting the active icon
    val activeColor = MaterialTheme.colorScheme.inversePrimary
    val inactiveColor = MaterialTheme.colorScheme.onSurface

    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = contentColorFor(MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HomeButton(
                navController = navController,
                currentDestination = currentDestination,
                activeColor = activeColor,
                inactiveColor = inactiveColor
            )
            Spacer(Modifier.width(170.dp))
            WatchlistButton(
                navController = navController,
                currentDestination = currentDestination,
                activeColor = activeColor,
                inactiveColor = inactiveColor
            )
        }
    }
}


@Composable
fun HomeButton(navController: NavController, currentDestination: String?, activeColor: Color, inactiveColor: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(
            onClick = {
                if (currentDestination != Screen.Home.route) navController.navigate(Screen.Home.route) })
    ) {
        Icon(Icons.Default.Home, contentDescription = "Home", tint = if (currentDestination == Screen.Home.route) activeColor else inactiveColor)
        Spacer(Modifier.height(7.dp))
        Text("Home", style = MaterialTheme.typography.labelSmall, color = if (currentDestination == Screen.Home.route) activeColor else inactiveColor)
    }
}

@Composable
fun WatchlistButton(navController: NavController, currentDestination: String?, activeColor: Color, inactiveColor: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(
            onClick = {
                if (currentDestination != Screen.Watchlist.route) navController.navigate(Screen.Watchlist.route) })
    ) {
        Icon(Icons.Default.Star, contentDescription = "Watchlist", tint = if (currentDestination == Screen.Watchlist.route) activeColor else inactiveColor)
        Spacer(Modifier.height(7.dp))
        Text("Watchlist", style = MaterialTheme.typography.labelSmall, color = if (currentDestination == Screen.Watchlist.route) activeColor else inactiveColor)
    }
}