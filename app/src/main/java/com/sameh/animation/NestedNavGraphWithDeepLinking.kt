package com.sameh.animation

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

@Composable
fun NestedNavGraphWithDeepLinking(url: String = "app://zonak.net") {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                    navController.navigate("app")
                }) {
                    Text(text = "To app")
                }
            }
        }
        composable(
            "app",
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "$url/{type}/{id}/{afCode}"
                    action = Intent.ACTION_VIEW
                },
            ),
            arguments = listOf(
                navArgument("type") {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument("afCode") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { entry ->
            val typeArgs = entry.arguments?.getString("type")
            val idArgs = entry.arguments?.getInt("id")
            val afCodeArgs = entry.arguments?.getString("afCode")

            var startDes = "home"
            (typeArgs != null && idArgs != null && afCodeArgs != null).let {
                if (typeArgs == "place")
                    startDes = "place/$idArgs/$afCodeArgs"
                else if (typeArgs == "offer")
                    startDes = "offer/$idArgs/$afCodeArgs"
            }

            val navController2 = rememberNavController()
            NavHost(navController = navController2, startDestination = startDes) {
                composable("home") {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = {
                            navController2.navigate("place/123/asd123asd")
                        }) {
                            Text(text = "To place")
                        }
                    }
                }
                composable(
                    route = "place/{id}/{afCode}",
                    arguments = listOf(
                        navArgument("id") {
                            type = NavType.IntType
                            defaultValue = idArgs ?: -1
                        },
                        navArgument("afCode") {
                            type = NavType.StringType
                            defaultValue = afCodeArgs.orEmpty()
                        }
                    )
                ) { entry ->
                    val id = entry.arguments?.getInt("id")
                    val afCode = entry.arguments?.getString("afCode")
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "place")
                        Text(text = "The id is $id")
                        Text(text = "The afCode is $afCode")
                        Button(onClick = {
                            navController2.navigate("offer/456/asd456asd")
                        }) {
                            Text(text = "To offer")
                        }
                    }
                }
                composable(
                    route = "offer/{id}/{afCode}",
                    arguments = listOf(
                        navArgument("id") {
                            type = NavType.IntType
                            defaultValue = idArgs ?: -1
                        },
                        navArgument("afCode") {
                            type = NavType.StringType
                            defaultValue = afCodeArgs.orEmpty()
                        }
                    )
                ) { entry ->
                    val id = entry.arguments?.getInt("id")
                    val afCode = entry.arguments?.getString("afCode")
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "offer")
                        Text(text = "The id is $id")
                        Text(text = "The afCode is $afCode")
                    }
                }
            }
        }
    }
}