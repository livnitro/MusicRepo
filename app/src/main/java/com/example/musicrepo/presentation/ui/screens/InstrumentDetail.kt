package com.example.musicrepo.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.materialIcon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.musicrepo.presentation.components.BookmarkItem
import com.example.musicrepo.presentation.components.BookmarkItemView
import com.example.musicrepo.utils.*

@Composable
fun InstrumentDetailScreen(innerPadding: PaddingValues, idInstrument: Int = 0) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .offset(y = 40.dp)
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ){
        Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .offset(y = 40.dp)
            .background(MaterialTheme.colorScheme.background)
        ){
            Text(
                text = "Classic Vibe '60s Jazzmaster",
                fontSize = 36.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.Bold,
                fontFamily = righteousFont
            )
            Text(
                text = "Fender",
                fontSize = 24.sp,
                fontFamily = righteousFont,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.jazzmaster),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(10.dp),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(10.dp)
            ){
                Text(
                    text = "La Classic Vibe ‘60s Jazzmaster® es un tributo fiel y sorprendente a uno de los modelos " +
                            "legendarios de Fender. Reproduce el sonido incomparable de la Jazzmaster gracias a sus " +
                            "dos pastillas de bobina simple diseñadas por Fender e imanes de Alnico.",
                    fontSize = 16.sp,
                    fontFamily = robotoFont,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Button(
                onClick = {
                    /*TODO*/
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary
                ),
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp)
                    .height(60.dp)
            ) {
                Text(
                    text = "Agregar a favoritos",
                    fontSize = 16.sp,
                    fontFamily = righteousFont,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
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