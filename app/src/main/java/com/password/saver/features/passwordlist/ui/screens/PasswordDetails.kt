package com.password.saver.features.passwordlist.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.password.saver.MyPasswordsActivity.Companion.ROUTE_PASSWORD_EDIT
import com.password.saver.R
import com.password.saver.features.passwordlist.ui.composables.DeleteButton
import com.password.saver.features.passwordlist.ui.composables.DeleteAlertDialog
import com.password.saver.ui.appcomposables.IconRightButton
import com.password.saver.models.Password
import com.password.saver.ui.appcomposables.CopyButton
import com.password.saver.ui.theme.ColorPrimary
import com.password.saver.ui.theme.PasswordSaverTheme


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PasswordDetails(
    passwordFromArgs: Password,
    navController: NavController,
    scaffoldState: ScaffoldState
) {
    val password = navController.currentBackStackEntry?.savedStateHandle
        ?.get<String>(Password.PASSWORD_ARGUMENT_KEY)
        ?.let { Password.fromJson(it) } ?: passwordFromArgs

    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
    var passwordVisibility by remember { mutableStateOf(false) }
    var showedPassword by remember { mutableStateOf("") }
    val showedIcon = if (!passwordVisibility) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff
    showedPassword = if (!passwordVisibility)
        password.password.map { "●" }.joinToString(separator = "")
    else
        password.password

    PasswordSaverTheme {
        Box {
            Surface(modifier = Modifier.fillMaxSize()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(10.dp)
                        .verticalScroll(rememberScrollState())
                        .fillMaxWidth()

                ) {
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(text = stringResource(R.string.title_with_colon), fontSize = 18.sp)
                    Text(
                        text = password.title,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
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
                        textToCopy = password.login,
                        scaffoldState = scaffoldState
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    Text(text = stringResource(R.string.password_with_colon), fontSize = 18.sp)
                    Text(
                        text = showedPassword,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                    )
                    Row {
                        CopyButton(
                            textToCopy = password.password,
                            scaffoldState = scaffoldState
                        )
                        IconButton(
                            onClick = {
                                passwordVisibility = !passwordVisibility
                            }
                        ) {
                            Icon(
                                imageVector = showedIcon,
                                contentDescription = "copyContent"
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(25.dp))
                    Row {
                        DeleteButton(onClick = {
                            setShowDialog(true)
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
                DeleteAlertDialog(showDialog, setShowDialog, password, navController)
            }
        }
    }
}

@Preview
@Composable
fun PasswordDetailsPreview() {
    PasswordSaverTheme {
        PasswordDetails(
            Password("TEST", "ASDASDASD", "ASDASDAS"),
            rememberNavController(),
            rememberScaffoldState()
        )
    }
}