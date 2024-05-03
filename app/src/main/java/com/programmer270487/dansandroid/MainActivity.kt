package com.programmer270487.dansandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.programmer270487.dansandroid.presentation.PositionScreen
import com.programmer270487.dansandroid.presentation.PositionViewModel
import com.programmer270487.dansandroid.presentation.ui.theme.AplikasiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AplikasiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = hiltViewModel<PositionViewModel>()
                    val positions = viewModel.pagingFlow.collectAsLazyPagingItems()
                    PositionScreen(positions = positions)
                }
            }
        }
    }
}