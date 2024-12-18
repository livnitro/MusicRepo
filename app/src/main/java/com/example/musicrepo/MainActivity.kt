package com.example.musicrepo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.musicrepo.domain.use_cases.SharedPref
import com.example.musicrepo.presentation.models.TabBarItem
import com.example.musicrepo.presentation.ui.screens.BookmarksScreen
import com.example.musicrepo.presentation.ui.screens.HomeScreen
import com.example.musicrepo.presentation.ui.screens.InstrumentDetailScreen
import com.example.musicrepo.presentation.ui.screens.InstrumentsCatScreen
import com.example.musicrepo.presentation.ui.screens.IntrumentsScreen
import com.example.musicrepo.presentation.ui.theme.MusicRepoTheme
import com.example.musicrepo.ui.screens.GuitarsScreen
import com.example.musicrepo.utils.Screen

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MusicRepoTheme {
                val homeTab = TabBarItem(title = "Inicio", selectedIcon = Icons.Filled.Home, unselectedIcon = Icons.Outlined.Home)
                val alertsTab = TabBarItem(title = "Instrumentos", selectedIcon = Icons.Filled.List, unselectedIcon = Icons.Outlined.List)
                val settingsTab = TabBarItem(title = "Favoritos", selectedIcon = Icons.Filled.Favorite, unselectedIcon = Icons.Outlined.FavoriteBorder)
                val moreTab = TabBarItem(title = "More", selectedIcon = Icons.Filled.List, unselectedIcon = Icons.Outlined.List)
                val tabBarItems = listOf(homeTab, alertsTab, settingsTab)

                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = { TabView(tabBarItems, navController) }) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route
                    ){
                        composable(route = Screen.Home.route) {
                            HomeScreen(innerPadding = innerPadding, navController = navController)
                        }
                        composable(route = Screen.InstrumentsCat.route) {
                            InstrumentsCatScreen(innerPadding = innerPadding, navController = navController)
                        }
                        composable(route = Screen.BookMarks.route) {
                            GuitarsScreen(innerPadding = innerPadding, navController = navController)
                        }
                        composable(route = Screen.InstrumentDetail.route) {
                            InstrumentDetailScreen(innerPadding = innerPadding)
                        }
                        composable(route = Screen.Instruments.route) {
                            IntrumentsScreen(innerPadding = innerPadding, navController = navController)
                        }
                        composable(route = Screen.Guitars.route) {
                            GuitarsScreen(innerPadding = innerPadding, navController = navController)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun TabView(tabBarItems: List<TabBarItem>, navController: NavController) {
    var selectedTabIndex by rememberSaveable {
        mutableStateOf(0)
    }

    val sharedPref = SharedPref(LocalContext.current)

    NavigationBar {
        // looping over each tab to generate the views and navigation for each item
        tabBarItems.forEachIndexed { index, tabBarItem ->
            NavigationBarItem(
                selected = selectedTabIndex == index,
                onClick = {
                    sharedPref.saveGuitarMode(1)
                    selectedTabIndex = index
                    navController.navigate(tabBarItem.title)
                },
                icon = {
                    TabBarIconView(
                        isSelected = selectedTabIndex == index,
                        selectedIcon = tabBarItem.selectedIcon,
                        unselectedIcon = tabBarItem.unselectedIcon,
                        title = tabBarItem.title,
                        badgeAmount = tabBarItem.badgeAmount
                    )
                },
                label = {Text(tabBarItem.title)})
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabBarIconView(
    isSelected: Boolean,
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector,
    title: String,
    badgeAmount: Int? = null
) {
    BadgedBox(badge = { TabBarBadgeView(badgeAmount) }) {
        Icon(
            imageVector = if (isSelected) {selectedIcon} else {unselectedIcon},
            contentDescription = title
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TabBarBadgeView(count: Int? = null) {
    if (count != null) {
        Badge {
            Text(count.toString())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MusicRepoTheme {

    }
}