package com.password.saver.ui.appcomposables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun IconRightButton(
    text: String,
    imageVector: ImageVector,
    onClick: () -> Unit
) {
    Button(
        shape = CircleShape,
        onClick = onClick,
    ) {
        Text(text = text.uppercase(), Modifier.padding(3.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Image(
            imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier
                .padding(3.dp)
                .height(20.dp)
        )
    }
}