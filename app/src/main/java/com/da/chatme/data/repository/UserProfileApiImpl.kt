package com.da.chatme.data.repository

import android.util.Log
import com.da.chatme.common.Contacts.ERROR_MESSAGE
import com.da.chatme.common.Resource
import com.da.chatme.domain.model.ChatRoom
import com.da.chatme.domain.model.User
import com.da.chatme.domain.repository.UserProfileApi
import com.google.android.gms.common.api.Response
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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject

class UserProfileApiImpl @Inject constructor(
   private val auth:FirebaseAuth,
   private val database:FirebaseDatabase
): UserProfileApi {
    override  fun getUser(): Flow<Resource<User>> = callbackFlow {
        try {
            this@callbackFlow.trySendBlocking(Resource.Loading())
            val userUUID = auth.currentUser?.uid
            val databaseReference = database.getReference("Profiles")
            val postListener = databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val before=snapshot.child(userUUID!!).child("profile")
                    val userFromFirebaseDatabase =before
                        .getValue(User::class.java)
                            ?: User()
                    this@callbackFlow.trySendBlocking(Resource.Success(userFromFirebaseDatabase))

                }

                override fun onCancelled(error: DatabaseError) {
                    this@callbackFlow.trySendBlocking(Resource.Error(error.message))
                }
            })
            databaseReference.addValueEventListener(postListener)
            awaitClose {
                databaseReference.removeEventListener(postListener)
                channel.close()
                cancel()
            }
        } catch (e: Exception) {
            this@callbackFlow.trySendBlocking(Resource.Error(e.message ?: ERROR_MESSAGE))
        }
    }

    override  fun insertUpdateUser(user: User):Flow<Resource<Boolean>> =
    flow {
            emit(Resource.Loading())
            val userUUID = auth.currentUser?.uid.toString()
            val userEmail = auth.currentUser?.email.toString()

            val databaseReference =
                database.getReference("Profiles").child(userUUID).child("profile")

            val childUpdates = mutableMapOf<String, Any>()

            childUpdates["/uid/"] = userUUID

            childUpdates["/email/"] = userEmail

            childUpdates["/room/"] = ""

            databaseReference.updateChildren(childUpdates).await()
            emit(Resource.Success(true))
        }.catch () {
            emit(Resource.Error(it.message ?: ERROR_MESSAGE))
        }


    override  fun deleteUser(user: User) {
        TODO("Not yet implemented")
    }
}