package com.password.saver.features.passwordlist.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.password.saver.ui.theme.ColorPrimary

@Composable
fun AddFloatingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        backgroundColor = ColorPrimary,
        modifier = modifier.padding(14.dp)
    ) {
        Image(imageVector = Icons.Rounded.Add, contentDescription = "addButton")
    }
}