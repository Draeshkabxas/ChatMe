package com.da.chatme.domain.repository

import com.google.firebase.auth.AuthResult

interface AuthApi {
    suspend fun getLogIn(email:String,password:String):AuthResult?

    suspend fun getSignUp(email:String,password:String):AuthResult?
}