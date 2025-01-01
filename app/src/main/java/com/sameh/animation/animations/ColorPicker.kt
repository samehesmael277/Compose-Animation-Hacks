package com.sameh.animation.animations

import android.widget.Toast
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateIntOffset
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import java.util.UUID
import kotlin.random.Random

@Composable
fun CallColorPicker() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val density = LocalDensity.current
        var parentSize by remember { mutableStateOf(DpSize.Zero) }

        Column(
            modifier = Modifier.systemBarsPadding(),
        ) {
            val colorHolder = rememberColorHolder(density, parentSize)

            ColorCardStack(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .onGloballyPositioned {
                        parentSize = with(density) {
                            DpSize(
                                width = it.size.width.toDp(),
                                height = it.size.height.toDp(),
                            )
                        }
                    },
                colorHolder = colorHolder
            )

            ColorPicker(
                modifier = Modifier.padding(16.dp),
                colorHolder = colorHolder
            )
        }
    }
}

@Composable
fun ColorCardStack(
    modifier: Modifier,
    colorHolder: ColorHolder
) {
    val context = LocalContext.current

    val showToast = remember(context) {
        { color: Color ->
            Toast.makeText(
                context,
                "${color.format()} copied to clipboard!",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        colorHolder.colorCardStack.forEach { colorCardData ->
            key(colorCardData.id) {
                ColorCard(
                    colorCardData = colorCardData,
                    onClick = {
                        showToast(it)
                    },
                    onPopAnimationFinished = {
                        colorHolder.remove(colorCardData)
                    }
                )
            }
        }
    }

}

@Composable
fun ColorPicker(
    modifier: Modifier,
    colorHolder: ColorHolder
) {
    Column(
        modifier = modifier,
    ) {
        ColorSlider(
            header = "Hue",
            value = colorHolder.currentHue / 360f,
            onValueChange = {
                colorHolder.set(ColorHolder.Type.Hue, it * 360f)
            },
            onPlus = {
                colorHolder.increase(ColorHolder.Type.Hue)
            },
            onMinus = {
                colorHolder.decrease(ColorHolder.Type.Hue)
            }
        )

        ColorSlider(
            header = "Saturation",
            value = colorHolder.currentSaturation,
            onValueChange = {
                colorHolder.set(ColorHolder.Type.Saturation, it)
            },
            onPlus = {
                colorHolder.increase(ColorHolder.Type.Saturation)
            },
            onMinus = {
                colorHolder.decrease(ColorHolder.Type.Saturation)
            }
        )

        ColorSlider(
            header = "Lightness",
            value = colorHolder.currentLightness,
            onValueChange = {
                colorHolder.set(ColorHolder.Type.Lightness, it)
            },
            onPlus = {
                colorHolder.increase(ColorHolder.Type.Lightness)
            },
            onMinus = {
                colorHolder.decrease(ColorHolder.Type.Lightness)
            }
        )

        Button(onClick = {
            colorHolder.pop()
        }) {
            Text(text = "Undo")
        }
    }
}

@Composable
fun ColorSlider(
    header: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    onPlus: () -> Unit,
    onMinus: () -> Unit,
) {
    Column {
        Text(text = header)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Slider(
                modifier = Modifier.weight(1f),
                value = value,
                onValueChange = onValueChange
            )
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onMinus) {
                Text(text = "-")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onPlus) {
                Text(text = "+")
            }
        }
    }
}

@Composable
fun rememberColorHolder(
    density: Density,
    parentSize: DpSize,
): ColorHolder {
    return remember(density, parentSize) {
        ColorHolder(density, parentSize)
    }
}

class ColorHolder internal constructor(
    private val density: Density,
    private val parentSize: DpSize,
) {
    var currentHue by mutableFloatStateOf(0f)
    var currentSaturation by mutableFloatStateOf(0.5f)
    var currentLightness by mutableFloatStateOf(0.5f)

    val colorCardStack = mutableStateListOf<ColorCardData>()

    private val currentColor: Color
        get() = Color.hsl(
            currentHue,
            currentSaturation,
            currentLightness
        )

    init {
        createColorCard()
    }

    fun increase(type: Type) = addUpdatedColor {
        when (type) {
            Type.Hue -> currentHue = (currentHue + 1f).coerceIn(0f, 360f)
            Type.Saturation -> currentSaturation = (currentSaturation + 0.01f).coerceIn(0f, 1f)
            Type.Lightness -> currentLightness = (currentLightness + 0.01f).coerceIn(0f, 1f)
        }
    }

    fun decrease(type: Type) = addUpdatedColor {
        when (type) {
            Type.Hue -> currentHue = (currentHue - 1f).coerceIn(0f, 360f)
            Type.Saturation -> currentSaturation = (currentSaturation - 0.01f).coerceIn(0f, 1f)
            Type.Lightness -> currentLightness = (currentLightness - 0.01f).coerceIn(0f, 1f)
        }
    }

    fun set(type: Type, value: Float) = addUpdatedColor {
        when (type) {
            Type.Hue -> currentHue = value
            Type.Saturation -> currentSaturation = value
            Type.Lightness -> currentLightness = value
        }
    }

    private fun createColorCard() {
        colorCardStack.add(ColorCardData.createRandom(currentColor, density, parentSize))
    }

    private fun addUpdatedColor(block: () -> Unit) {
        block()
        createColorCard()
        if (colorCardStack.size > 110) colorCardStack.removeRange(0, 10)
    }

    fun pop() {
        val indexOfLastNonDirtyColorCard = colorCardStack.indexOfLast { !it.dirty }
        if (indexOfLastNonDirtyColorCard >= 0) {
            val item = colorCardStack[indexOfLastNonDirtyColorCard].copy(
                dirty = true
            )
            colorCardStack.removeAt(indexOfLastNonDirtyColorCard)
            colorCardStack.add(indexOfLastNonDirtyColorCard, item)
        }
    }

    fun remove(colorCardData: ColorCardData) {
        colorCardStack.remove(colorCardData)
    }

    enum class Type {
        Hue, Saturation, Lightness
    }
}

data class ColorCardData(
    // Unique id
    val id: String,

    // The color
    val color: Color,

    // The rotation in degrees of when the card is created offscreen
    val rotationStart: Float,

    // The rotation in degrees of when the card reached the destination
    val rotationEnd: Float,

    // The rotation in degrees of when the card is popped
    val rotationPop: Float,

    // Where the card starts when created
    val translationStart: IntOffset,

    // Where the card rests until it's popped
    val translationEnd: IntOffset,

    // Where the card moves when it's popped
    val translationPop: IntOffset,

    // How high is a popped card tossed up before it falls off the screen
    val popHeight: Int,

    // When true, card is currently being popped. Delete it once translationPop is reached
    val dirty: Boolean = false,
) {
    companion object {
        fun createRandom(color: Color, density: Density, parentSize: DpSize) = ColorCardData(
            id = UUID.randomUUID().toString(),
            color = color,
            rotationStart = Random.nextInt(-90, 90).toFloat(),
            rotationEnd = Random.nextInt(-30, 30).toFloat(),
            rotationPop = Random.nextInt(-720, 720).toFloat(),
            translationStart = density.run {
                IntOffset(
                    x = 0,
                    y = -(parentSize.height * 2).roundToPx()
                )
            },
            translationEnd = density.run {
                IntOffset(
                    x = Random.nextInt(-25, 25).dp.roundToPx(),
                    y = Random.nextInt(-25, 25).dp.roundToPx(),
                )
            },
            translationPop = density.run {
                IntOffset(
                    x = Random.nextInt(-300, 300).dp.roundToPx(),
                    y = (parentSize.height * 2).roundToPx()
                )
            },
            popHeight = density.run { Random.nextInt(100, 300).dp.roundToPx() }
        )
    }
}

@Composable
fun ColorCard(
    colorCardData: ColorCardData,
    onClick: (Color) -> Unit,
    onPopAnimationFinished: () -> Unit,
) {
    var state by remember { mutableStateOf(State.Start) }

    // Animates between the states Start -> End -> Pop
    val transition = updateTransition(targetState = state)

    // Notify parent that the pop animation has finished. Animation driven logic, nice!
    LaunchedEffect(transition.isRunning) {
        if (transition.currentState == State.Pop && !transition.isRunning) {
            onPopAnimationFinished()
        }
    }

    // On composition: Start -> End
    // Once marked as dirty: End -> Pop
    LaunchedEffect(colorCardData.dirty) {
        state = if (colorCardData.dirty) {
            State.Pop
        } else {
            State.End
        }
    }

    val offset by transition.animateIntOffset(
        transitionSpec = {
            if (targetState == State.End) spring() else {
                keyframes {
                    durationMillis = popAnimationDuration

                    // Toss up -> decelerate
                    IntOffset(
                        x = colorCardData.translationPop.x / 2,
                        y = -colorCardData.popHeight
                    ) at popAnimationDuration / 2 using EaseOut

                    // Fall off the screen -> accelerate
                    colorCardData.translationPop at popAnimationDuration using EaseIn
                }
            }
        }, label = ""
    ) {
        when (it) {
            State.Start -> colorCardData.translationStart
            State.End -> colorCardData.translationEnd
            State.Pop -> colorCardData.translationPop
        }
    }

    val degrees by transition.animateFloat(
        transitionSpec = {
            if (targetState == State.End) spring() else tween(
                popAnimationDuration,
                easing = EaseIn
            ) // Match with offset animation when popped
        }, label = ""
    ) {
        when (it) {
            State.Start -> colorCardData.rotationStart
            State.End -> colorCardData.rotationEnd
            State.Pop -> colorCardData.rotationPop
        }
    }

    Card(
        modifier = Modifier
            .size(256.dp)
            .offset {
                offset
            }
            .graphicsLayer {
                rotationZ = degrees
            },
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(12.dp)
    ) {
        Card(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxSize(),
            colors = CardDefaults.cardColors(colorCardData.color),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                onClick(colorCardData.color)
            }
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = colorCardData.color.format(),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }
    }
}

enum class State {
    Start, End, Pop
}

private fun Color.format() = String.format("#%06X", 0xFFFFFF and this.toArgb())

const val popAnimationDuration = 500