package com.example.musicrepo.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.example.musicrepo.datasource.service.CatService
import com.example.musicrepo.datasource.service.InstrumentsService
import com.example.musicrepo.domain.dtos.InstrumentResponse
import com.example.musicrepo.domain.use_cases.SharedPref
import com.example.musicrepo.presentation.components.BookmarkItem
import com.example.musicrepo.presentation.components.BookmarkItemView
import com.example.musicrepo.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun InstrumentDetailScreen(innerPadding: PaddingValues, idInstrument: Int = 0) {

    val sharedPref = SharedPref(LocalContext.current)
    val instrumId = sharedPref.getNextId()
    val scope = rememberCoroutineScope()
    var instrument by remember {
        mutableStateOf(emptyList<InstrumentResponse>())
    }

    LaunchedEffect(key1 = true) {
        scope.launch(Dispatchers.IO) {
            try {
                val instrumentService = Retrofit.Builder()
                    .baseUrl("https://api.cosmobius.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(InstrumentsService::class.java)
                val response = instrumentService.getById(instrumId)
                Log.i("Instrument Screen",response.toString())
                Log.i("Instrument Screen",response.body().toString())
                if(response.code() == 200) {
                    withContext(Dispatchers.Main){
                        instrument = response.body()!!
                    }
                }
            }catch (e:Exception){
                Log.e("Instruments Error", e.toString())
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .offset(y = 40.dp)
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .offset(y = 40.dp)
                .background(MaterialTheme.colorScheme.background)
        ){
            items(instrument){
                Text(
                    text = it.nombre,
                    fontSize = 36.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.Bold,
                    fontFamily = righteousFont
                )
                Text(
                    text = it.marca,
                    fontSize = 24.sp,
                    fontFamily = righteousFont,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
                AsyncImage(
                    model = it.imagen,
                    contentDescription = it.descr,
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
                        text = it.descr,
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
}


@Preview
@Composable
fun InstrumentDetailScreenPreview() {
    MusicRepoTheme {
        InstrumentDetailScreen(innerPadding = PaddingValues(0.dp), 0)
    }
}