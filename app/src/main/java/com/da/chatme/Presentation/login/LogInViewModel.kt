package com.da.chatme.Presentation.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.da.chatme.common.Resource
import com.da.chatme.domain.model.User
import com.da.chatme.domain.use_case.LoginInUseCase
import com.da.chatme.domain.use_case.profile.GetUserUseCase
import com.da.chatme.domain.use_case.profile.InsertOrUpdateUserUseCase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    var auth:FirebaseAuth,
    var loginInUseCase: LoginInUseCase,
    var getUserUseCase: GetUserUseCase
) : ViewModel() {
    var authResult: Resource<AuthResult> by mutableStateOf(Resource.Loading<AuthResult>())
        private set

    var isLoginIn: Flow<Boolean> = flow {
        if(auth.currentUser != null){
            emit(true)
        }else{
            emit(false)
        }
    }

    fun logIn(email: String, password: String) {
        loginInUseCase(email, password).onEach { result ->
            authResult = result
        }.launchIn(viewModelScope)
    }

    fun getUser() {
        getUserUseCase().onEach { result ->
            when (result) {
                is Resource.Loading<User> -> {
                    Log.v("XtoX", "User Loading")
                }
                is Resource.Success<User> -> {
                    Log.v("XtoX", result.data.toString())
                }
                is Resource.Error<User> -> {
                    Log.v("XtoX", "Not found User")
                }
            }
        }.launchIn(viewModelScope)
    }
}
