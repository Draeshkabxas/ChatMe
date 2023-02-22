package com.da.chatme.Presentation.login.components


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.da.chatme.Presentation.common.components.EmailTextField
import com.da.chatme.Presentation.common.components.HelpQuestion
import com.da.chatme.Presentation.common.components.PasswordTextField
import com.da.chatme.Presentation.common.components.SubmitButton
import com.da.chatme.Presentation.destinations.ChatViewDestination
import com.da.chatme.Presentation.destinations.SingUpScreenDestination
import com.da.chatme.Presentation.login.LogInViewModel
import com.da.chatme.R
import com.da.chatme.common.Resource
import com.da.chatme.domain.model.User
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.AuthResult
import com.google.firebase.database.core.Context
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
@Destination(start = true)
fun LoginScreen(
    nav: DestinationsNavigator,
    logInViewModel: LogInViewModel= hiltViewModel()
) {
    val isLogin=logInViewModel.isLoginIn.collectAsState(initial = false)
    if(isLogin.value){
       nav.navigate(ChatViewDestination)
    }

    var email = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }

    Image(
        modifier = Modifier.fillMaxWidth(),
        painter = painterResource(R.drawable.bg),
        contentDescription = null
    )
    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .height(575.dp),
            shape = RoundedCornerShape(60.dp, 0.dp, 0.dp, 0.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xFFFFFFFF)),
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(52.dp))
                    Text(
                        text = "Log In", color = Color(0xFFFFA925),
                        fontSize = 36.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                    EmailTextField(email = email)
                    Spacer(modifier = Modifier.height(30.dp))
                    PasswordTextField(password = password)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(40.dp, 0.dp), horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(0.dp)) {
                            Text("forgot Password ?", color = Color(0xffffA925), fontSize = 12.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(120.dp))
                    val context= LocalContext.current
                    val result = logInViewModel.authResult
                    SubmitButton(text = "Log In") {
                        logInViewModel.logIn(email.value,password.value)
                    }
                    when (result) {
                        is Resource.Loading<AuthResult> -> {
                        }
                        is Resource.Success<AuthResult> -> {
                            nav.navigate(ChatViewDestination)
                        }
                        is Resource.Error<AuthResult> -> {
                            Toast.makeText(context,result.message,Toast.LENGTH_LONG).show()
                        }
                    }
                    HelpQuestion(
                        questionText = "Don't you have an account?",
                        answerText = "Sign up here"
                    ) {
                        nav.navigate(SingUpScreenDestination)
                    }
                }
            }
        }
    }
}



