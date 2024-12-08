package com.example.musicrepo.utils

sealed class Screen(val route: String){
    data object Home : Screen("home")
}