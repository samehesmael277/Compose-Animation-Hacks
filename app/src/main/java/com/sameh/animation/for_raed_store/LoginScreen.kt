package com.sameh.animation.for_raed_store

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sameh.animation.R

@Composable
fun LoginScreen() {
    val primaryColor = Color(0xFF001D25)
    val secondaryColor = Color(0xFF00C7FE)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_raed_store),
                contentDescription = "raed store",
                modifier = Modifier
                    .size(100.dp),
            )
            Text(
                text = "Login in raed store",
                color = Color.Black,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth(),
                supportingText = {
                    Text(
                        text = "error",
                        color = Color.Red,
                        fontSize = 14.sp
                    )
                },
                placeholder = {
                    Text(
                        text = "username *",
                        color = Color.Gray,
                    )
                },
                shape = RoundedCornerShape(10.dp),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp
                )
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "password *",
                        color = Color.Gray,
                    )
                },
                supportingText = {
                    Text(
                        text = "error",
                        color = Color.Red,
                        fontSize = 14.sp
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(10.dp),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp
                ),
                trailingIcon = {
                    if (true) {
                        IconButton(onClick = { }) {
                            Icon(
                                modifier = Modifier.alpha(0.5f),
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = "hide_password",
                                tint = primaryColor
                            )
                        }
                    } else {
                        IconButton(
                            onClick = { }) {
                            Icon(
                                modifier = Modifier.alpha(0.5f),
                                imageVector = Icons.Filled.VisibilityOff,
                                contentDescription = "show_password",
                                tint = primaryColor
                            )
                        }
                    }
                },
            )
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryColor
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Login",
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
            Text(
                text = "new user",
                color = Color.Black,
                fontSize = 14.sp
            )
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen()
}