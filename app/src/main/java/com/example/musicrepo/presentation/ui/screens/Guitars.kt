package com.example.musicrepo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
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
import com.example.musicrepo.utils.robotoFont
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun GuitarsScreen(innerPadding: PaddingValues) {
    var instruments by remember { mutableStateOf<List<Instrument>>(emptyList()) }
    var showDialog by remember { mutableStateOf(false) }
    var selectedInstrument by remember { mutableStateOf<Instrument?>(null) }

    LaunchedEffect(true) {
        RetrofitInstance.apiService.getAllInstruments().enqueue(object : Callback<List<Instrument>> {
            override fun onResponse(call: Call<List<Instrument>>, response: Response<List<Instrument>>) {
                if (response.isSuccessful) {
                    val allInstruments = response.body() ?: emptyList()
                    instruments = allInstruments.filter {
                        it.nombre.startsWith("guitarra", ignoreCase = true)
                    }
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
                        text = "Explora las guitarras",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        fontFamily = robotoFont,
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
                    descripcion = instrument.descr,
                    imagenUrl = instrument.imagen,
                    onCardClick = {
                        selectedInstrument = instrument
                        showDialog = true
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        selectedInstrument?.let { instrument ->
            if (showDialog) {
                InstrumentDetailDialog(
                    instrument = instrument,
                    onDismiss = { showDialog = false }
                )
            }
        }
    }
}



@Composable
fun GuitarCard(title: String, marca: String, modelo: String, imagenUrl: String, descripcion: String, onCardClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(8.dp)
            .background(Color(0xFF2A2C33))
            .clip(RoundedCornerShape(16.dp))
            .clickable { onCardClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF121212))
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Gray)
            ) {
                Image(
                    painter = rememberImagePainter(imagenUrl),
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

@Composable
fun InstrumentDetailDialog(instrument: Instrument, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = instrument.nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFFFFD700)
            )
        },
        text = {
            Column {
                Text(text = "Marca: ${instrument.marca}", color = Color.White)
                Text(text = "Modelo: ${instrument.modelo}", color = Color.White)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Descripción:", color = Color(0xFFFFD700))
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = instrument.descr, color = Color.White)
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Cerrar", color = Color(0xFFFFD700))
            }
        },
        containerColor = Color(0xFF2A2C33),
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .padding(16.dp)
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GuitarsScreenPreview() {
    GuitarsScreen(innerPadding = PaddingValues(0.dp))
}
