package com.example.musicrepo.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicrepo.R
import com.example.musicrepo.presentation.ui.theme.MusicRepoTheme
import com.example.musicrepo.utils.robotoFont

data class BookmarkItem(
    val imageResId: Int,
    val brand: String,
    val model: String
)

@Composable
fun BookmarkItemView(item: BookmarkItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .size(120.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondary
                )
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
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
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    }
}

@Preview
@Composable
fun BookmarkItemPreview() {
    BookmarkItemView(
        item = BookmarkItem(R.drawable.jazzmaster, "Guitarra Eléctrica", "Fender Stratocaster")
    )
}