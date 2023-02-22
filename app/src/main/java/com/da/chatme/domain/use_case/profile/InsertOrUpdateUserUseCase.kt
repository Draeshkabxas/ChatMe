package com.da.chatme.domain.use_case.profile

import com.da.chatme.common.Resource
import com.da.chatme.domain.model.User
import com.da.chatme.domain.repository.UserProfileApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InsertOrUpdateUserUseCase @Inject constructor(
    private val userProfileApi: UserProfileApi
){
    operator fun invoke(user: User): Flow<Resource<Boolean>> {
        return userProfileApi.insertUpdateUser(user)
    }
}