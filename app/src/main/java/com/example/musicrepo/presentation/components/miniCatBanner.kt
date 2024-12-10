package com.example.musicrepo.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MiniCatBannerView(nombre : String = "Nombre", onClick: () -> Unit){
    Box (modifier = Modifier.fillMaxWidth().height(60.dp).clip(RoundedCornerShape(20.dp))
        .clickable { onClick() }){
        Column (modifier = Modifier.background(color = Color.DarkGray).padding(10.dp)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            Text(nombre, color = Color.White)
        }
    }
}


@Preview(
    showBackground = true
)
@Composable
fun MiniCatBannerViewDemo(){
    MiniCatBannerView(nombre = "Nombre", onClick = ({}))
}