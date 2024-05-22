package com.sameh.animation.animations

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sameh.animation.R

@Preview
@Composable
private fun ItemPreview() {
    Screen()
}

@Composable
fun Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Item()
    }
}

@Composable
fun Item() {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.5f),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(
                        Color.LightGray.copy(alpha = 0.5f),
                        RoundedCornerShape(20.dp)
                    )
                    .align(Alignment.BottomCenter)
            )
            Image(
                painter = painterResource(id = R.drawable.braed),
                contentDescription = "category",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f),
                contentScale = ContentScale.FillBounds
            )
            Image(
                painter = painterResource(id = R.drawable.shadow),
                contentDescription = "category",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                alpha = 0.5f
            )
        }
        Text(
            text = "Bred",
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            fontSize = 16.sp
        )
    }
}
