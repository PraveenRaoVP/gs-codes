package android.example.newsappcompose.presentation.navgraph

import android.example.newsappcompose.presentation.bookmark.BookmarkScreen
import android.example.newsappcompose.presentation.bookmark.BookmarkViewModel
import android.example.newsappcompose.presentation.home.HomeScreen
import android.example.newsappcompose.presentation.home.HomeViewModel
import android.example.newsappcompose.presentation.newsnavigator.NewsNavigator
import android.example.newsappcompose.presentation.onboarding.OnboardingScreen
import android.example.newsappcompose.presentation.onboarding.OnboardingViewModel
import android.example.newsappcompose.presentation.search.SearchScreen
import android.example.newsappcompose.presentation.search.SearchViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun NavGraph(
    startDestination: String,
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination, modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnboardingScreen.route
        ) {
            composable(route = Route.OnboardingScreen.route) {
                val viewModel: OnboardingViewModel = hiltViewModel()
                OnboardingScreen(
                    event = viewModel::onEvent
                )
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(route = Route.NewsNavigatorScreen.route) {
                NewsNavigator()
            }
        }
    }
}