package com.da.chatme.Presentation.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmailTextField(
  email:MutableState<String>
){
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        OutlinedTextField(
            modifier = Modifier.size(310.dp,58.dp),
            value = email.value, onValueChange = { text-> email.value = text },
            textStyle = TextStyle.Default.copy(
                fontSize = 14.sp,
                color = Color(0xff262626)
            ),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFFFFA925),
                focusedLabelColor = Color(0xFFFFA925),
                unfocusedBorderColor = Color(0xFFF6F7FB),
                backgroundColor = Color(0xFFF6F7FB),

                ),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email,
                    contentDescription = "Email Icon",
                    tint = Color(0xFFC5C5C7))
                          },

            placeholder = {
                Text(text = " Enter your email please ", fontSize = 14.sp, color = Color(0xFFCCCCCC))
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),

            )
    }
}