package com.da.chatme.Presentation.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SubmitButton(
    text:String,
    onClick:()->Unit
){
    Button(modifier = Modifier
        .fillMaxWidth()
        .padding(40.dp, 0.dp),
        onClick = {
                  onClick()
        },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFA925))
    ) {
        Text(text = text, color = Color(0xFFFFFFFF))
    }
}