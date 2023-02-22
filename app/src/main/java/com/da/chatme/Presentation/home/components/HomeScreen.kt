package com.da.chatme.Presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.da.chatme.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
@Destination
fun ContactView(nav: DestinationsNavigator){
    Column(modifier = Modifier.fillMaxSize()) {
        Row(verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .padding(10.dp, 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(
                onClick = {
//                            nav.navigate(searchNoteDestination)
                },
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
                                painter = painterResource(R.drawable.cat),
                                contentDescription = "Search"
                            )
                        }
                    }

                }

            }
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween,modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)) {
            Column() {
                Card(shape = RoundedCornerShape(16.5.dp)
                    ,modifier = Modifier.size(116.dp,153.dp), backgroundColor = Color(0xfffafafa)
                ) {

                }
            }
            Column() {
                Card(shape = RoundedCornerShape(16.5.dp),modifier = Modifier.size(116.dp,153.dp), backgroundColor = Color(0xfffafafa)) {

                }
            }
            Column() {
                Card(shape = RoundedCornerShape(16.5.dp),modifier = Modifier.size(116.dp,153.dp), backgroundColor = Color(0xfffafafa)) {

                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Card(modifier = Modifier.size(347.dp,171.dp), backgroundColor = Color(0xfffafafa),shape = RoundedCornerShape(16.5.dp)) {
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp), horizontalArrangement = Arrangement.Center) {
            Column() {
                Card(shape = RoundedCornerShape(16.5.dp),modifier = Modifier.size(103.dp,125.dp), backgroundColor = Color(0xfffafafa)) {

                }
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 0.dp, 0.dp, 0.dp)) {
                Row(modifier = Modifier
                    .size(231.dp, 20.dp)
                    .background(Color(0xfffafafa))) {
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(modifier = Modifier
                    .size(231.dp, 20.dp)
                    .background(Color(0xfffafafa))) {
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(modifier = Modifier
                    .size(231.dp, 20.dp)
                    .background(Color(0xfffafafa))) {
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(modifier = Modifier
                    .size(231.dp, 20.dp)
                    .background(Color(0xfffafafa))) {
                }
            }
        }
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp, 30.dp, 50.dp), verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.End) {
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
                    painter = painterResource(R.drawable.message),
                    contentDescription = "Chat"
                )
            }
        }
    }

}

