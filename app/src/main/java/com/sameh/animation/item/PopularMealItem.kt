package com.sameh.animation.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sameh.animation.R

@Preview
@Composable
private fun PopularMealItemPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        PopularMealItem()
    }
}

@Composable
fun PopularMealItem() {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(200.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = {
            // delegate.onMealClick(meal.id)
        }
    ) {
        Image(
            painter = painterResource(id = R.drawable.meal),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .weight(8f),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Meal Name",
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .weight(2f),
        )
    }
}