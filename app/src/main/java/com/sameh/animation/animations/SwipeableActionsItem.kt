package com.sameh.animation.animations

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Archive
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun SwipeableActionsItem() {
    val context = LocalContext.current

    val archive = SwipeAction(
        icon = rememberVectorPainter(Icons.TwoTone.Archive),
        background = Color.Green,
        onSwipe = {
            Toast.makeText(context, "Archived", Toast.LENGTH_SHORT).show()
        }
    )

    val snooze = SwipeAction(
        icon = { Text("Snooze") },
        background = Color.Yellow,
        isUndo = true,
        onSwipe = {
            Toast.makeText(context, "Snoozed", Toast.LENGTH_SHORT).show()
        },
    )

    SwipeableActionsBox(
        startActions = listOf(archive),
        endActions = listOf(snooze)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(
                    horizontal = 16.dp,
                    vertical = 32.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Item: ", fontSize = 20.sp)
            Text(text = "our text", fontSize = 16.sp)
        }
    }
}