package edu.gvsu.cis.kmp_wordy
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
@Composable
fun App() {
    MaterialTheme {
        val vm: AppViewModel = viewModel()
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "main"){
            composable("main")
            {
                MainScreen(
                    viewModel= vm,
                    onNavigateToSettings = {navController.navigate("settings")},
                    onNavigateToHistory = {navController.navigate("history")}
                )
            }
            composable("settings"){
                SettingsScreen(
                    viewModel= vm,
                    onConfirm = {navController.popBackStack()},
                    onCancel = {navController.popBackStack()}
                )
            composable("history")
            {
                HistoryScreen(
                    viewModel= vm,
                    onBack = {navController.popBackStack()}
                )
            }
            }
        }
    }
}