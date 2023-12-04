package uz.itschool.lessonfirebase.navigation



sealed class Screens(val route: String){
    object Show: Screens("show_screen" + "/{userName}")
    object SignIn: Screens("signIn_screen")


}