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
fun BookmarksScreen(innerPadding : PaddingValues){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(20.dp),
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(innerPadding),
            contentAlignment = Alignment.CenterStart
        ){
            Text(
                text = "Guardados",
                fontSize = 24.sp,
                fontFamily = righteousFont,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth()
            )
        }
        BookmarksList()
    }
}

@Composable
fun BookmarksList() {
    val bookmarks = listOf(
        BookmarkItem(R.drawable.stratocaster, "Fender", "Stratocaster American Professional II"),
        BookmarkItem(R.drawable.jazzmaster, "Squier", "Classic Vibe '60s Jazzmaster"),
        BookmarkItem(R.drawable.lespaul, "Gibson", "Les Paul Standard 60s Heritage")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(bookmarks.size) { index ->
            BookmarkItemView(item = bookmarks[index])
        }
    }
}

@Preview
@Composable
fun BookmarksScreenPreview() {
    MusicRepoTheme {
        BookmarksScreen(innerPadding = PaddingValues(0.dp))
    }
}