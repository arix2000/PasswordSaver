package com.password.saver

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.password.saver.features.loginscreen.ui.LoginFragment
import com.password.saver.features.loginscreen.ui.LoginScreen
import com.password.saver.features.passwordlist.ui.screens.PasswordDetails
import com.password.saver.features.passwordlist.ui.PasswordsFragment
import com.password.saver.features.passwordlist.ui.screens.PasswordAddScreen
import com.password.saver.features.passwordlist.ui.screens.PasswordEditScreen
import com.password.saver.models.Password
import com.password.saver.models.Password.Companion.PASSWORD_ARGUMENT_KEY
import com.password.saver.ui.appcomposables.AppSnackBarHost
import com.password.saver.ui.appcomposables.TopBar


class MyPasswordsActivity : ComponentActivity() {

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSoftInputMode(true)
        setContent {
            AppContent()
        }
    }

    @ExperimentalComposeUiApi
    @Composable
    private fun AppContent() {
        var shouldShowBackButton by remember { mutableStateOf(false) }
        val navController = rememberNavController()
        val scaffoldState = rememberScaffoldState()
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { TopBar(navController, shouldShowBackButton) },
            snackbarHost = { AppSnackBarHost(hostState = it) }
        ) {
            NavHost(navController = navController, startDestination = ROUTE_LOGIN_FRAGMENT) {
                composable(ROUTE_LOGIN_FRAGMENT) {
                    LoginFragment(navController)
                    shouldShowBackButton = false
                }
                composable(ROUTE_PASSWORD_LIST) {
                    PasswordsFragment(navController)
                    shouldShowBackButton = true
                }
                composable(ROUTE_LOGIN_SCREEN) {
                    LoginScreen(navController)
                    shouldShowBackButton = false
                }
                composable(ROUTE_PASSWORD_ADD) {
                    PasswordAddScreen(scaffoldState, navController)
                    shouldShowBackButton = true
                }
                composable(
                    "$ROUTE_PASSWORD_DETAILS/{password}",
                    listOf(navArgument(PASSWORD_ARGUMENT_KEY) { type = NavType.StringType })
                ) { backStackEntry ->
                    backStackEntry.arguments?.getString(PASSWORD_ARGUMENT_KEY)?.let {
                        PasswordDetails(Password.fromJson(it), navController, scaffoldState)
                    }
                    shouldShowBackButton = true
                }
                composable(
                    "$ROUTE_PASSWORD_EDIT/{password}",
                    listOf(navArgument(PASSWORD_ARGUMENT_KEY) { type = NavType.StringType })
                ) { backStackEntry ->
                    backStackEntry.arguments?.getString(PASSWORD_ARGUMENT_KEY)?.let {
                        PasswordEditScreen(Password.fromJson(it), scaffoldState, navController)
                    }
                    shouldShowBackButton = true
                }
            }
        }
    }

    private fun setSoftInputMode(boolean: Boolean) {
        if (boolean)
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        else
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED)
    }

    @ExperimentalComposeUiApi
    @Preview
    @Composable
    private fun DefaultPreview() {
        AppContent()
    }

    companion object {
        const val ROUTE_LOGIN_FRAGMENT = "loginFragment"
        const val ROUTE_PASSWORD_LIST = "passwordList"
        const val ROUTE_LOGIN_SCREEN = "loginScreen"
        const val ROUTE_PASSWORD_DETAILS = "passwordDetails"
        const val ROUTE_PASSWORD_ADD = "passwordAdd"
        const val ROUTE_PASSWORD_EDIT = "passwordEdit"
    }
}