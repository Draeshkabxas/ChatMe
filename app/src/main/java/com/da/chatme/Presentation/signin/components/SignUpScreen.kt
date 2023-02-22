package com.da.chatme.Presentation.signin.components

import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.da.chatme.Presentation.common.components.EmailTextField
import com.da.chatme.Presentation.common.components.HelpQuestion
import com.da.chatme.Presentation.common.components.PasswordTextField
import com.da.chatme.Presentation.common.components.SubmitButton
import com.da.chatme.Presentation.destinations.ChatViewDestination
import com.da.chatme.Presentation.destinations.LoginScreenDestination
import com.da.chatme.Presentation.destinations.SingUpScreenDestination
import com.da.chatme.Presentation.signin.SignUpViewModel
import com.da.chatme.R
import com.da.chatme.common.Resource
import com.da.chatme.domain.model.User
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.AuthResult
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun SingUpScreen(
    nav: DestinationsNavigator,
    signUpViewModel: SignUpViewModel = hiltViewModel()
) {
//    var textShopName by remember { mutableStateOf("") }
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
                        text = "Sign Up", color = Color(0xFFFFA925),
                        fontSize = 36.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                    EmailTextField(email = email)
                    Spacer(modifier = Modifier.height(30.dp))
                    PasswordTextField(password = password)
                    Spacer(modifier = Modifier.height(140.dp))
                    SubmitButton(text = "Sign Up") {
                        signUpViewModel.insertOrUpdateUser(User())
                        signUpViewModel.signUp(email.value,password.value)
                    }
                    when (signUpViewModel.auth) {
                        is Resource.Loading<AuthResult> -> {
                            Log.v("XtoX", "User Loading")
                        }
                        is Resource.Success<AuthResult> -> {
                            nav.navigate(ChatViewDestination)
                        }
                        is Resource.Error<AuthResult> -> {
                            Log.v("XtoX", "Not found User")
                        }
                    }
                    HelpQuestion(
                        questionText = "Do you have an account?",
                        answerText = "Login here"
                    ) {
                        nav.navigate(LoginScreenDestination)
                    }
                }
            }
        }
    }
}
