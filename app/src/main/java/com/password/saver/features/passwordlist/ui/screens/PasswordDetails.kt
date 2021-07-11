package com.password.saver.features.passwordlist.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getSystemService
import com.password.saver.R
import com.password.saver.features.passwordlist.ui.composables.DeleteButton
import com.password.saver.features.passwordlist.ui.composables.EditButton
import com.password.saver.models.Password
import com.password.saver.ui.appcomposables.CopyButton
import com.password.saver.ui.theme.ColorPrimary
import com.password.saver.ui.theme.PasswordSaverTheme
import kotlinx.coroutines.launch


@Composable
fun PasswordDetails(
    password: Password,
    scaffoldState: ScaffoldState
) {
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
                    Text(text = "Tytuł:", fontSize = 18.sp)
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
                        EditButton(onClick = {
                            //TODO
                        })
                    }
                }
            }
        }
    }
}