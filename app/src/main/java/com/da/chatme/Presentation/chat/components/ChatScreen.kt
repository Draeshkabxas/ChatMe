package com.da.chatme.Presentation.chat.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.da.chatme.Presentation.chat.ChatViewModel
import com.da.chatme.Presentation.destinations.ChatRoomScreenDestination
import com.da.chatme.R
import com.da.chatme.domain.model.Message
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
@Destination
fun ChatView(
    nav: DestinationsNavigator,
    chatViewModel: ChatViewModel= hiltViewModel()
) {
    
    nav.navigate(ChatRoomScreenDestination)
    val message = chatViewModel.messages.data
    Log.e("XtoX",message.toString())
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .padding(10.dp, 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFFAFAFA),
                    contentColor = Color.White
                ),
                modifier = Modifier
//                .align(Alignment.End)
                    .size(48.dp, 48.dp),
                shape = RoundedCornerShape(15.dp),
                contentPadding = PaddingValues(0.dp, 10.dp, 0.dp, 10.dp)
            ) {
                Icon(Icons.Default.Search, contentDescription = "Search", tint = Color(0xffa3a3a3))
            }

            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = "Home",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,

                color = Color.Black,
            )

            Column() {
                Row(
                ) {

                    Column() {
                        Button(
                            onClick = {
//                            nav.navigate(searchNoteDestination)
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFFFAFAFA),
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .align(Alignment.End)
                                .size(48.dp, 48.dp),
                            shape = RoundedCornerShape(15.dp),
                            contentPadding = PaddingValues(0.dp, 0.dp, 0.dp, 0.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.catx),
                                contentDescription = "Search"
                            )
                        }
                    }

                }

            }
        }


        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(550.dp)
        ) {


            // Add 5 items
            items(8) { index ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp), horizontalArrangement = Arrangement.Center
                ) {
                    Card(
                        modifier = Modifier
                            .size(375.dp, 80.dp)
                    ) {
                        Row(modifier = Modifier.fillMaxSize()) {
                            Column(
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .padding(10.dp)
                                    .align(alignment = Alignment.CenterVertically)
                            ) {
                                Image(
                                    modifier = Modifier.size(48.dp, 48.dp),
                                    painter = painterResource(R.drawable.catx),
                                    contentDescription = "user"
                                )
                            }
                            Column(modifier = Modifier.align(alignment = Alignment.CenterVertically)) {
                                Text(
                                    text = "Alberto dwdfw",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = "say any thing ...",
                                    fontSize = 10.sp,
                                    color = Color(0xffFFA925)
                                )
                            }
                            Spacer(modifier = Modifier.width(130.dp))

                            Column(
                                horizontalAlignment = Alignment.End, modifier = Modifier
                                    .align(alignment = Alignment.CenterVertically)
                                    .fillMaxWidth()
                                    .padding(5.dp)
                            ) {
                                Text(
                                    textAlign = TextAlign.Center,
                                    text = " 2 min ago ",
                                    fontSize = 10.sp,
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                Card(
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier.size(16.dp, 16.dp),
                                    backgroundColor = Color(0xffFFA925)
                                ) {
                                    Text(
                                        textAlign = TextAlign.Center,
                                        text = "1",
                                        fontSize = 10.sp,
                                        color = Color(0xffffffff)
                                    )
                                }

                            }
                        }
                    }
                }
            }


        }


        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 0.dp, 30.dp, 50.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
//                            nav.navigate(searchNoteDestination)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFFFA925),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .size(48.dp, 48.dp),
                shape = RoundedCornerShape(40.dp),
                contentPadding = PaddingValues(0.dp, 0.dp, 0.dp, 0.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.visible),
                    contentDescription = "Chat"
                )
            }
        }
    }

}
