package com.example.musicrepo.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicrepo.presentation.ui.theme.MusicRepoTheme
import com.example.musicrepo.R
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.musicrepo.presentation.components.BookmarkItem
import com.example.musicrepo.presentation.components.BookmarkItemView
import com.example.musicrepo.utils.*

@Composable
fun InstrumentDetailScreen(innerPadding : PaddingValues, idInstrument : Int = 0){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(20.dp),
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .padding(innerPadding),
            contentAlignment = Alignment.CenterStart
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(20.dp)
            ){
                Text(
                    text = "Classic Vibe '60s Jazzmaster",
                    fontSize = 36.sp,
                    fontFamily = righteousFont,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(
                    text = "Fender",
                    fontSize = 24.sp,
                    fontFamily = righteousFont,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.jazzmaster),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ){
                Column {
                    Text(
                        text = "Descripción",
                        fontSize = 16.sp,
                        fontFamily = robotoFont,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .padding(top = 20.dp, start = 20.dp)
                    )
                    Text(
                        text = "La Classic Vibe ‘60s Jazzmaster® es un tributo fiel y sorprendente a uno de los modelos " +
                                "legendarios de Fender. Reproduce el sonido incomparable de la Jazzmaster gracias a sus " +
                                "dos pastillas de bobina simple diseñadas por Fender e imanes de Alnico.",
                        fontSize = 16.sp,
                        fontFamily = robotoFont,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    )
                }
            }
        }


    }
}

@Preview
@Composable
fun InstrumentDetailScreenPreview() {
    MusicRepoTheme {
        InstrumentDetailScreen(innerPadding = PaddingValues(0.dp), 0)
    }
}