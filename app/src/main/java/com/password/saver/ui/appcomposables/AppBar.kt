package com.password.saver.ui.appcomposables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.password.saver.MyPasswordsActivity.Companion.ROUTE_CHANGE_PASS_SCREEN

@Composable
fun TopBar(
    navController: NavController,
    shouldShowBackButton: Boolean,
    shouldShowDropDownMenu: Boolean,
    title: String
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
                            tint = Color.White
                        )
                    }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = title,
                    fontSize = 20.sp,
                    color = Color.White,
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
                        tint = Color.White
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
        shouldShowDropDownMenu = true,
        "Twoje Hasła"
    )
}