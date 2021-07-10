package com.password.saver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.password.saver.features.loginscreen.ui.LoginFragment
import com.password.saver.features.loginscreen.ui.LoginScreen
import com.password.saver.features.passwordlist.PasswordListFragment


class MyPasswordsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppContent()
        }
    }

    @Composable
    private fun AppContent() {
        var shouldShowBackButton by remember { mutableStateOf(false) }
        val navController = rememberNavController()
        Scaffold(
            topBar = {
                TopBar(navController, shouldShowBackButton)
            }
        ) {
            NavHost(navController = navController, startDestination = ROUTE_LOGIN_FRAGMENT) {
                composable(ROUTE_LOGIN_FRAGMENT) {
                    LoginFragment(navController); shouldShowBackButton = false
                }
                composable(ROUTE_PASSWORD_LIST) {
                    PasswordListFragment(); shouldShowBackButton = true
                }
                composable(ROUTE_LOGIN_SCREEN) {
                    LoginScreen(navController); shouldShowBackButton = false
                }
            }
        }
    }

    @Preview
    @Composable
    private fun DefaultPreview() {
        AppContent()
    }

    @Composable
    private fun TopBar(navController: NavController, shouldShowBackButton: Boolean) {
        TopAppBar {
            if (shouldShowBackButton)
                IconButton(
                    onClick = { navController.popBackStack() },
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                    )
                }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = stringResource(R.string.app_name),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

        }
    }

    companion object {
        const val ROUTE_LOGIN_FRAGMENT = "loginFragment"
        const val ROUTE_PASSWORD_LIST = "passwordList"
        const val ROUTE_LOGIN_SCREEN = "loginScreen"
    }
}