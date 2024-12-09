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
                .height(110.dp)
                .padding(innerPadding),
            contentAlignment = Alignment.CenterStart
        ){
            Row(
                modifier = Modifier
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "MusicRepo",
                    fontSize = 32.sp,
                    fontFamily = righteousFont,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
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

        BookmarksList()
    }
}

@Composable
fun BookmarksList(){
    val bookmarks = listOf(
        BookmarkItem(R.drawable.test, "Fender", "Stratocaster American Ultra II"),
        BookmarkItem(R.drawable.test, "Squier", "Jazzmaster Classic Vibe '51"),
        BookmarkItem(R.drawable.test, "Gibson", "Les Paul")
    )

    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(bookmarks.size) { index: Int ->
            BookmarkCard(bookmarks[index])
        }
    }
}

@Composable
fun BookmarkCard(item: BookmarkItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .size(120.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.Gray, shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = item.brand,
                    fontSize = 16.sp,
                    fontFamily = robotoFont,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = item.model,
                    fontSize = 14.sp,
                    fontFamily = robotoFont,
                    color = Color.Gray
                )
            }
        }
    }
}

data class BookmarkItem(val imageResId: Int, val brand: String, val model: String)

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun BookmarksScreenPreview(){
    MusicRepoTheme {
        BookmarksScreen(innerPadding = PaddingValues(0.dp))
    }
}