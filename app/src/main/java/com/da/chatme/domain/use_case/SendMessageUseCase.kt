package com.da.chatme.domain.use_case

import com.da.chatme.common.Resource
import com.da.chatme.domain.model.Message
import com.da.chatme.domain.repository.AuthApi
import com.da.chatme.domain.repository.MessagesApi
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    val messagesApi:MessagesApi
) {
    suspend operator fun invoke(message: Message){
        messagesApi.sendMessage(message)
    }
}