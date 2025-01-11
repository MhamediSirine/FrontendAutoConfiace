package centre.elife.fronted_autoconfiance.DataStoreManager

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

object DataStoreManager {
    private val Context.dataStore by preferencesDataStore(name = "user_prefs")

    private val EMAIL_KEY = stringPreferencesKey(name = "email")
    private val IS_LOGGED_IN_KEY = booleanPreferencesKey(name = "is_logged_in")

    suspend fun getEmail(context: Context): String =
        context.dataStore.data.map { preferences -> preferences[EMAIL_KEY] ?: "" }.first()

    suspend fun setEmail(context: Context, email: String) {
        context.dataStore.edit { preferences ->
            preferences[EMAIL_KEY] = email
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
