package com.example.musicrepo.presentation.ui.screens

import android.graphics.pdf.PdfDocument.Page
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.lerp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.example.musicrepo.presentation.components.CardCarrousel
import com.example.musicrepo.presentation.components.RecienteItem
import com.example.musicrepo.presentation.ui.theme.MusicRepoTheme
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun HomeScreen(innerPadding : PaddingValues){
    val pagerState = rememberPagerState(pageCount = { 10 })

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(MaterialTheme.colorScheme.background)
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 30.dp)
        ){
            Text(
                text = "Instrumentos recomendados",
                modifier = Modifier.width(170.dp),
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        HorizontalPager(
            contentPadding = PaddingValues(start = 30.dp, top = 20.dp, bottom = 20.dp, end = 150.dp),
            state = pagerState,
            pageSize = PageSize.Fixed(230.dp)
        ) { page ->
//            val pageOffset = pagerState.getOffsetDistanceInPages(page).absoluteValue

            Card(
                modifier = Modifier
                    .graphicsLayer {
                        // Calculate the absolute offset for the current page from the
                        // scroll position. We use the absolute value which allows us to mirror
                        // any effects for both directions
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
                    .fillMaxWidth(),
                shape = RoundedCornerShape(35.dp)
            ) {
                CardCarrousel()
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
            val coroutineScope = rememberCoroutineScope()
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
                            coroutineScope.launch {
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
            color = Color.White
        )
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ){
            RecienteItem()
            RecienteItem()
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
        HomeScreen(innerPadding = PaddingValues(0.dp))
    }
}