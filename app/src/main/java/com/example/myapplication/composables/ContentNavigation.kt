package com.example.myapplication.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.appComponent
import com.example.myapplication.composables.edit_profile.DaggerEditProfileComponent
import com.example.myapplication.composables.edit_profile.EditProfileScreen
import com.example.myapplication.composables.edit_profile.EditProfileViewModel

@Composable
fun ContentNavigation() {
    val innerNavController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(50.dp)
            ) {
                Box(Modifier.weight(1f))
                IconButton(onClick = {
                    innerNavController.navigate(ContentScreens.EditProfile.name)
                }) {
                    Icon(Icons.Filled.Person, contentDescription = null)
                }
                Box(Modifier.weight(1f))
            }
        }
    ) {
        NavHost(
            modifier = Modifier.padding(
                top = it.calculateTopPadding(),
                bottom = it.calculateBottomPadding()
            ),
            navController = innerNavController,
            startDestination = ContentScreens.EditProfile.name
        ) {
            composable(ContentScreens.EditProfile.name) {
                val appComponent = LocalContext.current.appComponent()
                val component = DaggerEditProfileComponent.factory().create(appComponent)
                val viewModel: EditProfileViewModel = daggerViewModel {
                    component.getViewModel()
                }
                EditProfileScreen(viewModel)
            }
        }
    }
}

enum class ContentScreens(name: String) {
    EditProfile("editProfileScreen")
}