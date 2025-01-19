package centre.elife.fronted_autoconfiance.DataStoreManager

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import centre.elife.fronted_autoconfiance.data.models.ProfileDetails

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

object DataStoreManager {
    private val Context.dataStore by preferencesDataStore(name = "user_prefs")


    private val NAME_KEY = stringPreferencesKey(name = "name")
    private val LAST_NAME_KEY = stringPreferencesKey(name = "last_name")
    private val ADDRESS_KEY = stringPreferencesKey(name = "address")
    private val EMAIL_KEY = stringPreferencesKey(name = "email")
    private val IS_LOGGED_IN_KEY = booleanPreferencesKey(name = "is_logged_in")
    private val TOKEN_KEY = stringPreferencesKey(name = "token")


    // Method to store user details
    suspend fun storeUserDetails(context: Context, userData: ProfileDetails) {
        context.dataStore.edit { preferences ->
            preferences[NAME_KEY] = userData.name
            preferences[LAST_NAME_KEY] = userData.lastName
            preferences[ADDRESS_KEY] = userData.address
            preferences[EMAIL_KEY] = userData.email
        }
    }

    // Method to retrieve user details
    suspend fun getUserDetails(context: Context): ProfileDetails {
        val preferences = context.dataStore.data.first()
        return ProfileDetails(
            name = preferences[NAME_KEY] ?: "",
            lastName = preferences[LAST_NAME_KEY] ?: "",
            address = preferences[ADDRESS_KEY] ?: "",
            email = preferences[EMAIL_KEY] ?: ""
        )
    }
    suspend fun getEmail(context: Context): String =
        context.dataStore.data.map { preferences -> preferences[EMAIL_KEY] ?: "" }.first()

    suspend fun setEmail(context: Context, email: String) {
        context.dataStore.edit { preferences ->
            preferences[EMAIL_KEY] = email
        }
    }

    suspend fun getToken(context: Context): String =
        context.dataStore.data.map { preferences -> preferences[TOKEN_KEY] ?: "" }.first()

    suspend fun setToken(context: Context, token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    suspend fun isLoggedIn(context: Context): Boolean =
        context.dataStore.data.map { preferences -> preferences[IS_LOGGED_IN_KEY] ?: false }.first()

    suspend fun setLoggedIn(context: Context, isLoggedIn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN_KEY] = isLoggedIn
        }
    }

    suspend fun logout(context: Context) {
        context.dataStore.edit { preferences ->
            preferences.remove(EMAIL_KEY)
            preferences[IS_LOGGED_IN_KEY] = false
        }
    }


}
