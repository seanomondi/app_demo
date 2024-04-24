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
import net.wayv.ui.categories.CharitiesScreen
import net.wayv.ui.categories.FashionScreen
import net.wayv.ui.categories.FestivalsScreen
import net.wayv.ui.categories.FoodScreen
import net.wayv.ui.categories.KidsScreen
import net.wayv.ui.categories.LecturesScreen
import net.wayv.ui.home.HomeScreen
import net.wayv.ui.categories.NightlifeScreen
import net.wayv.ui.categories.PerformingArtsScreen
import net.wayv.ui.categories.SportsScreen
import net.wayv.ui.categories.VisualArtsScreen
import net.wayv.ui.post.AddPostScreen
import net.wayv.ui.post.ViewPostScreen
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


        composable(ROUTE_ADD_POST) {
            AddPostScreen(navController)
        }

        composable(ROUTE_VIEW_POST) {
            ViewPostScreen(navController = navController, viewModel = viewModel())
        }

        composable(ROUTE_PERFORMING_ARTS) {
            PerformingArtsScreen(navController)
        }

        composable(ROUTE_FESTIVALS) {
            FestivalsScreen(navController)
        }

        composable(ROUTE_NIGHTLIFE) {
            NightlifeScreen(navController)
        }

        composable(ROUTE_CHARITIES) {
            CharitiesScreen(navController)
        }

        composable(ROUTE_VISUAL_ARTS) {
            VisualArtsScreen(navController)
        }

        composable(ROUTE_FOOD) {
            FoodScreen(navController)
        }

        composable(ROUTE_FASHION) {
            FashionScreen(navController)
        }

        composable(ROUTE_SPORTS) {
            SportsScreen(navController)
        }

        composable(ROUTE_LECTURES) {
            LecturesScreen(navController)
        }

        composable(ROUTE_KIDS) {
            KidsScreen(navController)
        }


    }
}
