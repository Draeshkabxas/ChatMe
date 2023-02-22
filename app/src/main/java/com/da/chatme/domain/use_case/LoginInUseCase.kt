package com.da.chatme.domain.use_case

import android.util.Log
import com.da.chatme.common.Resource
import com.da.chatme.domain.repository.AuthApi
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginInUseCase @Inject constructor(
     val auth: AuthApi
) {
    operator fun invoke(email: String, password: String): Flow<Resource<AuthResult>> = flow{
        emit(Resource.Loading())
        val auth=auth.getLogIn(email, password)!!
        Log.i("XtoX",auth.user!!.email.toString())
            emit(Resource.Success(auth))
    }.catch { emit(Resource.Error("Cloud Not login")) }

}