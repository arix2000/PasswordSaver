package com.password.saver.ui.appcomposables

import android.content.ClipData
import android.content.Context
import android.os.Build
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.core.content.ContextCompat
import com.password.saver.R
import com.password.saver.models.Password
import kotlinx.coroutines.launch

@Composable
fun CopyButton(textToCopy: String, scaffoldState: ScaffoldState) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val copiedStr = stringResource(R.string.copied)
    IconButton(
        onClick = {
            copyToClipboard(context, textToCopy)
            scope.launch {
                scaffoldState.snackbarHostState.showSnackbar(copiedStr)
            }
        }
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_content_copy),
            contentDescription = "copyContent"
        )
    }
}

private fun copyToClipboard(context: Context, text: String) {
    val clipboard =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
    val clip = ClipData.newPlainText("Copied Text", text)
    clipboard.setPrimaryClip(clip)
}