package com.example.musicrepo.presentation.ui.screens

import android.graphics.pdf.PdfDocument.Page
import android.util.Log
import androidx.annotation.ColorRes
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.lerp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.musicrepo.R
import com.example.musicrepo.datasource.service.CatService
import com.example.musicrepo.datasource.service.InstrumentsService
import com.example.musicrepo.domain.dtos.CatResponse
import com.example.musicrepo.domain.dtos.InstrumentResponse
import com.example.musicrepo.domain.use_cases.SharedPref
import com.example.musicrepo.presentation.components.CardCarrousel
import com.example.musicrepo.presentation.components.RecienteItem
import com.example.musicrepo.presentation.ui.theme.MusicRepoTheme
import com.example.musicrepo.utils.Screen
import com.example.musicrepo.utils.righteousFont
import com.example.musicrepo.utils.robotoFont
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.absoluteValue

@Composable
fun HomeScreen(innerPadding : PaddingValues, navController: NavController){
    val sharedPref = SharedPref(LocalContext.current)
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { 5 })
    var instrumentosAleatorios by remember {
        mutableStateOf(emptyList<InstrumentResponse>())
    }
    var instrumentosRecientes by remember {
        mutableStateOf(emptyList<InstrumentResponse>())
    }
    var isLoading by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = true) {
        scope.launch(Dispatchers.IO) {
            try {
                val instrumentsService = Retrofit.Builder()
                    .baseUrl("https://api.cosmobius.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(InstrumentsService::class.java)
                isLoading = true
                val peticion = async { instrumentsService.get5Instruments() }
                val peticion2 = async { instrumentsService.getLast3Instruments() }

                val response = peticion.await()
                val response2 = peticion2.await()
                isLoading = false

                if(response.code() == 200) {
                    withContext(Dispatchers.Main){
                        instrumentosAleatorios = response.body()!!
                    }
                }
                if(response2.code() == 200) {
                    withContext(Dispatchers.Main){
                        instrumentosRecientes = response2.body()!!
                    }
                }

            }catch (e:Exception){
                Log.e("Instruments Error", e.toString())
            }
        }
    }


    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(
                rememberScrollState()
            )
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 30.dp)
        ){
            Text(
                text = "Instrumentos recomendados",
                modifier = Modifier.width(210.dp),
                fontSize = 26.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = righteousFont
            )
        }

        HorizontalPager(
            contentPadding = PaddingValues(start = 30.dp, top = 20.dp, bottom = 20.dp, end = 150.dp),
            state = pagerState,
            pageSize = PageSize.Fixed(230.dp)
        ) { page ->
//            val pageOffset = pagerState.getOffsetDistanceInPages(page).absoluteValue

            val instrumento = instrumentosAleatorios.getOrNull(page)
            Card(
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue

                        val scale = lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                        scaleX = scale
                        scaleY = scale
                        translationY = lerp(
                            start = 25.dp.toPx(),
                            stop = 0f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )

                        // We animate the alpha, between 50% and 100%
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
                    .padding(end = 10.dp)
                    .height(330.dp)
                    .fillMaxWidth()
                    .clickable {
                        if (instrumento != null) {
                            sharedPref.saveNextId(instrumento.id)
                            navController.navigate(Screen.InstrumentDetail.route)
                        }
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(35.dp)
            ) {
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                } else{
                    if (instrumento != null) {
                        CardCarrousel(nombre = instrumento.nombre , modelo = instrumento.modelo, imagen = instrumento.imagen)
                    }
                }
            }
        }
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(bottom = 30.dp, top = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondary
                val boxSize = if (pagerState.currentPage == iteration) 15.dp else 10.dp
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .width(boxSize)
                        .size(boxSize)
                        .background(color)
                        .size(16.dp)
                        .clickable {
                            scope.launch {
                                pagerState.animateScrollToPage(iteration)
                            }
                        }
                )
            }
        }

        Text(
            text = "Agregados recientemente",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 30.dp, top = 20.dp, bottom = 20.dp, end = 20.dp),
            color = Color.White,
            fontFamily = righteousFont
        )
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 30.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ){
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
            } else {
                instrumentosRecientes.forEach {instrumento ->
                    RecienteItem(nombre=instrumento.nombre, modelo=instrumento.modelo, imagen = instrumento.imagen, onClick = ({
                        sharedPref.saveNextId(instrumento.id)
                        navController.navigate(Screen.InstrumentDetail.route)
                    }))
                }
            }
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HomeScreenPreview(){
    MusicRepoTheme {
        val navController = rememberNavController()
        HomeScreen(innerPadding = PaddingValues(0.dp), navController = navController)
    }
}