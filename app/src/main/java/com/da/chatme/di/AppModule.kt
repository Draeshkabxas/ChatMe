package com.da.chatme.di

import android.accounts.AuthenticatorDescription
import android.app.Application
import androidx.room.Room
import com.da.chatme.data.repository.AuthFirebaseApiImpl
import com.da.chatme.data.repository.MessagesApiImpl
import com.da.chatme.data.repository.UserProfileApiImpl
import com.da.chatme.domain.repository.AuthApi
import com.da.chatme.domain.repository.MessagesApi
import com.da.chatme.domain.repository.UserProfileApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun makeAuth():FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun makeAuthApi(auth:FirebaseAuth): AuthApi {
        return AuthFirebaseApiImpl(auth)
    }



    @Provides
    @Singleton
    fun makeFirebaseDatabase()=
        Firebase.database

    @Provides
    @Singleton
    fun makeFirebaseStorage()=
        FirebaseStorage.getInstance()


    @Provides
    @Singleton
    fun makeUserProfileApi(auth:FirebaseAuth,db:FirebaseDatabase): UserProfileApi {
        return UserProfileApiImpl(auth,db)
    }


    @Provides
    @Singleton
    fun makeMessageApi(storage: FirebaseStorage,auth:FirebaseAuth,db:FirebaseDatabase): MessagesApi {
        return MessagesApiImpl(storage,auth,db)
    }

}