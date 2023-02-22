package com.da.chatme.Presentation.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HelpQuestion(
    questionText:String,
    answerText:String,
    onAnswerClick:()->Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
        , horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(0.dp, 17.dp),
            text = questionText,
            fontSize = 12.sp
        )
        TextButton(onClick = { onAnswerClick() }, modifier = Modifier.padding(0.dp)) {
            Text(answerText, color = Color(0xffffA925), fontSize = 12.sp)
        }
    }
}