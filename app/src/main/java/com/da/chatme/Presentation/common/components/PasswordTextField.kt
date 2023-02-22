package com.da.chatme.Presentation.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.da.chatme.R

@Composable
fun PasswordTextField(
    password:MutableState<String>
){
    var showPassword by remember {
        mutableStateOf(false)
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        OutlinedTextField(
            modifier = Modifier.size(310.dp,58.dp),
            value = password.value, onValueChange = { password.value = it },
            textStyle = TextStyle.Default.copy(
                fontSize = 14.sp,
                color = Color(0xFF262626)
            ),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(

                focusedBorderColor = Color(0xFFFFA925),
                unfocusedBorderColor = Color(0xFFF6F7FB),
                backgroundColor = Color(0xFFF6F7FB),
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Lock Icon", tint = Color(0xFFC5C5C7)
                )
            },

            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        painter = if (showPassword) painterResource(R.drawable.visible) else painterResource(
                            R.drawable.eye),
                        contentDescription = if (showPassword) "Show Password" else "Hide Password",
                        modifier = Modifier.size(18.dp)
                    )
                }
            },
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),

            placeholder = {
                Text(text = "Enter your password please", fontSize = 14.sp, color = Color(0xFFCCCCCC))
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),

            )
    }
}