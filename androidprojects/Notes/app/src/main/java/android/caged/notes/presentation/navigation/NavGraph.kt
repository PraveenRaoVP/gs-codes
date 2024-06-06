package android.caged.notes.presentation.navigation

import android.app.Activity
import android.caged.notes.presentation.onboarding.OnboardingScreen
import android.caged.notes.presentation.onboarding.OnboardingViewModel
import android.caged.notes.presentation.sign_in.SignInScreen
import android.caged.notes.presentation.sign_up.SignUpScreen
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController

@Composable
fun NavGraph(startDestination: String, modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination ) {
            composable(route = Route.OnboardingScreen.route) {
                val viewModel: OnboardingViewModel = hiltViewModel()
                OnboardingScreen(
                    event = { event -> viewModel.onEvent(event) { route -> navController.navigate(route) } },
                    navigateTo = { route ->
                        navController.navigate(route)
                    }
                )
            }

            composable(route = Route.SignInScreen.route) {
                SignInScreen(openAndPopUp = { route, popUp -> navController.navigate(route) {
                    launchSingleTop = true
                    popUpTo(popUp) { inclusive = true}
                } })
            }

            composable(route = Route.SignUpScreen.route) {
                SignUpScreen(openAndPopUp = { route, popUp -> navController.navigate(route) {
                    launchSingleTop = true
                    popUpTo(popUp) { inclusive = true}
                } })
            }
        navigation(startDestination = Route.NotesScreen.route, route = Route.PostSigningIn.route) {
            composable(route = Route.NotesScreen.route) {
                Text("Notes screen")
            }
        }
    }
}