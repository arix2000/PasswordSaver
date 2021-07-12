package com.password.saver.features.passwordlist.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.password.saver.features.passwordlist.PasswordsViewModel
import com.password.saver.features.passwordlist.ui.screens.PasswordList
import com.password.saver.ui.theme.PasswordSaverTheme
import org.koin.androidx.compose.getViewModel


@Composable
fun PasswordsFragment(navController: NavController) {
    val passwordsViewModel = getViewModel<PasswordsViewModel>()
    val passwords by passwordsViewModel.getPasswords().collectAsState(initial = listOf())

    PasswordSaverTheme {
        PasswordList(passwords, navController)
    }
}