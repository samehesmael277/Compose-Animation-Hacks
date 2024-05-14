package com.sameh.animation.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NestedIconButtonAnimation() {
    var visibilityState by remember { mutableStateOf(true) }
    Button(onClick = { visibilityState = !visibilityState }) {
        Text(text = "Click")
    }
    Spacer(modifier = Modifier.height(32.dp))
    AnimatedVisibility(
        visible = visibilityState,
        enter = fadeIn(
            animationSpec = tween(500)
        ),
        exit = fadeOut(
            animationSpec = tween(500)
        )
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.LightGray)
        ) {
            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier
                    .padding(32.dp)
                    .size(40.dp)
                    .animateEnterExit(
                        enter = slideInVertically{it*2},
                        exit = slideOutVertically{-it*2}
                    )
            )
        }
    }
}