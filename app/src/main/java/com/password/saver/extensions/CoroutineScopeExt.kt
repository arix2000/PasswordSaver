package com.password.saver.extensions

import androidx.compose.material.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

fun CoroutineScope.showSnackBar(snackBarHostState: SnackbarHostState, text: String) {
    this.launch {
        if (snackBarHostState.currentSnackbarData != null)
            snackBarHostState.currentSnackbarData?.dismiss()

        snackBarHostState.showSnackbar(text)
    }
}