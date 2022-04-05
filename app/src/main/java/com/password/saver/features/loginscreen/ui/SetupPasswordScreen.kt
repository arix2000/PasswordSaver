package com.password.saver.features.loginscreen.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.password.saver.MyPasswordsActivity.Companion.ROUTE_LOGIN_SCREEN
import com.password.saver.R
import com.password.saver.features.loginscreen.LoginViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SetupPasswordScreen(navController: NavController) {
    var password by remember { mutableStateOf("") }
    var secondPassword by remember { mutableStateOf("") }
    val context = LocalContext.current
    val viewModel = getViewModel<LoginViewModel>()

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(R.string.setup_password),
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 20.sp
            )
            Box(modifier = Modifier.height(25.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Hasło") },
                shape = CircleShape,
                visualTransformation = PasswordVisualTransformation(),
                keyboardActions = KeyboardActions {
                    focusRequester.requestFocus()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
            )
            Box(modifier = Modifier.height(25.dp))
            OutlinedTextField(
                value = secondPassword,
                onValueChange = { secondPassword = it },
                label = { Text("Powtórz hasło") },
                shape = CircleShape,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.focusRequester(focusRequester),
                keyboardActions = KeyboardActions {
                    keyboardController?.hide()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
            )
            Box(modifier = Modifier.height(25.dp))
            Button(
                onClick = {
                    validatePasswords(password, secondPassword, navController, context, viewModel)
                },
                shape = CircleShape
            ) {
                Text(text = "Ustaw".uppercase(), Modifier.padding(3.dp))
            }
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
private fun validatePasswords(
    password: String,
    secondPassword: String,
    navController: NavController,
    context: Context,
    viewModel: LoginViewModel
) {
    if (password != secondPassword) {
        Toast.makeText(context, R.string.different_passwords, Toast.LENGTH_SHORT).show()
    } else if (password.isEmpty() || secondPassword.isEmpty()) {
        Toast.makeText(context, R.string.empty_pools, Toast.LENGTH_SHORT).show()
    } else {
        viewModel.saveMainPassword(password)
        navController.navigate(ROUTE_LOGIN_SCREEN)
    }
}