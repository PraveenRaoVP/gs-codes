package android.caged.jogfit.presentation.navigation

import android.caged.jogfit.presentation.appnavigator.AppNavigator
import android.caged.jogfit.presentation.appnavigator.AppNavigatorViewModel
import android.caged.jogfit.presentation.auth.login.ForgotPasswordScreen
import android.caged.jogfit.presentation.auth.login.LoginAlternate
import android.caged.jogfit.presentation.auth.login.LoginScreen
import android.caged.jogfit.presentation.auth.login.LoginViewModel
import android.caged.jogfit.presentation.auth.signup.SignUpScreen
import android.caged.jogfit.presentation.auth.signup.SignUpViewModel
import android.caged.jogfit.presentation.components.ActionBar
import android.caged.jogfit.presentation.home.HomeScreen
import android.caged.jogfit.presentation.home.HomeViewModel
import android.caged.jogfit.presentation.settings.SettingsScreen
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation

@Composable
fun NavGraph(startDestination: String, modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            startDestination = Routes.LoginWithGoogleRoute.route,
            route = Routes.AppStartRoute.route
        ) {
            composable(route = Routes.LoginWithGoogleRoute.route) {
                val viewModel: LoginViewModel = hiltViewModel()
                LoginScreen(
                    onEvent = viewModel::onEvent,
                    navigateTo = { route -> navigateTo(route, navController) },
                    navigateToPopUp = { route, popUp ->
                        navigateAndPopUp(
                            route,
                            popUp,
                            navController
                        )
                    }
                )
            }
            composable(route = Routes.LoginWithEmailPasswordRoute.route) {
                val viewModel: LoginViewModel = hiltViewModel()
                LoginAlternate(
                    state = viewModel.uiState.value,
                    onEvent = viewModel::onEvent,
                    navigateTo = { route -> navigateTo(route, navController) },
                    navigateToPopUp = { route, popUp ->
                        navigateAndPopUp(
                            route,
                            popUp,
                            navController
                        )
                    }
                )
            }
            composable(route = Routes.SignUpRoute.route) {
                val viewModel: SignUpViewModel = hiltViewModel()
                SignUpScreen(
                    state = viewModel.uiState.value,
                    onEvent = viewModel::onEvent,
                    navigatePopUp = { route, popUp ->
                        navigateAndPopUp(
                            route,
                            popUp,
                            navController
                        )
                    })
            }
            composable(route = Routes.ForgotPassword.route) {
                val viewModel: LoginViewModel = hiltViewModel()
                ForgotPasswordScreen(
                    state = viewModel.uiState.value,
                    onEvent = viewModel::onEvent,
                    navigateToPopUp = { route, popUp -> navigateAndPopUp(route, popUp, navController) }
                )
            }
        }
        navigation(
            startDestination = Routes.AppNavigationScreen.route,
            route = Routes.AppNavigatorRoute.route
        ) {
            composable(route = Routes.AppNavigationScreen.route) {
                val viewModel: AppNavigatorViewModel = hiltViewModel()
                AppNavigator(
                    viewModel,
                    navigatePopUp = { route, popUp ->
                        navigateAndPopUp(
                            route,
                            popUp,
                            navController
                        )
                    }
                )
            }
        }
    }
}

fun navigateTo(route: String, navHostController: NavHostController) {
    navHostController.navigate(route)
}

fun navigateAndPopUp(route: String, popUp: String, navHostController: NavHostController) {
    navHostController.navigate(route) {
        popUpTo(popUp) {
            inclusive = true
        }
    }
}