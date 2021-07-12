package com.password.saver.features.passwordlist.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.password.saver.MyPasswordsActivity
import com.password.saver.features.passwordlist.PasswordsViewModel
import com.password.saver.features.passwordlist.ui.composables.AddFloatingButton
import com.password.saver.features.passwordlist.ui.screens.PasswordEmptyScreen
import com.password.saver.features.passwordlist.ui.screens.PasswordList
import com.password.saver.models.Password
import com.password.saver.ui.theme.PasswordSaverTheme
import org.koin.androidx.compose.getViewModel


@Composable
fun PasswordsFragment(navController: NavController) {
    val passwordsViewModel = getViewModel<PasswordsViewModel>()
    val passwords by passwordsViewModel.getPasswords().observeAsState()

    PasswordSaverTheme {
        Surface {
            Box(modifier = Modifier.fillMaxSize()) {
                if (passwords == null) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                } else if (passwords?.isNotEmpty() == true)
                    PasswordList(passwords!!, navController)
                else
                    PasswordEmptyScreen()

                AddFloatingButton(modifier = Modifier.align(Alignment.BottomEnd)) {
                    navController.navigate(MyPasswordsActivity.ROUTE_PASSWORD_ADD)
                }
            }
        }
    }
}