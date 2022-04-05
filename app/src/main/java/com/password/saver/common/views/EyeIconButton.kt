package com.password.saver.common.views

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.password.saver.ui.theme.PasswordSaverTheme

@Composable
fun EyeIconButton(isPasswordVisible: Boolean, onClick: () -> Unit) {
    val showedIcon =
        if (!isPasswordVisible) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff
    Icon(
        modifier = Modifier.clickable(onClick = onClick),
        imageVector = showedIcon,
        contentDescription = null
    )
}

@Preview
@Composable
fun EyeIconPreview() {
    PasswordSaverTheme {
        EyeIconButton(false) {}
    }
}