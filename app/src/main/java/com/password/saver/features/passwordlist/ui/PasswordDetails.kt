package com.password.saver.features.passwordlist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.password.saver.models.Password

@Preview
@Composable
fun PasswordDetails(password: Password = Password("Poczta Gmail", "arix20003@gmail.com", "Painkiler1")) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(text = "Tytuł")
        Text(text = password.title)
    }
}