package com.password.saver.features.passwordlist.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.password.saver.R
import com.password.saver.features.passwordlist.PasswordsViewModel
import com.password.saver.ui.appcomposables.IconRightButton
import com.password.saver.models.Password
import com.password.saver.models.Password.Companion.PASSWORD_ARGUMENT_KEY
import com.password.saver.ui.theme.ColorPrimary
import com.password.saver.ui.theme.PasswordSaverTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun PasswordEditScreen(
    password: Password,
    scaffoldState: ScaffoldState,
    navController: NavController
) {

    var title by remember { mutableStateOf(password.title) }
    var login by remember { mutableStateOf(password.login) }
    var passwordValue by remember { mutableStateOf(password.password) }
    val context = LocalContext.current
    val viewModel = getViewModel<PasswordsViewModel>()
    fun updateAndBackWithData() {
        val newPassword = Password(title, login, passwordValue)

        viewModel.update(newPassword.apply { id = password.id })

        Toast.makeText(context, R.string.updated, Toast.LENGTH_SHORT).show()

        navController.previousBackStackEntry
            ?.savedStateHandle?.set(PASSWORD_ARGUMENT_KEY, newPassword.toJson())
        navController.popBackStack()
    }

    PasswordSaverTheme {
        Box {
            Surface(modifier = Modifier.fillMaxSize()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier
                        .padding(10.dp)
                        .verticalScroll(rememberScrollState())
                ) {

                    Spacer(modifier = Modifier.height(30.dp))

                    Text(
                        text = stringResource(R.string.edit_password),
                        fontSize = 22.sp,
                        color = ColorPrimary,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    Text(text = stringResource(R.string.title), fontSize = 18.sp)
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        shape = CircleShape,
                        maxLines = 1
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    Text(text = stringResource(R.string.login), fontSize = 18.sp)
                    OutlinedTextField(
                        value = login,
                        onValueChange = { login = it },
                        shape = CircleShape,
                        maxLines = 1
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    Text(text = stringResource(R.string.password), fontSize = 18.sp)
                    OutlinedTextField(
                        value = passwordValue,
                        onValueChange = { passwordValue = it },
                        shape = CircleShape,
                        maxLines = 1
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    val snackBarText = stringResource(R.string.all_pools_required)
                    val scope = rememberCoroutineScope()
                    IconRightButton(
                        text = stringResource(R.string.approve),
                        imageVector = Icons.Rounded.Done
                    ) {
                        if (title.isNotBlank() && login.isNotBlank() && passwordValue.isNotBlank()) {
                            updateAndBackWithData()
                        } else {
                            scope.launch {
                                scaffoldState.snackbarHostState
                                    .showSnackbar(snackBarText)
                            }
                        }
                    }
                }
            }
        }
    }
}