package com.sameh.animation.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedVisibilityWithRows() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(10) {
            DataRow()
        }
    }
}

@Composable
fun DataRow() {
    var expanded by remember { mutableStateOf(false) }
    val iconRotate: Float by animateFloatAsState(
        if (expanded) 90f else 0f, label = ""
    )
    val iconRotateAR: Float by animateFloatAsState(
        if (expanded) -90f else 0f, label = ""
    )

    Card(
        shape = RoundedCornerShape(size = 5.dp),
        border = BorderStroke(1.dp, Color.Black),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.animateContentSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(60.dp)
                    .clickable {
                        expanded = !expanded
                    }
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column {
                    Text(
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        text = "hello"
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                            text = "how are you"
                        )

                        Icon(
                            modifier = Modifier
                                .size(12.dp),
                            imageVector = Icons.Filled.Build,
                            contentDescription = "",
                            tint = Color.Black
                        )

                        Icon(
                            modifier = Modifier.size(12.dp),
                            imageVector = Icons.Filled.Done,
                            contentDescription = "",
                            tint = Color(0xFF188038)
                        )
                    }
                }

                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "show",
                    modifier = Modifier
                        .size(25.dp)
                        .rotate(
                            if (LocalLayoutDirection.current == LayoutDirection.Ltr) iconRotate
                            else
                                iconRotateAR
                        ),
                    tint = Color.Black
                )
            }

            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(
                    spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMediumLow
                    )
                ),
                exit = shrinkVertically(
                    spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMediumLow
                    )
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Divider(
                        thickness = 1.dp,
                        color = Color.Black
                    )
                    Box(
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "here 1",
                            )
                            Text(
                                text = "********",
                            )
                            Text(
                                text = "how are you man",
                            )
                            Text(
                                text = "********",
                            )
                        }
                        IconButton(
                            onClick = {
                            }
                        ) {
                            Icon(
                                modifier = Modifier.size(20.dp),
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = "edit place",
                                tint = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}
