package com.da.chatme.Presentation.chat

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.da.chatme.common.Resource
import com.da.chatme.domain.model.Message
import com.da.chatme.domain.use_case.GetMessagesUseCase
import com.da.chatme.domain.use_case.LoginInUseCase
import com.da.chatme.domain.use_case.SendMessageUseCase
import com.da.chatme.domain.use_case.profile.GetUserUseCase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    val auth: FirebaseAuth,
    val sendMessageUseCase: SendMessageUseCase,
    val getMessagesUseCase: GetMessagesUseCase
) : ViewModel() {
    var messages: Resource<List<Message>> by
    mutableStateOf(Resource.Loading<List<Message>>())
        private set

    init {
        getMessages()
    }

    fun isMe(email:String):Boolean = email == auth.currentUser?.email

    fun sendMessage(to:String,message: String){
        viewModelScope.launch {
         sendMessageUseCase(Message(auth.currentUser?.email ?: "",to,message))
        }
    }
    fun getMessages(){
            getMessagesUseCase().onEach {
                messages=it
                Log.i("XtoX","Message ${it.data}")
            }.launchIn(viewModelScope)
        Log.i("XtoX","Message after")
    }

}
