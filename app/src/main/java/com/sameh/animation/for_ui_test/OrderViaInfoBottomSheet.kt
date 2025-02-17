package com.sameh.animation.for_ui_test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OrderViaInfoBottomSheet() {
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Title",
                    modifier = Modifier
                        .weight(1f)
                )
                IconButton(
                    onClick = {
                        // todo
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "close",
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
            }
            HorizontalDivider()
            ViaBottomSheetPoint()
            ViaBottomSheetPoint()
            ViaBottomSheetPoint()
        }
    }
}

@Composable
fun ViaBottomSheetPoint() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "●"
        )
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Black,
                        fontSize = 16.sp,
                        // fontFamily = FontFamily(Font(R.font.bahij_plain)),
                    )
                ) {
                    append("title 1")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.Gray,
                        fontSize = 16.sp,
                        // fontFamily = FontFamily(Font(R.font.bahij_plain)),
                    )
                ) {
                    append("Des 1")
                }
            },
        )
    }
}

@Preview
@Composable
private fun OrderViaInfoBottomSheetPreview() {
    OrderViaInfoBottomSheet()
}