package com.password.saver.ui.appcomposables

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable

@Composable
fun AppSnackBarHost(hostState: SnackbarHostState) {
    SnackbarHost(hostState) {
        Snackbar(
            snackbarData = it,
            shape = CircleShape
        )
    }
}