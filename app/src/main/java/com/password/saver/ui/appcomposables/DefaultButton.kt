package com.password.saver.ui.appcomposables

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        shape = CircleShape,
        onClick = onClick,
        modifier = modifier
    ) {
        Text(text = text.uppercase(), Modifier.padding(3.dp))
    }
}