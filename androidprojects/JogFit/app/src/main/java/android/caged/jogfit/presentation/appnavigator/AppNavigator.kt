package android.caged.jogfit.presentation.appnavigator

import android.caged.jogfit.presentation.components.ActionBar
import android.caged.jogfit.presentation.components.BottomNavBar
import android.caged.jogfit.presentation.components.DrawerContent
import android.caged.jogfit.presentation.home.HomeScreen
import android.caged.jogfit.presentation.home.HomeViewModel
import android.caged.jogfit.presentation.navigation.Routes
import android.caged.jogfit.presentation.navigation.navigateAndPopUp
import android.caged.jogfit.presentation.navigation.navigateTo
import android.caged.jogfit.presentation.profilepage.ProfilePageScreen
import android.caged.jogfit.presentation.profilepage.ProfilePageViewModel
import android.caged.jogfit.presentation.settings.SettingsScreen
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@Composable
fun AppNavigator(
    viewModel: AppNavigatorViewModel,
    navigatePopUp: (String, String) -> Unit
) {
    val bottomNavItems = remember {
        listOf(
            BottomNavigationItem(
                route = Routes.HomeRoute.route,
                icon = Icons.Default.Home,
                label = "Home"
            ),
            BottomNavigationItem(
                route = Routes.StatisticsRoute.route,
                icon = Icons.Default.DateRange,
                label = "Stats"
            ),
            BottomNavigationItem(
                route = Routes.ProfileRoute.route,
                icon = Icons.Default.Person,
                label = "Profile"
            )
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Routes.HomeRoute.route ||
                backStackState?.destination?.route == Routes.StatisticsRoute.route ||
                backStackState?.destination?.route == Routes.ProfileRoute.route
    }

    selectedItem = when (backStackState?.destination?.route) {
        Routes.HomeRoute.route -> 0
        Routes.StatisticsRoute.route -> 1
        Routes.ProfileRoute.route -> 2
        else -> 0
    }

    val snackbarHostState = viewModel.snackbarHostState

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            DrawerContent(
                navController = navController,
                onLogoutClick = {
                    coroutineScope.launch {
                        drawerState.close()
                        viewModel.signOut(navigatePopUp)
                    }
                },
                profilePictureUrl = viewModel.profileImageUrl.toString(),
                profileName = viewModel.displayName!!
            )
        },
        drawerState = drawerState
    ) {
        Log.i("AppNavigator", "Current route: ${backStackState?.destination?.route}")
        Scaffold(
            snackbarHost = { SnackbarHost(hostState = snackbarHostState)},
            topBar = {
                if (backStackState?.destination?.route != Routes.SettingsScreen.route ||
                    backStackState?.destination?.route != Routes.ProfileSettings.route
                ) {
                    ActionBar(
                        title = "",
                        onMenuClick = {
                            // TODO: Implement drawer
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        },
                        onSettingsClick = {
                            navigateTo(Routes.SettingsScreen.route, navController)
                        }
                    )
                }
            },
            bottomBar = {
                if (isBottomBarVisible) {
                    BottomNavBar(
                        items = bottomNavItems,
                        selectedItem = selectedItem,
                        onItemSelect = { index ->
                            when (index) {
                                0 -> {
                                    viewModel.previousScreen.value = Routes.HomeRoute.route
                                    navigateToTab(navController, Routes.HomeRoute.route)
                                }

                                1 -> {
                                    viewModel.previousScreen.value = Routes.StatisticsRoute.route
                                    navigateToTab(navController, Routes.StatisticsRoute.route)
                                }

                                2 -> {
                                    viewModel.previousScreen.value = Routes.ProfileRoute.route
                                    navigateToTab(navController, Routes.ProfileRoute.route)
                                }
                            }
                        }
                    )
                }
            }
        ) { innerPadding ->
            val bottomPadding = innerPadding.calculateBottomPadding()
            NavHost(
                navController = navController,
                startDestination = Routes.HomeRoute.route,
                modifier = Modifier.padding(bottom = bottomPadding)
            ) {
                composable(route = Routes.HomeRoute.route) {
                    val viewModel: HomeViewModel = hiltViewModel()
                    HomeScreen(
                        navigatePopUp = { route, popUp ->
                            navigateAndPopUp(
                                route,
                                popUp,
                                navController
                            )
                        },
                        navigateTo = { route -> navigateTo(route, navController) },
                        snackbarHostState = snackbarHostState,
                        onSnackbarEvent = viewModel::onSnackbarEvent
                    )
                }

                composable(route = Routes.SettingsScreen.route) {
                    SettingsScreen(
                        navigatePopUp = { route, popUp ->
                            navigateAndPopUp(
                                route,
                                popUp,
                                navController
                            )
                        },
                        previousScreen = viewModel.previousScreen.value
                    )
                }


                composable(route = Routes.StatisticsRoute.route) {
                    // TODO: Implement StatisticsScreen
                }

                composable(route = Routes.ProfileRoute.route) {
                    val viewModel: ProfilePageViewModel = hiltViewModel()
                    ProfilePageScreen(
                        profileImageUrl = viewModel.profileImageUrl,
                        displayName = viewModel.displayName,
                        email = viewModel.email,
                        navigateTo = { route -> navigateTo(route, navController) }
                    )
                }

                composable(route = Routes.ProfileSettings.route) {
                    // TODO: Implement ProfileSettingsScreen
                }
            }
        }
    }
}

data class BottomNavigationItem(
    val route: String,
    val icon: ImageVector,
    val label: String
)

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigatePopBackStack(navController: NavController) {
    navController.popBackStack()
}

private fun navigateToDetails(navController: NavController) {
//    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
//    navController.navigate(Route.DetailsScreen.route)
}