package com.password.saver.features.passwordlist.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.password.saver.R
import com.password.saver.models.Password
import com.password.saver.ui.theme.PasswordSaverTheme
import com.password.saver.ui.theme.VioletDark

@Composable
fun ListItemPassword(password: Password, onClick: () -> Unit) {
    Box {
        Card(
            backgroundColor = VioletDark,
            shape = RoundedCornerShape(35.dp),
            contentColor = Color.White,
            elevation = 0.dp,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
            ) {

            Column(
                Modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .padding(end = 30.dp)) {
                Text(
                    text = password.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row {
                    Text(text = stringResource(R.string.login_with_colon))
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = password.login,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.W500,
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row {
                    Text(text = stringResource(R.string.password_with_colon))
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = password.password.map { "●" }.joinToString(separator = ""),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.W500,
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
        Row(modifier = Modifier.align(Alignment.CenterEnd)) {
            Icon(
                imageVector = Icons.Rounded.KeyboardArrowRight,
                contentDescription = "nextButton",
                modifier = Modifier
                    .size(30.dp)
            )
            Spacer(modifier = Modifier.width(25.dp))
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    PasswordSaverTheme {
        ListItemPassword(password = Password("Title", "Login", "Password")) {

        }
    }
}