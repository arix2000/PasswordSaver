package com.password.saver.features.loginscreen.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.password.saver.MyPasswordsActivity.Companion.ROUTE_PASSWORD_LIST
import com.password.saver.R
import com.password.saver.features.loginscreen.LoginViewModel
import com.password.saver.ui.theme.PasswordSaverTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun LoginScreen(navController: NavController) {
    var password by remember { mutableStateOf("") }
    val viewModel = getViewModel<LoginViewModel>()
    val correctPassword by viewModel.getMainPassword().observeAsState()
    val context = LocalContext.current
    PasswordSaverTheme {
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.enter_password),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 20.sp
                )
                Box(modifier = Modifier.height(25.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(stringResource(R.string.password)) },
                    shape = CircleShape,
                    visualTransformation = PasswordVisualTransformation()
                )
                Box(modifier = Modifier.height(25.dp))
                Button(onClick = { checkPassword(context ,navController, password, correctPassword) }, shape = CircleShape) {
                    Text(text = stringResource(R.string.next).uppercase(), Modifier.padding(3.dp))
                }
            }
        }
    }
}

private fun checkPassword(
    context: Context,
    navController: NavController,
    password: String,
    correctPassword: String?
) {
    if (password == correctPassword) {
        navController.navigate(ROUTE_PASSWORD_LIST)
    } else {
        Toast.makeText(context, R.string.wrong_password, Toast.LENGTH_SHORT).show()
    }
}
