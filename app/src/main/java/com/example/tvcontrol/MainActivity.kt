package com.example.tvcontrol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tvcontrol.destionations.Search
import com.example.tvcontrol.ui.theme.TVControlTheme
import com.example.tvcontrol.view.SearchDevices

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val tvControlViewModel by viewModels<TVControlViewModel> {TVControlViewModelFactory(application)}

        setContent {
            TVControlTheme {
                CustomNavHost(tvControlViewModel)
            }
        }
    }

    @Composable
    private fun CustomNavHost(tvControlViewModel: TVControlViewModel) {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = Search) {
            composable<Search>{ SearchDevices(viewModel = tvControlViewModel)}
        }
    }
}