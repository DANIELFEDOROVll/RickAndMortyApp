package com.example.rickandmorty.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rickandmorty.R


@Composable
fun HeaderRowWithBackBtn(
    title: String = "",
    startPadding: Dp = 32.dp,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(onClick = { onClick.invoke() } )
        {
            Icon(
                painter = painterResource(R.drawable.back_icon),
                contentDescription = "Back button",
                tint = Color.DarkGray,
                modifier = Modifier.size(50.dp)
            )
        }

        Text(
            modifier = Modifier
                .padding(start = startPadding)
                .weight(1f)
                .align(Alignment.CenterVertically),
            text = title,
            textAlign = TextAlign.Left,
            fontSize = 22.sp
        )
    }
}