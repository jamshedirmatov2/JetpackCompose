package com.wigroup.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.wigroup.jetpackcompose.ui.component.InstagramProfileCard
import com.wigroup.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

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
                        items(models.value) {
                            InstagramProfileCard(model = it) { model ->
                                viewModel.changeFollowingState(model)
                            }
                        }
                    }
                }
            }
        }
    }
}