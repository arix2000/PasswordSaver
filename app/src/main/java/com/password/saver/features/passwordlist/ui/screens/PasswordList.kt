package com.password.saver.features.passwordlist.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.password.saver.MyPasswordsActivity.Companion.ROUTE_PASSWORD_DETAILS
import com.password.saver.extensions.toSafeNavString
import com.password.saver.features.passwordlist.ui.ListItemPassword
import com.password.saver.models.Password

@Composable
fun PasswordList(passwords: List<Password>, navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            items(passwords) { password ->
                ListItemPassword(password = password) {
                    openDetails(navController, password)
                }
            }
            item {
                Spacer(modifier = Modifier.height(60.dp))
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
fun openDetails(navController: NavController, password: Password) {
    navController.navigate("$ROUTE_PASSWORD_DETAILS/${password.toJson().toSafeNavString()}")
}
