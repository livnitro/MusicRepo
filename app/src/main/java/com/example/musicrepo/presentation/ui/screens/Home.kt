package com.example.musicrepo.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicrepo.presentation.ui.theme.MusicRepoTheme

@Composable
fun HomeScreen(innerPadding : PaddingValues){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ){

    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HomeScreenPreview(){
    MusicRepoTheme {
        HomeScreen(innerPadding = PaddingValues(0.dp))
    }
}