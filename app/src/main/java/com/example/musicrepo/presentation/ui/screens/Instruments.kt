package com.example.musicrepo.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicrepo.datasource.service.InstrumentsService
import com.example.musicrepo.domain.dtos.Instrument
import com.example.musicrepo.domain.dtos.InstrumentResponse
import com.example.musicrepo.presentation.components.RecienteItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Composable
fun IntrumentsScreen(innerPadding : PaddingValues){
    val scope = rememberCoroutineScope()
    var instruments by remember {
        mutableStateOf(emptyList<InstrumentResponse>())
    }

    LaunchedEffect(key1 = true) {
        scope.launch(Dispatchers.IO) {
            try {
                val InstrumentsService = Retrofit.Builder()
                    .baseUrl("https://api.cosmobius.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(InstrumentsService::class.java)
                val response = InstrumentsService.getAll()
                Log.i("InstrumentScreen",response.toString())
                Log.i("InstrumentScreen",response.body().toString())
                if(response.code() == 200) {
                    withContext(Dispatchers.Main){
                        instruments = response.body()!!
                    }
                }
            }catch (e:Exception){
                Log.e("Instruments Error", e.toString())
            }
        }
    }
    Column (modifier = Modifier.fillMaxSize().padding(innerPadding)){
        Text("Instrumentos", modifier = Modifier.padding(start = 10.dp, top = 20.dp, bottom = 20.dp), fontWeight = FontWeight.Bold)
        LazyColumn (modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp), verticalArrangement = Arrangement.spacedBy(20.dp)){
            items(instruments){
                RecienteItem(nombre = it.nombre, descr = it.descr)
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = false
)
@Composable
fun InstrumentsScreenPreview(){
    IntrumentsScreen(PaddingValues(0.dp))
}