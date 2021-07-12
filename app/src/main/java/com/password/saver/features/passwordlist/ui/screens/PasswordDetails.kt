package com.password.saver.features.passwordlist.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.password.saver.MyPasswordsActivity.Companion.ROUTE_PASSWORD_EDIT
import com.password.saver.R
import com.password.saver.features.passwordlist.ui.composables.DeleteButton
import com.password.saver.features.passwordlist.ui.composables.IconRightButton
import com.password.saver.models.Password
import com.password.saver.ui.appcomposables.CopyButton
import com.password.saver.ui.theme.ColorPrimary
import com.password.saver.ui.theme.PasswordSaverTheme


@Composable
fun PasswordDetails(
    password2: Password,
    navController: NavController,
    scaffoldState: ScaffoldState
) {
    val password = navController.currentBackStackEntry?.savedStateHandle
        ?.get<String>(Password.PASSWORD_ARGUMENT_KEY)
        ?.let { Password.fromJson(it) } ?: password2

    PasswordSaverTheme {
        Box {
            Surface(modifier = Modifier.fillMaxSize()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(10.dp)
                        .verticalScroll(rememberScrollState())

                ) {
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(text = stringResource(R.string.title_with_colon), fontSize = 18.sp)
                    Text(
                        text = password.title,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorPrimary,
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    Text(text = stringResource(R.string.login_with_colon), fontSize = 18.sp)
                    Text(
                        text = password.login,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                    CopyButton(
                        password = password,
                        scaffoldState = scaffoldState
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    Text(text = stringResource(R.string.password_with_colon), fontSize = 18.sp)
                    Text(
                        text = password.password,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                    CopyButton(
                        password = password,
                        scaffoldState = scaffoldState
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    Row {
                        DeleteButton(onClick = {
                            //TODO
                        })
                        Spacer(modifier = Modifier.width(25.dp))
                        IconRightButton(
                            text = stringResource(R.string.edit),
                            imageVector = Icons.Rounded.Edit
                        ) {
                            navController.navigate("$ROUTE_PASSWORD_EDIT/${password.toJson()}")
                        }
                    }
                }
            }
        }
    }
}