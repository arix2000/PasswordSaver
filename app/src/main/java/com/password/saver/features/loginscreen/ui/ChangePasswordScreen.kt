package com.password.saver.features.loginscreen.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.password.saver.MyPasswordsActivity.Companion.ROUTE_PASSWORD_LIST
import com.password.saver.R
import com.password.saver.common.views.EyeIconButton
import com.password.saver.features.loginscreen.LoginViewModel
import com.password.saver.ui.theme.PasswordSaverTheme
import org.koin.androidx.compose.getViewModel

@OptIn(
    ExperimentalComposeUiApi::class,
    androidx.compose.foundation.ExperimentalFoundationApi::class
)
@Composable
fun ChangePasswordScreen(navController: NavController) {
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var isNewPasswordVisible by remember { mutableStateOf(false) }
    var isOldPasswordVisible by remember { mutableStateOf(false) }
    var secondNewPassword by remember { mutableStateOf("") }
    val context = LocalContext.current
    val viewModel = getViewModel<LoginViewModel>()
    val actualPassword by viewModel.getMainPassword().observeAsState()

    val focusRequester = remember { FocusRequester() }
    val focusRequester2 = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    PasswordSaverTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = oldPassword,
                    onValueChange = { oldPassword = it },
                    label = { Text("Stare hasło") },
                    shape = CircleShape,
                    visualTransformation = getPasswordVisualTransformation(isOldPasswordVisible),
                    trailingIcon = {
                        EyeIconButton(isPasswordVisible = isOldPasswordVisible) {
                            isOldPasswordVisible = !isOldPasswordVisible
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions {
                        focusRequester.requestFocus()
                    }
                )
                Box(modifier = Modifier.height(25.dp))
                OutlinedTextField(
                    value = newPassword,
                    onValueChange = { newPassword = it },
                    label = { Text("Hasło") },
                    shape = CircleShape,
                    visualTransformation = getPasswordVisualTransformation(isNewPasswordVisible),
                    trailingIcon = {
                        EyeIconButton(isPasswordVisible = isNewPasswordVisible) {
                            isNewPasswordVisible = !isNewPasswordVisible
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions {
                        focusRequester2.requestFocus()
                    },
                    modifier = Modifier.focusRequester(focusRequester)
                )
                Box(modifier = Modifier.height(25.dp))
                OutlinedTextField(
                    value = secondNewPassword,
                    onValueChange = { secondNewPassword = it },
                    label = { Text("Powtórz hasło") },
                    shape = CircleShape,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions {
                        keyboardController?.hide()
                    },
                    modifier = Modifier.focusRequester(focusRequester2)
                )
                Box(modifier = Modifier.height(25.dp))
                Button(
                    onClick = {
                        validatePasswords(
                            oldPassword,
                            newPassword,
                            secondNewPassword,
                            navController,
                            context,
                            viewModel,
                            actualPassword
                        )
                    },
                    shape = CircleShape
                ) {
                    Text(text = "Zmień hasło".uppercase(), Modifier.padding(3.dp))
                }
            }
        }
    }
}

private fun getPasswordVisualTransformation(isNewPasswordVisible: Boolean) =
    if (isNewPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()

@OptIn(ExperimentalComposeUiApi::class)
private fun validatePasswords(
    oldPassword: String,
    newPassword: String,
    secondNewPassword: String,
    navController: NavController,
    context: Context,
    viewModel: LoginViewModel,
    actualPassword: String?
) {
    if (oldPassword != actualPassword) {
        Toast.makeText(context, R.string.wrong_old_password, Toast.LENGTH_SHORT).show()
    } else if (newPassword.isEmpty() || secondNewPassword.isEmpty()) {
        Toast.makeText(context, R.string.empty_pools, Toast.LENGTH_SHORT).show()
    } else if (newPassword != secondNewPassword) {
        Toast.makeText(context, R.string.different_passwords, Toast.LENGTH_SHORT).show()
    } else {
        viewModel.saveMainPassword(newPassword)
        Toast.makeText(context, R.string.password_has_been_changed, Toast.LENGTH_SHORT).show()
        navController.navigateUp()
    }
}

@Preview
@Composable
fun ChangePasswordScreenPreview() {
    PasswordSaverTheme {
        ChangePasswordScreen(rememberNavController())
    }
}