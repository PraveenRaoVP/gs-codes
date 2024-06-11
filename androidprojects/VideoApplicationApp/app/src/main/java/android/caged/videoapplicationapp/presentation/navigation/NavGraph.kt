package android.caged.videoapplicationapp.presentation.navigation

import android.caged.videoapplicationapp.presentation.home.HomeScreen
import android.caged.videoapplicationapp.presentation.login.LoginScreen
import android.caged.videoapplicationapp.presentation.login.LoginViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Routes.LoginRoute.route) {
            val viewModel: LoginViewModel = hiltViewModel()
            LoginScreen(
                viewModel = viewModel,
                onNavigate = { route, popUp, _ -> onNavigate(navController,route, popUp) }
            )
        }
        composable(route = Routes.HomeRoute.route, arguments = listOf(navArgument("username") { defaultValue = "" })) {
            HomeScreen(
                username = it.arguments?.getString("username") ?: "",
                onNavigate = { route, popUp -> onNavigate(navController, route, popUp) }
            )
        }

        composable(Routes.VideoDetailRoute.route) {
            Box(modifier= Modifier.fillMaxSize()) {
                Text("Video")
            }
        }
    }
}

fun onNavigate(navController: NavHostController, route: String, popUp: String) {
    navController.navigate(route) {
        popUpTo(popUp) {
            inclusive = true
        }
        launchSingleTop = true
    }
}