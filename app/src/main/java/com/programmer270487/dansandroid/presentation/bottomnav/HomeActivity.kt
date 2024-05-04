package com.programmer270487.dansandroid.presentation.bottomnav

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.android.gms.auth.api.identity.Identity
import com.programmer270487.dansandroid.presentation.positions.PositionScreen
import com.programmer270487.dansandroid.presentation.positions.PositionViewModel
import com.programmer270487.dansandroid.presentation.googlesignin.GoogleAuthUiClient
import com.programmer270487.dansandroid.presentation.googlesignin.ProfileScreen
import com.programmer270487.dansandroid.ui.theme.DansAndroidTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            applicationContext,
            Identity.getSignInClient(applicationContext)
        )
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DansAndroidTheme {
                val navController = rememberNavController()

                val items = listOf(
                    Screen.ScreenPosition,
                    Screen.ScreenProfile
                )

                Scaffold(
                    bottomBar = {
                        BottomNavigation {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentRoute = navBackStackEntry?.destination?.route

                            items.forEach { screen ->
                                BottomNavigationItem(
                                    icon = { Icon(screen.icon, contentDescription = null) },
                                    label = { androidx.compose.material.Text(screen.title) },
                                    selected = currentRoute == screen.route,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            // Pop up to the start destination of the graph
                                            popUpTo(navController.graph.startDestinationRoute!!) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "positionScreen"
                    ) {
                        /*
                        Job List
                         */
                        composable("positionScreen") {
                            val viewModel = hiltViewModel<PositionViewModel>()
                            val positions = viewModel.pagingFlow.collectAsLazyPagingItems()
                            PositionScreen(positions = positions)
                        }

                        /*
                        Profile
                         */
                        composable("profileScreen") {
                            ProfileScreen(
                                userData = googleAuthUiClient.getSignedInUser(),
                                onSignOut = {
                                    lifecycleScope.launch {
                                        googleAuthUiClient.signOut()
                                        Toast.makeText(applicationContext, "You just signed out", Toast.LENGTH_SHORT).show()
//                                        navController.popBackStack()
                                        finish()
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object ScreenPosition : Screen("positionScreen", "Home", Icons.Default.Home)
    object ScreenProfile : Screen("profileScreen", "Account", Icons.Default.AccountCircle)
}