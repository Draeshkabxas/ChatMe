package com.da.chatme.domain.repository

import android.net.Uri
import com.da.chatme.common.Resource
import com.da.chatme.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface MessagesApi {

    suspend fun sendMessage(message: Message)

     fun getMessages(): Flow<Resource<List<Message>>>

     fun uploadPictureToFirebase(url: Uri): Flow<Resource<String>>

}