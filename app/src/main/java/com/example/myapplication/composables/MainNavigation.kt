package com.example.myapplication.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.appComponent
import com.example.myapplication.composables.check_code.CheckCodeScreen
import com.example.myapplication.composables.check_code.CheckCodeViewModel
import com.example.myapplication.composables.check_code.DaggerCheckCodeComponent
import com.example.myapplication.composables.login.DaggerLoginComponent
import com.example.myapplication.composables.login.LoginScreen
import com.example.myapplication.composables.login.LoginViewModel
import com.example.myapplication.composables.login.Prefix
import com.example.myapplication.composables.registration.DaggerRegistrationComponent
import com.example.myapplication.composables.registration.RegistrationScreen
import com.example.myapplication.composables.registration.RegistrationViewModel
import com.example.myapplication.composables.splash.DaggerSplashComponent
import com.example.myapplication.composables.splash.SplashScreen
import com.example.myapplication.composables.splash.SplashViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    MyApplicationTheme {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = "splashScreen"
        ) {
            composable("content") {
                ContentNavigation()
            }
            composable("loginScreen") {
                val appComponent = LocalContext.current.appComponent()
                val component = DaggerLoginComponent.factory().create(appComponent)
                val viewModel: LoginViewModel = daggerViewModel {
                    component.getViewModel()
                }
                LoginScreen(viewModel, navController)
            }
            composable(
                "checkCodeScreen/{prefix}&{phone}"
            ) {
                val appComponent = LocalContext.current.appComponent()
                val component = DaggerCheckCodeComponent.factory().create(appComponent)
                val viewModel: CheckCodeViewModel = daggerViewModel {
                    component.getViewModel()
                }
                val phone = it.arguments?.getString("phone")

                val prefix = it.arguments?.getString("prefix")
                if (phone != null && prefix != null) {
                    val phoneType = Prefix.valueOf(prefix)
                    CheckCodeScreen(viewModel, navController, phoneType, phone)
                }
            }
            composable("registrationScreen/{prefix}&{phone}") {
                val appComponent = LocalContext.current.appComponent()
                val component = DaggerRegistrationComponent.factory().create(appComponent)
                val viewModel: RegistrationViewModel = daggerViewModel {
                    component.getViewModel()
                }
                val phone = it.arguments?.getString("phone")

                val prefix = it.arguments?.getString("prefix")
                if (phone != null && prefix != null) {
                    val phoneType = Prefix.valueOf(prefix)
                    RegistrationScreen(viewModel, navController, phoneType, phone)
                }
            }
            composable("splashScreen") {
                val appComponent = LocalContext.current.appComponent()
                val component = DaggerSplashComponent.factory().create(appComponent)
                val viewModel: SplashViewModel = daggerViewModel {
                    component.getViewModel()
                }
                SplashScreen(navController, viewModel)
            }
        }
    }
}