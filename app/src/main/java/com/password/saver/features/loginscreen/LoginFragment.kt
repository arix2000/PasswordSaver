package com.password.saver.features.loginscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.password.saver.R
import com.password.saver.ui.theme.PasswordSaverTheme

@Composable
fun LoginFragment(navController: NavController) {
    var text by remember { mutableStateOf("") }
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
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Hasło") }
                )
                Box(modifier = Modifier.height(25.dp))
                Button(onClick = { checkPassword(navController) }, shape = CircleShape) {
                    Text(text = "Dalej".uppercase(), Modifier.padding(3.dp))
                }
            }
        }
    }
}

private fun checkPassword(navController: NavController) {
    navController.navigate("PasswordList")
}