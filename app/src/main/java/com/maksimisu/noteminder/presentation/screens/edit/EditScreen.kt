package com.maksimisu.noteminder.presentation.screens.edit

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColor
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.maksimisu.noteminder.domain.model.Note
import com.maksimisu.noteminder.presentation.navigation.Screens
import com.maksimisu.noteminder.presentation.ui.theme.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditScreen(
    navHostController: NavHostController,
    id: Long
) {

    val viewModel = hiltViewModel<EditViewModel>()

    var title by rememberSaveable { mutableStateOf("") }
    var content by rememberSaveable { mutableStateOf("") }
    val titleLabel by rememberSaveable { mutableStateOf("Title") }
    val contentLabel by rememberSaveable { mutableStateOf("Content") }

    var currentColor by rememberSaveable { mutableStateOf(DarkRed.toArgb()) }

    val note = viewModel.note.observeAsState().value

    if (id >= 0) {
        viewModel.getNoteById(id)
        if (note != null) {
            title = note.title
            content = note.content
            currentColor = note.cardColor
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "EDIT NOTE",
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
                },
                actions = {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Save",
                        tint = LightGray,
                        modifier = Modifier
                            .size(48.dp)
                            .clickable {
                                viewModel.addNote(
                                    if (id >= 0) {
                                        Note(
                                            id,
                                            title = title,
                                            content = content,
                                            cardColor = currentColor
                                        )
                                    } else {
                                        Note(
                                            title = title,
                                            content = content,
                                            cardColor = currentColor
                                        )
                                    }
                                ) {
                                    navHostController.navigate(Screens.MainScreen.route)
                                }
                            }
                    )
                }
            )
        },
        content = {
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
                    Box(
                        modifier = Modifier
                            .width(180.dp)
                            .height(48.dp)
                            .clip(shape = RoundedCornerShape(5.dp))
                            .background(Color(currentColor)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "CARD COLOR",
                            fontSize = 24.sp,
                            color = LightGray
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val selectorColors = listOf(
                            DarkRed,
                            DarkPink,
                            DarkPurple,
                            DarkBlue,
                            DarkBreeze,
                            DarkGreen,
                            DarkYellow,
                            DarkOrange
                        )
                        selectorColors.forEach {
                            item {
                                if (Color(currentColor) == it) {
                                    Box(
                                        modifier = Modifier
                                            .padding(horizontal = 5.dp)
                                            .size(36.dp)
                                            .background(DarkViolet)
                                            .padding(2.dp)
                                            .background(it)
                                    )
                                } else {
                                    Box(
                                        modifier = Modifier
                                            .padding(horizontal = 5.dp)
                                            .size(36.dp)
                                            .background(LightGray)
                                            .padding(2.dp)
                                            .background(it)
                                            .clickable {
                                                currentColor = it.toArgb()
                                            }
                                    )
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text(titleLabel) },
                    textStyle = TextStyle(color = LightGray, fontSize = 24.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    value = content,
                    onValueChange = { content = it },
                    label = { Text(contentLabel) },
                    textStyle = TextStyle(color = LightGray, fontSize = 14.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                        .padding(bottom = 10.dp)
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun EditPreview() {
    NOTEminderTheme {
        EditScreen(navHostController = rememberNavController(), -1)
    }
}