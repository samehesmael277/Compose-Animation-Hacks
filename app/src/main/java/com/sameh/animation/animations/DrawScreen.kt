package com.sameh.animation.animations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.ak1.drawbox.DrawBox
import io.ak1.drawbox.rememberDrawController

@Composable
fun DrawScreen() {
    val controller = rememberDrawController()

    Column {
        DrawBox(
            drawController = controller,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f, true),
            backgroundColor = Color.White,
            bitmapCallback = { _, _ ->

            },
            trackHistory = { _, _ ->

            }
        )
    }
}