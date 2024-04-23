package net.wayv.navigation


import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.wayv.ui.auth.LoginScreen
import net.wayv.ui.auth.SignupScreen
import net.wayv.ui.bookmarks.BookmarksScreen
import net.wayv.ui.explore.ExploreScreen
import net.wayv.ui.home.HomeScreen
import net.wayv.ui.profile.AddPostScreen
import net.wayv.ui.profile.ProfileScreen
import net.wayv.ui.splash.SplashScreen


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SPLASH


) {
    BackHandler {
        navController.popBackStack()

    }
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        composable(ROUTE_SPLASH) {
            SplashScreen(navController)
        }


        composable(ROUTE_LOGIN) {
            LoginScreen(navController)
        }


        composable(ROUTE_SIGNUP) {
            SignupScreen(navController)
        }


        composable(ROUTE_HOME) {
            HomeScreen(navController)
        }


        composable(ROUTE_EXPLORE) {
            ExploreScreen(navController)
        }


        composable(ROUTE_BOOKMARKS) {
            BookmarksScreen(navController)
        }


        composable(ROUTE_PROFILE) {
            ProfileScreen(navController)
        }


        composable(ROUTE_ADD_POST) {
            AddPostScreen(navController)
        }

    }
}
