package com.da.chatme.domain.model

import java.util.UUID

data class ChatRoom(
    val id:String ="",
    val from:String="",
    val to:String = "",
    val messages:List<Message> = emptyList()
)