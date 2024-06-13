package android.caged.videoapplicationapp.presentation.navigation

sealed class Routes(val route: String) {
    data object LoginRoute : Routes("login")
    data object HomeRoute : Routes("home/{username}") {
        fun createRoute(username: String) = "home/$username"
    }
    data object VideoDetailRoute : Routes("videoDetail") {
//        fun createRoute() =
    }
}