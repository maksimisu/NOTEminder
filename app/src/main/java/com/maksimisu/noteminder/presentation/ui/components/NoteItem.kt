package com.maksimisu.noteminder.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maksimisu.noteminder.domain.model.Note
import com.maksimisu.noteminder.presentation.ui.theme.LightGray

@Composable
fun NoteItem(note: Note, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .fillMaxWidth()
            .height(64.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(Color(note.cardColor))
            .padding(vertical = 5.dp, horizontal = 15.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = note.title,
                fontSize = 20.sp,
                color = LightGray,
                maxLines = 1
            )
            Text(
                text = note.content,
                fontSize = 14.sp,
                color = LightGray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}