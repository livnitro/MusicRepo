package com.example.musicrepo.presentation.ui.screens

import android.service.carrier.CarrierService
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.musicrepo.datasource.service.CatService
import com.example.musicrepo.datasource.service.InstrumentsService
import com.example.musicrepo.domain.dtos.CatResponse
import com.example.musicrepo.domain.dtos.InstrumentResponse
import com.example.musicrepo.domain.use_cases.SharedPref
import com.example.musicrepo.presentation.components.MiniCatBannerView
import com.example.musicrepo.utils.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun InstrumentsCatScreen(innerPadding: PaddingValues, navController: NavController){
    val scope = rememberCoroutineScope()
    var cats by remember {
        mutableStateOf(emptyList<CatResponse>())
    }

    LaunchedEffect(key1 = true) {
        scope.launch(Dispatchers.IO) {
            try {
                val catService = Retrofit.Builder()
                    .baseUrl("https://api.cosmobius.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(CatService::class.java)
                val response = catService.getAll()
                Log.i("Cat Screen",response.toString())
                Log.i("Cat Screen",response.body().toString())
                if(response.code() == 200) {
                    withContext(Dispatchers.Main){
                        cats = response.body()!!
                    }
                }
            }catch (e:Exception){
                Log.e("Instruments Error", e.toString())
            }
        }
    }

    val sharedPref = SharedPref(LocalContext.current)
    Column (modifier =  Modifier.padding(innerPadding).padding(start = 20.dp, end = 20.dp, top = 32.dp)){
        Text("Nuestras Categorias")
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier =  Modifier.padding(top = 14.dp)
        ) {
            items(cats){
                MiniCatBannerView(it.nombre, onClick = ({
                    sharedPref.saveNextId(it.id)
                    sharedPref.saveGuitarMode(0)
                    val lol = sharedPref.getGuitarMode()
                    Log.i("InstrumentsCat", lol.toString())
                    navController.navigate(Screen.Guitars.route)
                }) )
            }
        }
    }

}