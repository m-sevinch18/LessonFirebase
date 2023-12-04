package uz.itschool.lessonfirebase.model

import android.window.SplashScreen
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import uz.itschool.lessonfirebase.navigation.NavGraph

@Composable
fun ShowScreen(navController: NavHostController, userName:String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Welcome, $userName!")
        // Other content for the ShowScreen
    }
}

@Preview(showBackground = true)
@Composable
fun showSplash(){
    val navController = rememberNavController()
    NavGraph(navController = navController)
    ShowScreen(navController = navController,"")
}