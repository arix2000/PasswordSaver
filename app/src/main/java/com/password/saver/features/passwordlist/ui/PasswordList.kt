package com.password.saver.features.passwordlist.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.password.saver.MyPasswordsActivity.Companion.ROUTE_PASSWORD_DETAILS
import com.password.saver.models.Password

@Composable
fun PasswordList(passwords: List<Password>, navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            items(10) {
                ListItemPassword(
                    password = Password(
                        "Poczta Gmail",
                        "arix20003@gmail.com",
                        "Painkiler1"
                    )
                ) {
                    openDetails(navController)
                }
            }
            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

fun openDetails(navController: NavController) {
    navController.navigate(ROUTE_PASSWORD_DETAILS)
}
