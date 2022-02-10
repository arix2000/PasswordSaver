package com.password.saver.features.passwordlist.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.password.saver.R
import com.password.saver.ui.theme.ColorPrimary

@Preview
@Composable
fun PasswordEmptyScreen() {
    Surface {
        Box(modifier = Modifier.fillMaxSize())
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(14.dp)
                    .fillMaxHeight()
            ) {
                Text(
                    stringResource(R.string.no_passwords_saved),
                    color = ColorPrimary,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    stringResource(R.string.add_first_password),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 85.dp, bottom = 28.dp)
            ) {
                Text(
                    text = stringResource(R.string.add_password_pointer),
                    color = ColorPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    
                )
                Spacer(modifier = Modifier.width(13.dp))
                Image(
                    imageVector = Icons.Rounded.ArrowForward,
                    contentDescription = "ArrowForward",
                    modifier = Modifier.size(24.dp),
                    colorFilter = ColorFilter.tint(ColorPrimary)
                )
            }
        }
    }
}