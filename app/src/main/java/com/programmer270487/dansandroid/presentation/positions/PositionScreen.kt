package com.programmer270487.dansandroid.presentation.positions

import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.programmer270487.dansandroid.domain.Position
import com.programmer270487.dansandroid.util.ErrorMessage


@Composable
fun PositionScreen(
    positions: LazyPagingItems<Position>
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = positions.loadState) {
        if (positions.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error "+ (positions.loadState.refresh as LoadState.Error).error.message,
                LENGTH_SHORT
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if(positions.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                CustomSampleOne()
//                CustomSampleTwo()

                items(positions.itemCount) { index ->
                    Log.d("PositionsList", "itemCount: ${positions.itemCount}")
                    Toast.makeText(context, "itemCount: ${positions.itemCount}", LENGTH_SHORT).show()
                    if(positions[index] != null) {
                        PositionItem(
                            position = positions[index]!!,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                positions.apply {
                    when {
                        loadState.refresh is LoadState.Error -> {
                            val error = positions.loadState.refresh as LoadState.Error
                            item {
                                ErrorMessage(
                                    modifier = Modifier.fillParentMaxSize(),
                                    message = error.error.localizedMessage!!,
                                    onClickRetry = { retry() })
                            }
                        }

                        loadState.append is LoadState.Loading -> {
                            item { CircularProgressIndicator() }
                        }

                        loadState.append is LoadState.Error -> {
                            val error = positions.loadState.append as LoadState.Error
                            item {
                                ErrorMessage(
                                    modifier = Modifier,
                                    message = error.error.localizedMessage!!,
                                    onClickRetry = { retry() })
                            }
                        }
                    }
                }

//                item {
//                    Toast.makeText(context, "itemCount: ${positions.itemCount}", LENGTH_SHORT).show()

//                    if(positions.loadState.append is LoadState.Loading) {
//                        CircularProgressIndicator()
//                    }
//                }
            }
        }
    }
}

fun LazyListScope.CustomSampleOne(position: Position) {
    //  ----- block 1 -----
    item {
        PositionItem(
            position = position,
            modifier = Modifier.fillMaxWidth()
        )
    }
    item {
        Text(text = "header 1")
    }
    item {
        Button(onClick = { }) {
            Text("Button 1")
        }
    }
    //  -------------------
}

fun LazyListScope.CustomSampleTwo() {
    //  ----- block 2 -----
    item {
        Text(text = "Header 2")
    }
    item {
        Button(onClick = { }) {
            Text("Button 2")
        }
    }
    //  -------------------
}