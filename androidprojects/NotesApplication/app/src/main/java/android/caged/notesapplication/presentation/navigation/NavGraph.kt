package android.caged.notesapplication.presentation.navigation

import android.caged.notesapplication.presentation.notes_screen.NotesScreen
import android.caged.notesapplication.presentation.onboarding.OnboardingScreen
import android.caged.notesapplication.presentation.onboarding.OnboardingViewModel
import android.caged.notesapplication.presentation.sign_in.SignInScreen
import android.caged.notesapplication.presentation.sign_in.SignInViewModel
import android.caged.notesapplication.presentation.sign_up.SignUpScreen
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Routes.OnboardingRoute.route) {
            val viewModel: OnboardingViewModel = hiltViewModel()
            OnboardingScreen(
                event = viewModel::onEvent,
                navigateTo = { route -> navController.navigate(route) {
                    popUpTo(Routes.OnboardingRoute.route) { inclusive = true }
                } }
            )
        }

        composable(Routes.SignInRoute.route) {
            val viewModel: SignInViewModel = hiltViewModel()
            SignInScreen(
                navigateAndPopUp = { route, popUpRoute -> navController.navigate(route) {
                    popUpTo(popUpRoute) { inclusive = true }
                } },
                viewModel = viewModel
            )
        }

        composable(Routes.SignUpRoute.route) {
            SignUpScreen(navigateAndPopUp = { route, popUpRoute -> navController.navigate(route) {
                popUpTo(popUpRoute) { inclusive = true }
            } })
        }

        composable(Routes.NotesRoute.route) {
            NotesScreen(
                navigateTo = { route -> navController.navigate(route) },
                navigateAndPopUp = { route, popUpRoute -> navController.navigate(route) {
                    popUpTo(popUpRoute) { inclusive = true }
                } }
            )
        }
    }
}

