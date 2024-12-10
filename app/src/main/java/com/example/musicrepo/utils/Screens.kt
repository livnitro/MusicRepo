package com.example.musicrepo.utils

sealed class Screen(val route: String){
    data object Home : Screen("inicio")
    data object Instruments : Screen("instrumentos")
    data object BookMarks : Screen("favoritos")
}