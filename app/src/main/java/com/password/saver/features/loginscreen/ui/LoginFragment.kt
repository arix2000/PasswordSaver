package com.password.saver.features.loginscreen.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.password.saver.features.loginscreen.LoginViewModel
import com.password.saver.ui.theme.PasswordSaverTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun LoginFragment(navController: NavController) {
    val viewModel = getViewModel<LoginViewModel>()
    PasswordSaverTheme {
        val password by viewModel.getMainPassword().observeAsState()
        if (password?.isEmpty() == true)
            SetupPasswordScreen(navController)
        else
            LoginScreen(navController)
    }
}