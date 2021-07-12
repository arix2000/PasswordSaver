package com.password.saver.features.passwordlist.ui.composables

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.password.saver.R
import com.password.saver.features.passwordlist.PasswordsViewModel
import com.password.saver.models.Password
import com.password.saver.ui.appcomposables.DefaultButton
import org.koin.androidx.compose.getViewModel

@Composable
fun DialogDemo(
    showDialog: Boolean,
    setShowDialog: (Boolean) -> Unit,
    password: Password,
    navController: NavController
) {
    if (showDialog) {
        val viewModel = getViewModel<PasswordsViewModel>()
        AlertDialog(
            onDismissRequest = {},
            title = {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        stringResource(R.string.sure_to_delete),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            },
            buttons = {
                val context = LocalContext.current
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)) {
                    DeleteButton(modifier = Modifier.align(Alignment.CenterEnd)) {
                        viewModel.delete(password)
                        Toast.makeText(context, R.string.deleted, Toast.LENGTH_SHORT).show()
                        navController.navigateUp()
                        setShowDialog(false)
                    }
                    DefaultButton(
                        text = stringResource(R.string.dismiss),
                        modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        setShowDialog(false)
                    }
                }
            },
            shape = RoundedCornerShape(35.dp),
            modifier = Modifier.padding(8.dp)
        )
    }
}