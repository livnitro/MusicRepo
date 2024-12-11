package com.example.musicrepo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.musicrepo.model.Instrument
import com.example.musicrepo.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun GuitarsScreen(innerPadding: PaddingValues) {
    var instruments by remember { mutableStateOf<List<Instrument>>(emptyList()) }

    LaunchedEffect(true) {
        RetrofitInstance.apiService.getAllInstruments().enqueue(object : Callback<List<Instrument>> {
            override fun onResponse(call: Call<List<Instrument>>, response: Response<List<Instrument>>) {
                if (response.isSuccessful) {
                    instruments = response.body() ?: emptyList()
                }
            }

            override fun onFailure(call: Call<List<Instrument>>, t: Throwable) {
            }
        })
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2A2C33))
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(bottom = 16.dp)
                ) {
                    Text(
                        text = "Explora los instrumentos",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.TopStart)
                    )
                }
            }

            items(instruments) { instrument ->
                GuitarCard(
                    title = instrument.nombre,
                    marca = instrument.marca,
                    modelo = instrument.modelo,
                    imagenUrl = instrument.imagen  // Usamos la URL de la imagen
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun GuitarCard(title: String, marca: String, modelo: String, imagenUrl: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(8.dp)
            .background(Color(0xFF2A2C33))
            .clip(RoundedCornerShape(16.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF121212))
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Usamos Coil para cargar la imagen desde la URL
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Gray)
            ) {
                Image(
                    painter = rememberImagePainter(imagenUrl),  // Aquí se usa Coil para cargar la imagen desde la URL
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(110.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Fila para Marca y Modelo
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = modelo,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = Color(0xFFFFD700)
                    )
                    Text(
                        text = marca,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = Color(0xFFFFD700)
                    )
                }
            }

            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = null,
                tint = Color(0xFFFFD700),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GuitarsScreenPreview() {
    GuitarsScreen(innerPadding = PaddingValues(0.dp))
}
