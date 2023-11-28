package ru.sergean.userpaymentsapp.data.auth

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val DATASTORE_NAME = "auth_preferences"
private val Context.dataStore by preferencesDataStore(name = DATASTORE_NAME)

class AuthPreferences @Inject constructor(@ApplicationContext private val context: Context) {

    private val dataStore = context.dataStore

    private val loggedKey = booleanPreferencesKey(name = loggedKeyName)
    private val tokenKey = stringPreferencesKey(name = tokenKeyName)

    val logged: Flow<Boolean> = dataStore.data.map { preferences -> preferences[loggedKey] ?: false }
    val token: Flow<String?> = dataStore.data.map { preferences -> preferences[tokenKey]?.ifEmpty { null } }

    suspend fun login() = changeLogged(logged = true)

    suspend fun logout() = changeLogged(logged = false)

    suspend fun clearToken() = changeToken(token = emptyToken)

    suspend fun saveToken(token: String) = changeToken(token = token)

    private suspend fun changeLogged(logged: Boolean) {
        dataStore.edit { preferences ->
            preferences[loggedKey] = logged
        }
    }

    private suspend fun changeToken(token: String) {
        dataStore.edit { preferences ->
            preferences[tokenKey] = token
        }
    }

    companion object {
        private const val loggedKeyName = "logged"
        private const val tokenKeyName = "token"
        private const val emptyToken = ""
    }
}
