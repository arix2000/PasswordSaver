package com.password.saver.ui.appcomposables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.password.saver.MyPasswordsActivity
import com.password.saver.MyPasswordsActivity.Companion.ROUTE_CHANGE_PASS_SCREEN
import com.password.saver.R

@Composable
fun TopBar(
    navController: NavController,
    shouldShowBackButton: Boolean,
    shouldShowDropDownMenu: Boolean
) {
    TopAppBar {
        Box {
            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                if (shouldShowBackButton)
                    IconButton(
                        onClick = { navController.popBackStack() },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(R.string.app_name),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            if (shouldShowDropDownMenu)
                IconButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = { navController.navigate(ROUTE_CHANGE_PASS_SCREEN) },
                ) {
                    Icon(
                        imageVector = Icons.Filled.VpnKey,
                        contentDescription = "Back",
                    )
                }
        }
    }
}

@Preview
@Composable
private fun DefaultPreview() {
    TopBar(
        navController = rememberNavController(),
        shouldShowBackButton = true,
        shouldShowDropDownMenu = true
    )
}