package com.sameh.animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sameh.animation.animations.CallColorPicker
import com.sameh.animation.ui.theme.AnimationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimationTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    SelectAllOnDoubleClickTextField()
                }
            }
        }
    }
}

@Composable
fun SelectAllOnDoubleClickTextField() {
    var text by remember { mutableStateOf("Double-click to select all text") }
    val textFieldValueState = remember { mutableStateOf(TextFieldValue(text)) }

    // For double-click detection
    var lastClickTime by remember { mutableStateOf(0L) }
    val doubleClickTimeWindow = 300 // milliseconds

    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(
        value = textFieldValueState.value,
        onValueChange = { newValue ->
            textFieldValueState.value = newValue
            text = newValue.text
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(
                width = 1.dp,
                shape = CircleShape,
                color = Color.Black
            )
            .padding(8.dp),
        textStyle = TextStyle(fontSize = 16.sp),
        singleLine = true,
        interactionSource = interactionSource
    )

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect {
            if (it is PressInteraction.Release) {
                val currentTime = System.currentTimeMillis()
                if (currentTime - lastClickTime < doubleClickTimeWindow) {
                    // Double click detected
                    textFieldValueState.value = TextFieldValue(
                        text = text,
                        selection = TextRange(0, text.length)
                    )
                }
                lastClickTime = currentTime
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AnimationTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            SelectAllOnDoubleClickTextField()
        }
    }
}