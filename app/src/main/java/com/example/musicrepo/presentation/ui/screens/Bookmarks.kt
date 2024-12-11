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
                text = "Guardados",
                modifier = Modifier.width(170.dp),
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = righteousFont
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