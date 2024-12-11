package com.example.musicrepo.presentation.components

import android.graphics.drawable.PaintDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.widget.TextViewCompat.AutoSizeTextType
import coil.compose.AsyncImage
import com.example.musicrepo.R
import com.example.musicrepo.presentation.ui.theme.MusicRepoTheme
import com.example.musicrepo.utils.righteousFont
import com.example.musicrepo.utils.robotoFont
import kotlin.math.max


@Composable
fun CardCarrousel(nombre : String, modelo : String, imagen : String){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column (
            modifier = Modifier
                .padding(20.dp),
            horizontalAlignment = Alignment.End
        ){
            Text(
                text = nombre,
                fontSize = 17.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(),
                fontFamily = robotoFont,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = modelo,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.tertiary,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(),
                fontFamily = robotoFont
            )

            Box (
                contentAlignment = Alignment.Center,
                modifier = Modifier.height(250.dp)
            ) {
//                AsyncImage(model = "https://png.pngtree.com/png-clipart/20231016/original/pngtree-guitar-ace-playing-card-chance-photo-png-image_13318398.png", contentDescription = "Guitarra")

                Text(
                    text = nombre.uppercase(),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontFamily = righteousFont,
                    textAlign = TextAlign.Center,
                    lineHeight = 37.sp
                )

//                Image(
//                    painter = painterResource(id = R.drawable.guitarra),
//                    contentDescription = "Guitarra",
//                    contentScale = ContentScale.Crop,
//                )
                AsyncImage(
                    model = imagen,
                    contentDescription = modelo,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Preview
@Composable
fun CardCarrouselPreview(){
    MusicRepoTheme {
        CardCarrousel(nombre = "Guitarra electrica", modelo = "Fender Stratocaster", imagen = "lol")
    }
}