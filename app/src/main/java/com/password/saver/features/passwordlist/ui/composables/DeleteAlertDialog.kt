package com.password.saver.features.passwordlist.ui.composables

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.password.saver.R
import com.password.saver.features.passwordlist.PasswordsViewModel
import com.password.saver.models.Password
import com.password.saver.ui.appcomposables.DefaultButton
import com.password.saver.ui.theme.PasswordSaverTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun DeleteAlertDialog(
    showDialog: Boolean,
    onDismiss: (Boolean) -> Unit,
    password: Password,
    navController: NavController
) {
    if (showDialog) {
        val viewModel = getViewModel<PasswordsViewModel>()
        AlertDialog(
            onDismissRequest = { onDismiss(false) },
            title = {
                Box(modifier = Modifier.fillMaxWidth(0.9f)) {
                    Text(
                        stringResource(R.string.sure_to_delete),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            },
            buttons = {
                val context = LocalContext.current
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(20.dp)
                ) {
                    DeleteButton(modifier = Modifier.align(Alignment.CenterEnd)) {
                        viewModel.delete(password)
                        Toast.makeText(context, R.string.deleted, Toast.LENGTH_SHORT).show()
                        navController.navigateUp()
                        onDismiss(false)
                    }
                    DefaultButton(
                        text = stringResource(R.string.dismiss),
                        modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        onDismiss(false)
                    }
                }
            },
            shape = RoundedCornerShape(35.dp),
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview
@Composable
fun DialogDemoPreview() {
    PasswordSaverTheme {
        DeleteAlertDialog(
            showDialog = true,
            onDismiss = {},
            password = Password("ASDASD","ASDASD","ASDSAD"),
            navController = rememberNavController()
        )
    }
}