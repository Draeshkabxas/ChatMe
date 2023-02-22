package com.da.chatme.Presentation.signin

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.da.chatme.common.Resource
import com.da.chatme.domain.model.User
import com.da.chatme.domain.use_case.LoginInUseCase
import com.da.chatme.domain.use_case.SignUpUseCase
import com.da.chatme.domain.use_case.profile.InsertOrUpdateUserUseCase
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    var signUpUseCase: SignUpUseCase,
    var insertOrUpdateUserUseCase: InsertOrUpdateUserUseCase,
    var loginInUseCase: LoginInUseCase
):ViewModel() {

    var auth:Resource<AuthResult> by mutableStateOf(Resource.Loading<AuthResult>())
        private set

    @JvmName("getUser1")
    fun signUp(email:String,password:String) {
       signUpUseCase(email, password).onEach {
           if(it is Resource.Success<AuthResult>){
               logIn(email,password)
           }else{
               auth = it
           }
       }.launchIn(viewModelScope)

    }


    fun insertOrUpdateUser(user: User) {
        insertOrUpdateUserUseCase(user).onEach { result ->
            when (result) {
                is Resource.Loading<Boolean> -> {
                    Log.v("XtoX", "Insert Loading")
                }
                is Resource.Success<Boolean> -> {
                    Log.v("XtoX", "Insert state: " + result.data.toString())
                }
                is Resource.Error<Boolean> -> {
                    Log.v("XtoX", "Not found Inserted")
                }
            }
        }.launchIn(viewModelScope)
    }
    fun logIn(email: String, password: String) {
        loginInUseCase(email, password).onEach { result ->
            auth = result
        }.launchIn(viewModelScope)
    }
}