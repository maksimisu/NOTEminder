package com.maksimisu.noteminder.presentation.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.maksimisu.noteminder.presentation.navigation.Screens
import com.maksimisu.noteminder.presentation.ui.components.CardButton
import com.maksimisu.noteminder.presentation.ui.components.NoteItem
import com.maksimisu.noteminder.presentation.ui.theme.DarkViolet
import com.maksimisu.noteminder.presentation.ui.theme.LightGray
import com.maksimisu.noteminder.presentation.ui.theme.NOTEminderTheme

@Composable
fun MainScreen(navHostController: NavHostController) {

    val viewModel = hiltViewModel<MainViewModel>()
    val notes = viewModel.notes.observeAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "NOTEminder",
                        fontSize = 30.sp,
                        color = LightGray
                    )
                },
                backgroundColor = DarkViolet,
                contentColor = LightGray,
                modifier = Modifier
                    .height(64.dp)
            )
        },
        content = {
            it.calculateTopPadding()
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                ) {
                    CardButton(
                        text = "NEW NOTE...",
                        icon = Icons.Filled.Add,
                        modifier = Modifier
                            .clickable {
                                navHostController.navigate(Screens.EditScreen.route)
                            }
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    notes?.forEach {
                        item {
                            NoteItem(
                                note = it,
                                modifier = Modifier
                                    .clickable {
                                        navHostController.navigate(Screens.DetailedScreen.route + "/${it.id}")
                                    }
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun MainPreview() {
    NOTEminderTheme {
        MainScreen(navHostController = rememberNavController())
    }
}