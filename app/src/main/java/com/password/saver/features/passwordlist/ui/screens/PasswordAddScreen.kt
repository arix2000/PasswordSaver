package com.password.saver.features.passwordlist.ui.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.password.saver.R
import com.password.saver.extensions.showSnackBar
import com.password.saver.features.passwordlist.PasswordsViewModel
import com.password.saver.models.Password
import com.password.saver.ui.appcomposables.IconRightButton
import com.password.saver.ui.theme.ColorPrimary
import com.password.saver.ui.theme.PasswordSaverTheme
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@ExperimentalComposeUiApi
@Composable
fun PasswordAddScreen(
    scaffoldState: ScaffoldState,
    navController: NavController
) {
    var title by remember { mutableStateOf("") }
    var login by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }
    val viewModel = getViewModel<PasswordsViewModel>()
    var passwordVisibility by remember { mutableStateOf(false) }
    val showedIcon =
        if (!passwordVisibility) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff

    val focusRequester = remember { FocusRequester() }
    val focusRequester2 = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

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
                        text = stringResource(R.string.add_new_password),
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
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions {
                            focusRequester.requestFocus()
                        }
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    Text(text = stringResource(R.string.login), fontSize = 18.sp)
                    OutlinedTextField(
                        value = login,
                        onValueChange = { login = it },
                        shape = CircleShape,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions {
                            focusRequester2.requestFocus()
                        },
                        modifier = Modifier.focusRequester(focusRequester)
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    Text(text = stringResource(R.string.password), fontSize = 18.sp)
                    OutlinedTextField(
                        value = passwordValue,
                        onValueChange = { passwordValue = it },
                        shape = CircleShape,
                        trailingIcon = {
                            Icon(
                                modifier = Modifier.clickable {
                                    passwordVisibility = !passwordVisibility
                                },
                                imageVector = showedIcon,
                                contentDescription = null
                            )
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions {
                            keyboardController?.hide()
                        },
                        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                        modifier = Modifier.focusRequester(focusRequester2)
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    val snackBarText = stringResource(R.string.all_pools_required)
                    val scope = rememberCoroutineScope()
                    val context = LocalContext.current
                    IconRightButton(
                        text = stringResource(R.string.add),
                        imageVector = Icons.Rounded.Add
                    ) {
                        if (title.isNotBlank() && login.isNotBlank() && passwordValue.isNotBlank()) {
                            viewModel.add(Password(title, login, passwordValue))
                            Toast.makeText(context, R.string.added, Toast.LENGTH_SHORT).show()
                            navController.navigateUp()
                        } else {
                            scope.showSnackBar(scaffoldState.snackbarHostState, snackBarText)
                        }
                    }
                }
            }
        }
    }
}