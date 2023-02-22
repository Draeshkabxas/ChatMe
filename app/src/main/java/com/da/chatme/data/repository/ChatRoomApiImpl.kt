package com.da.chatme.data.repository

import com.da.chatme.common.Contacts
import com.da.chatme.common.Resource
import com.da.chatme.domain.model.ChatRoom
import com.da.chatme.domain.model.Message
import com.da.chatme.domain.model.User
import com.da.chatme.domain.repository.ChatRoomApi
import com.da.chatme.domain.repository.MessagesApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject

class ChatRoomApiImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase
): ChatRoomApi {
    override suspend fun createRoom(friend: User): Flow<Resource<Boolean>> =
        flow {
            emit(Resource.Loading())
            val roomUid=UUID.randomUUID()
            val userUUID = auth.currentUser?.uid.toString()
            val userEmail = auth.currentUser?.email.toString()

            val databaseReference =
                database.getReference("Rooms").child(roomUid.toString())

            val childUpdates = mutableMapOf<String, Any>()

            childUpdates["/id/"] = roomUid

            childUpdates["/from/"] = userUUID

            childUpdates["/to/"] = friend.uid

            childUpdates["/to/"] = friend.uid

            databaseReference.updateChildren(childUpdates).await()
            emit(Resource.Success(true))
        }.catch () {
            emit(Resource.Error(it.message ?: Contacts.ERROR_MESSAGE))
        }

    override suspend fun getRooms(user: User): Flow<Resource<List<ChatRoom>>> {
        TODO("Not yet implemented")
    }

}