package com.da.chatme.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var uid:String="",
    var email:String="",
    var room:List<String> = emptyList()
) : Parcelable
