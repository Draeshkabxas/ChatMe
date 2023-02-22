package com.da.chatme.data.repository

import android.util.Log
import com.da.chatme.common.Resource
import com.da.chatme.domain.model.Message
import com.da.chatme.domain.repository.MessagesApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MessagesApiImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase
) : MessagesApi {
    override suspend fun sendMessage(message: Message) {
        if (message.message.isNotBlank()){
            Log.i("XtoX","send $message")
            val messageRef = database.getReference("messages").push()
            messageRef.setValue(message)
        }
    }

    override  fun getMessages(): Flow<Resource<List<Message>>> = callbackFlow {
        try {
            val messageList = mutableListOf<Message>()
            val msgListener = (object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    messageList.clear()
                    this@callbackFlow.trySendBlocking(Resource.Loading<List<Message>>())
                    snapshot.children.forEach { message ->
                        val from = message.child("email").getValue(String::class.java).toString()
                        val to = message.child("to").getValue(String::class.java).toString()
                        val message =
                            message.child("message").getValue(String::class.java).toString()
                        messageList.add(Message(from, to, message))
                    }
                    this@callbackFlow.trySendBlocking(Resource.Success<List<Message>>(messageList))
                }

                override fun onCancelled(error: DatabaseError) {
                    error.toException().printStackTrace()
                    this@callbackFlow.trySendBlocking(Resource.Success<List<Message>>(messageList))
                }

            })
            database.getReference("messages").addValueEventListener(msgListener)
            this@callbackFlow.trySendBlocking(Resource.Success<List<Message>>(messageList))
            awaitClose {
                database.getReference("messages").removeEventListener(msgListener)
                channel.close()
                cancel()
            }
        }catch (e:Exception){
            this@callbackFlow.trySendBlocking(Resource.Error<List<Message>>("error"))
        }
    }
}