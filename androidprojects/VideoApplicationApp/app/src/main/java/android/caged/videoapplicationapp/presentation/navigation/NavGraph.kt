package android.caged.videoapplicationapp.presentation.navigation

import android.caged.videoapplicationapp.presentation.call.CallScreen
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
import androidx.navigation.NavType
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
                onNavigate = { route, popUp, _ -> onNavigate(navController, route, popUp) }
            )
        }
        composable(
            route = Routes.HomeRoute.route,
            arguments = listOf(navArgument("username") { defaultValue = "" })
        ) {
            HomeScreen(
                username = it.arguments?.getString("username") ?: "",
                onNavigate = { route, popUp -> onNavigate(navController, route, popUp) }
            )
        }

        composable(Routes.VideoDetailRoute.route + "/{isVideoCall}/{target}/{isCaller}",
            arguments = listOf(
                navArgument("isVideoCall") {
                    defaultValue = false
                    type = NavType.BoolType
                },
                navArgument("target") {
                    defaultValue = ""
                    type = NavType.StringType
                },
                navArgument("isCaller") {
                    defaultValue = false
                    type = NavType.BoolType
                }
            )
        ) { backStackEntry ->
            val isVideoCall = backStackEntry.arguments?.getBoolean("isVideoCall") ?: false
            val target = backStackEntry.arguments?.getString("target") ?: ""
            val isCaller = backStackEntry.arguments?.getBoolean("isCaller") ?: false
            CallScreen(
                target = target,
                isVideoCall = isVideoCall,
                isCaller = isCaller,
                onNavigate = {})
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