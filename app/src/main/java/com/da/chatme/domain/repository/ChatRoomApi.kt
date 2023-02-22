package com.da.chatme.domain.repository

import com.da.chatme.common.Resource
import com.da.chatme.domain.model.ChatRoom
import com.da.chatme.domain.model.Message
import com.da.chatme.domain.model.User
import kotlinx.coroutines.flow.Flow

interface ChatRoomApi {

    suspend fun createRoom(friend:User): Flow<Resource<Boolean>>

    suspend fun getRooms(user:User): Flow<Resource<List<ChatRoom>>>
}