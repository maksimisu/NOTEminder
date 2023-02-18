package com.maksimisu.noteminder.presentation.screens.detailed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.maksimisu.noteminder.presentation.navigation.Screens
import com.maksimisu.noteminder.presentation.ui.theme.DarkViolet
import com.maksimisu.noteminder.presentation.ui.theme.LightGray

@Composable
fun DetailedScreen(
    navHostController: NavHostController,
    id: Long
) {

    val viewModel = hiltViewModel<DetailedViewModel>()
    val note = viewModel.note.observeAsState().value
    id.let {
        viewModel.getNoteById(it)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "NOTE DETAILS",
                        fontSize = 30.sp,
                        color = LightGray
                    )
                },
                backgroundColor = DarkViolet,
                contentColor = LightGray,
                modifier = Modifier
                    .height(64.dp),
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = LightGray,
                        modifier = Modifier
                            .size(48.dp)
                            .clickable {
                                navHostController.popBackStack()
                            }
                    )
                }
            )
        },
        content = {
            it.calculateTopPadding()
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete",
                        tint = LightGray,
                        modifier = Modifier
                            .size(48.dp)
                            .clickable {
                                viewModel.deleteNote(note!!)
                                navHostController.navigate(Screens.MainScreen.route)
                            }
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Edit",
                        tint = LightGray,
                        modifier = Modifier
                            .size(48.dp)
                            .clickable {
                                navHostController.navigate(Screens.EditScreen.route + "?name=$id")
                            }
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = note?.title.toString(),
                    fontSize = 24.sp,
                    color = LightGray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = note?.content.toString(),
                    fontSize = 16.sp,
                    color = LightGray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                        .verticalScroll(rememberScrollState())
                )
            }
        }
    )
}