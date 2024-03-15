package com.wigroup.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wigroup.jetpackcompose.ui.component.InstagramProfileCard
import com.wigroup.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    val models = viewModel.models.observeAsState(listOf())
                    LazyColumn {
                        items(models.value, key = { it.id }) { model ->
                            SwipeToDismissBox(
                                state = rememberSwipeToDismissBoxState(confirmValueChange = {
                                    viewModel.delete(model)
                                    true
                                }),
                                enableDismissFromStartToEnd = false,
                                backgroundContent = {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(16.dp)
                                            .background(Color.Red.copy(alpha = 0.5f)),
                                        contentAlignment = Alignment.CenterEnd
                                    ) {
                                        Text(
                                            modifier = Modifier.padding(16.dp),
                                            text = "Delete Item",
                                            color = Color.White,
                                            fontSize = 24.sp
                                        )
                                    }
                                },
                            ) {
                                InstagramProfileCard(model = model) { model ->
                                    viewModel.changeFollowingState(model)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}