package com.da.chatme

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name="LocalStore")
@HiltAndroidApp
class ChatApplication:Application(){

}