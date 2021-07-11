package com.password.saver.features.passwordlist.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.password.saver.R
import com.password.saver.ui.theme.RedDark

@Composable
fun DeleteButton(
    text: String = stringResource(id = R.string.delete),
    onClick: () -> Unit
) {
    Button(
        shape = CircleShape,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = RedDark,
            contentColor = Color.Black
        )
    ) {
        Text(text = text.uppercase(), Modifier.padding(3.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Image(
            imageVector = Icons.Rounded.Delete,
            contentDescription = null,
            modifier = Modifier
                .padding(3.dp)
                .height(20.dp)
            )
    }
}