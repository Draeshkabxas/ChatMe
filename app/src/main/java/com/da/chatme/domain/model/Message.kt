package com.da.chatme.domain.model

import android.provider.ContactsContract.CommonDataKinds.Email
import java.util.Date

data class Message(
    val email:String="",
    val to:String="",
    val message:String="",
    var pictureUrl:String=""
)
