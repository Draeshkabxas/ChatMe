package com.da.chatme.firebase

import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.da.chatme.datastore
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ChatAppMessageServer:FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        //Log incoming message
        Log.v("CloudMessage","From ${message.from}")
        //Log Data Payload
        if(message.data.isNotEmpty()){
            Log.v("CloudMessage","Message Data ${message.data}")
        }
        //Check if message contains a notification Payload
        message.data.let {
            Log.v("CloudMessage","Message Notification Body ${it["body"]}")
        }

        if(message.notification != null){
            Log.v("CloudMessage","Notification ${message.notification}")
            Log.v("CloudMessage","Notification Title ${message.notification!!.title}")
            Log.v("CloudMessage","Notification Body ${message.notification!!.body}")
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        GlobalScope.launch {
            saveGCMToken(token)
        }
    }

    //Save GCM Token DataStore Preference
    //you can used to send it on your server.
    private suspend fun saveGCMToken(token: String) {
        val gckTokenKey= stringPreferencesKey("gcm_token")
        baseContext.datastore.edit {pref->
            pref[gckTokenKey] = token
        }
    }
}