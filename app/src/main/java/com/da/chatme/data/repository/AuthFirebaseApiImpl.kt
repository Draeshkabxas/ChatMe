package com.da.chatme.data.repository

import com.da.chatme.domain.repository.AuthApi
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthFirebaseApiImpl @Inject constructor(
    private val auth:FirebaseAuth
): AuthApi {
    override suspend fun getLogIn(email: String, password: String): AuthResult? =
        auth.signInWithEmailAndPassword(email,password).await()

    override suspend fun getSignUp(email: String, password: String): AuthResult? =
        auth.createUserWithEmailAndPassword(email, password).await()
}