package com.example.myapplication.composables.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Preview(showBackground = true)
@Composable
fun RegistrationPreview() {
    SplashScreen(rememberNavController(), viewModel())
}

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel
) {
    LaunchedEffect(viewModel.splashState) {
        when (viewModel.splashState) {
            SplashState.Auth -> navController.navigate("loginScreen") {
                popUpTo("splashScreen") { inclusive = true }
            }
            SplashState.Content -> navController.navigate("content") {
                popUpTo("splashScreen") { inclusive = true }
            }
            SplashState.Loading -> Unit
        }
    }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(100.dp)
        )
    }
}