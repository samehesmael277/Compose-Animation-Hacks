package com.sameh.animation.animations

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CardHeightAnimationWithSpring() {
    var clickedState by remember { mutableStateOf(false) }
    val animation by animateDpAsState(
        targetValue = if (clickedState) 200.dp else 100.dp, label = "card height animation",
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    Button(
        onClick = { clickedState = !clickedState },
        modifier = Modifier.height(animation),
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(text = "Click Me")
    }
}