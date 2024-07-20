package com.example.myapplication.composables.main_activity

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.composables.MainNavigation
import com.example.myapplication.ui.theme.MyApplicationTheme


@Composable
fun MainActivityScreen() {
    MyApplicationTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            val navController = rememberNavController()
            MainNavigation()
        }
    }
}