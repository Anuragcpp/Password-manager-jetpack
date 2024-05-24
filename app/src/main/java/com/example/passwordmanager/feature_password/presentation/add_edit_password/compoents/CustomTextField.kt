package com.example.passwordmanager.feature_password.presentation.add_edit_password.compoents

import android.hardware.camera2.CameraExtensionSession.StillCaptureLatency
import android.telecom.StatusHints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    text : String,
    hint : String,
    isNote : Boolean = false,
    textStyle: TextStyle = TextStyle(),
    onChangeText : (String) -> Unit,
    leadingIcon : @Composable () -> Unit
    ) {

    TextField(
        value = text,
        onValueChange = onChangeText,
        modifier = modifier.fillMaxWidth(0.8f),
        textStyle = textStyle,
        leadingIcon = leadingIcon,
        singleLine = !isNote,
        label = { Text(text = hint)}
    )

}