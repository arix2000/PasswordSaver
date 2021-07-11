package com.password.saver.features.passwordlist.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.password.saver.features.passwordlist.PasswordsViewModel
import com.password.saver.features.passwordlist.ui.screens.PasswordList
import com.password.saver.models.Password
import com.password.saver.ui.theme.PasswordSaverTheme
import org.koin.androidx.compose.getViewModel


@Composable
fun PasswordsFragment(navController: NavController) {
    val passwordsViewModel = getViewModel<PasswordsViewModel>()
    val passwords = listOf(Password("Poczta Gmail", "arkadiusz.madry.2000@gmail.com", "Painkiler1"))
    //by passwordsViewModel.getPasswords().collectAsState(initial = listOf())

    PasswordSaverTheme {
        PasswordList(passwords, navController)
    }
}