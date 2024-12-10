package com.example.musicrepo.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.musicrepo.R
import com.example.musicrepo.presentation.ui.theme.MusicRepoTheme
import com.example.musicrepo.utils.righteousFont
import com.example.musicrepo.utils.robotoFont

@Composable
fun RecienteItem(nombre : String, descr : String, imagen: String = ""){
    Card (
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
        ,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ){
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Box (
                modifier = Modifier
                    .fillMaxHeight()
                    .width(80.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
            ){
                AsyncImage(
                    model = imagen,
                    contentDescription = descr,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Column (
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 20.dp, top = 15.dp)
            ){
                Text(
                    text = nombre,
                    fontFamily = robotoFont,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = descr,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 12.sp,
                    fontFamily = robotoFont
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Flecha",
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(30.dp)
            )
        }
    }
}

@Preview
@Composable
fun RecienteItemPreview(){
    MusicRepoTheme {
        RecienteItem("Prueba", "Descr")
    }
}