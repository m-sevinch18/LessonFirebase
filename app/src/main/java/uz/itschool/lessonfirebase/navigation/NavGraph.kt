package uz.itschool.lessonfirebase.navigation



import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument


import uz.itschool.lessonfirebase.model.ShowScreen
import uz.itschool.lessonfirebase.model.SignInScreen


@Composable
fun NavGraph (navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screens.SignIn.route)
    {
        composable(route = Screens.SignIn.route){
            SignInScreen(navController)
        }
        composable(route = Screens.Show.route,
            arguments = listOf(
                navArgument("userName"){
                    type = NavType.IntType
                }) ){ entry ->
                val username = entry.arguments?.getString("userName")!!

                ShowScreen(navController = navController, userName = username)
            }


        }









}