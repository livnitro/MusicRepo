package com.example.musicrepo.utils

sealed class Screen(val route: String){
    data object Home : Screen("inicio")
    data object InstrumentsCat : Screen("instrumentos")
    data object BookMarks : Screen("favoritos")
    data object InstrumentDetail : Screen("instrumentdetail")
    data object Instruments : Screen("instruments")
    data object Guitars : Screen("guitars")
}