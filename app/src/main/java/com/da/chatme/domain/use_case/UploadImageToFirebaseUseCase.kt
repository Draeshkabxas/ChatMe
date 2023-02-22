package com.da.chatme.domain.use_case

import android.net.Uri
import com.da.chatme.common.Resource
import com.da.chatme.domain.repository.MessagesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UploadImageToFirebaseUseCase @Inject constructor(
    private val messagesApi: MessagesApi
) {
    operator fun invoke(url:Uri): Flow<Resource<String>> =
        messagesApi.uploadPictureToFirebase(url)

}