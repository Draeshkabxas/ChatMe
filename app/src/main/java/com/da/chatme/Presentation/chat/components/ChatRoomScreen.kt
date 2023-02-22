package com.da.chatme.Presentation.chat.components


import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.da.chatme.Presentation.chat.ChatViewModel
import com.da.chatme.R
import com.da.chatme.domain.model.Message
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
@Destination
fun ChatRoomScreen(
    nav: DestinationsNavigator,
    chatViewModel: ChatViewModel = hiltViewModel()

) {

    var sendMessage = rememberSaveable { mutableStateOf("") }
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val scope = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column() {
                Row() {
                    Column() {
                        Button(
                            onClick = {

                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFFFAFAFA),
                                contentColor = androidx.compose.ui.graphics.Color.White
                            ),
                            modifier = Modifier
                                .align(Alignment.End)
                                .width(48.dp)
                                .height(48.dp),
                            shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp),
                            contentPadding = PaddingValues(0.dp, 10.dp, 0.dp, 10.dp)
                        ) {
//                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                            Icon(
                                painter = painterResource(R.drawable.back),
                                tint = Color(0xFFA3A3A3),
                                contentDescription = "Back"
                            )
                        }
                    }

                    Spacer(modifier = Modifier.size(10.dp))
                    Box() {
                        Image(
                            painter = painterResource(R.drawable.catx),
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp),
                            contentDescription = "Profile Pic"
                        )
                        Icon(
                            modifier = Modifier
                                .padding(40.dp, 0.dp, 0.dp, 0.dp)
                                .width(10.dp)
                                .height(10.dp),
                            painter = painterResource(R.drawable.catx),
                            tint = Color(0xFF22C55E),
                            contentDescription = "Online"
                        )
                    }

                    Spacer(modifier = Modifier.size(15.dp, 15.dp))
                    Column(modifier = Modifier.padding(0.dp, 5.dp, 0.dp, 0.dp)) {
                        Text(
                            chatViewModel.getEmail(),
                            fontSize = 14.sp,
                            color = androidx.compose.ui.graphics.Color.Black
                        )
                        Text(
                            "Online now",
                            fontSize = 12.sp,
                            color = androidx.compose.ui.graphics.Color(0xFFFFA925)
                        )
                    }

                }


            }




            Column() {
                Row() {

                    Column() {
                        Button(
                            onClick = { },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFFFAFAFA),
                                contentColor = androidx.compose.ui.graphics.Color.White
                            ),
                            modifier = Modifier
                                .align(Alignment.End)
                                .width(48.dp)
                                .height(48.dp),
                            shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp),
                            contentPadding = PaddingValues(0.dp, 10.dp, 0.dp, 10.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.video),
                                tint = Color(0xFFA3A3A3),
                                contentDescription = "Call"
                            )
                        }
                    }
                    Spacer(modifier = Modifier.size(10.dp, 10.dp))
                    Column() {
                        Button(
                            onClick = { },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFFFAFAFA),
                                contentColor = androidx.compose.ui.graphics.Color.White
                            ),
                            modifier = Modifier
                                .align(Alignment.End)
                                .width(48.dp)
                                .height(48.dp),
                            shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp),
                            contentPadding = PaddingValues(10.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.phone),
                                tint = Color(0xFFA3A3A3),
                                contentDescription = "Video"
                            )
                        }
                    }
                }
            }
        }


        val messages = chatViewModel.messages.data ?: emptyList()

        val scrollState = rememberLazyListState()


        LazyColumn(
            modifier = Modifier
                .padding(0.dp, 0.dp, 0.dp, 0.dp)
                .weight(1f)
                .fillMaxSize(),
            state = scrollState
        ) {

            itemsIndexed(messages) { i, message ->
                if (chatViewModel.isMe(message.email)) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(5.dp),
                            backgroundColor = Color(0xFFF5F5F5),
                            shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 0.dp)
                        ) {
                            Column(modifier = Modifier.padding(20.dp)) {
                                Row {
                                    Text(text = message.message, fontSize = 12.sp)
                                }
                                AsyncImage(
                                    model = message.pictureUrl,
                                    contentDescription = "image",
                                    modifier = Modifier.size(200.dp)
                                )
                            }
                        }

                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(5.dp),
                            backgroundColor = Color(0xFFFFA925),
                            shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 20.dp)
                        ) {
                            Column(modifier = Modifier.padding(20.dp)) {
                                Row() {
                                    Text(text = message.message, fontSize = 12.sp)
                                }
                                AsyncImage(
                                    model = message.pictureUrl,
                                    contentDescription = "image",
                                    modifier = Modifier.size(200.dp)
                                )
                            }
                        }

                    }
                }
            }
        }
        if (messages.isNotEmpty()) {
            scrollToEnd(scope, scrollState, messages.size - 1)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFAFAFA)),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Row() {


                    val imageLauncher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.PickVisualMedia(),
                        onResult = { uri ->
                            imageUri = uri
                        }
                    )

                    Column() {
                        Row() {
                            TextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = sendMessage.value,
                                onValueChange = { sendMessage.value = it },
                                textStyle = TextStyle.Default.copy(
                                    fontSize = 14.sp,
                                    color = androidx.compose.ui.graphics.Color.Black
                                ),

                                colors = TextFieldDefaults.textFieldColors(
                                    textColor = Color.Gray,
                                    disabledTextColor = Color.Transparent,
                                    backgroundColor = Color(0xFFFAFAFA),
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent
                                ),
                                placeholder = {
                                    Text(
                                        text = "Type something..",
                                        fontSize = 14.sp,
                                        color = Color(0xFF9A9A9A),
                                        modifier = Modifier.padding(5.dp, 0.dp, 0.dp, 0.dp)
                                    )
                                },
                                leadingIcon = {
                                    Button(
                                        onClick = {
                                            imageLauncher.launch(
                                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo)
                                            )
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = Color(0xFFF5F5F5),
                                            contentColor = androidx.compose.ui.graphics.Color.White
                                        ),
                                        modifier = Modifier
                                            .width(48.dp)
                                            .height(48.dp),
                                        shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp),
                                        contentPadding = PaddingValues(10.dp)
                                    ) {
                                        Icon(
                                            painter = painterResource(R.drawable.plus),
                                            tint = Color(0xFFFFA925),
                                            contentDescription = "Plus"
                                        )
                                    }
                                },

                                trailingIcon = {
                                    Button(
                                        onClick = {
                                            scrollToEnd(scope, scrollState, messages.size - 1)
                                            if (imageUri != null) {
                                                chatViewModel.uploadImage(imageUri!!,"hi",sendMessage.value)
                                                imageUri=null
                                            } else {
                                                chatViewModel.sendMessage(
                                                    "toYou",
                                                    sendMessage.value
                                                )
                                            }

                                            sendMessage.value = ""
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = Color(0xFFF5F5F5),
                                            contentColor = androidx.compose.ui.graphics.Color.White
                                        ),
                                        modifier = Modifier
                                            .width(48.dp)
                                            .height(48.dp),
                                        shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp),
                                        contentPadding = PaddingValues(10.dp)
                                    ) {
                                        Icon(
                                            painter = painterResource(R.drawable.upload),
                                            tint = Color(0xFFFFA925),
                                            contentDescription = "Send"
                                        )
                                    }
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}


fun scrollToEnd(scope: CoroutineScope, scrollState: LazyListState, x: Int) {
    scope.launch {
        if(x >0) {
            scrollState.animateScrollToItem(x, 0)
        }
    }
}