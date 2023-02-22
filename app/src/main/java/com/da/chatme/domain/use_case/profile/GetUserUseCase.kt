package com.da.chatme.domain.use_case.profile

import com.da.chatme.common.Resource
import com.da.chatme.domain.model.User
import com.da.chatme.domain.repository.UserProfileApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userProfileApi: UserProfileApi
) {
    operator fun invoke(): Flow<Resource<User>> {
        return userProfileApi.getUser()
    }
}