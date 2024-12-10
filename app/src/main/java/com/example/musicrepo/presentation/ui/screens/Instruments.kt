package com.example.musicrepo.presentation.ui.screens

import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun IntrumentsScreen(){
    Column {
        Text("Hi")
    }
}

@Preview(
    showBackground = true,
    showSystemUi = false
)
@Composable
fun InstrumentsScreenPreview(){
    IntrumentsScreen()
}