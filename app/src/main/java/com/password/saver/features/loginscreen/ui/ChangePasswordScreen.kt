package com.password.saver.features.loginscreen.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.password.saver.R
import com.password.saver.ui.theme.PasswordSaverTheme

@Composable
fun ChangePasswordScreen(navController: NavController) {
    PasswordSaverTheme {
        Surface {
            Column {
                SetupPasswordScreen(navController = navController, showTitle = false)
            }
        }
    }
}

@Preview
@Composable
fun ChangePasswordScreenPreview() {
    PasswordSaverTheme {
        ChangePasswordScreen(rememberNavController())
    }
}