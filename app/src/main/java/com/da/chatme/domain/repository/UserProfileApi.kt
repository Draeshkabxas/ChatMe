package com.da.chatme.domain.repository

import com.da.chatme.common.Resource
import com.da.chatme.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserProfileApi {

     fun getUser(): Flow<Resource<User>>

     fun insertUpdateUser(user: User): Flow<Resource<Boolean>>

     fun deleteUser(user:User)
}