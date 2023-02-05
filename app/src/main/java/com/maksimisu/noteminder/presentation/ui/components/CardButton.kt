package com.maksimisu.noteminder.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maksimisu.noteminder.presentation.ui.theme.DarkRed
import com.maksimisu.noteminder.presentation.ui.theme.LightGray

@Composable
fun CardButton(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth()
            .height(54.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(DarkRed)
            .padding(start = 15.dp, end = 10.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                fontSize = 20.sp,
                color = LightGray
            )
            Icon(
                imageVector = icon,
                contentDescription = "New note",
                tint = LightGray,
                modifier = Modifier
                    .size(36.dp)
            )
        }
    }
}