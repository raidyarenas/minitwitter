package dev.raidyarenas.minitwitter.common

import android.content.Context
import android.content.SharedPreferences
import dev.raidyarenas.minitwitter.responses.AuthResponse

object SharedPreferencesManager: SharedPreferences {
    private const val APP_SETTINGS_FILE = "APP_SETTINGS"
    private val sharedPreference: SharedPreferences = App.instance.getSharedPreferences(APP_SETTINGS_FILE, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = edit()

    override fun getAll(): MutableMap<String, *> {
        return sharedPreference.all
    }

    override fun getString(key: String?, defValue: String?): String? {
        return sharedPreference.getString(key, defValue)
    }

    override fun getStringSet(key: String?, defValues: MutableSet<String>?): MutableSet<String> {
        return sharedPreference.getStringSet(key, defValues) as MutableSet<String>
    }

    override fun getInt(key: String?, defValue: Int): Int {
        return sharedPreference.getInt(key, defValue)
    }

    override fun getLong(key: String?, defValue: Long): Long {
        return sharedPreference.getLong(key, defValue)
    }

    override fun getFloat(key: String?, defValue: Float): Float {
        return sharedPreference.getFloat(key, defValue)
    }

    override fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return sharedPreference.getBoolean(key, defValue)
    }

    override fun contains(key: String?): Boolean {
        return sharedPreference.contains(key)
    }

    override fun edit(): SharedPreferences.Editor {
        return sharedPreference.edit()
    }

    override fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        return sharedPreference.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        return sharedPreference.unregisterOnSharedPreferenceChangeListener(listener)
    }

    fun setAuthResponse(data: AuthResponse) {
        editor
                .putString(Constants.PREFERENCE_TOKEN, data.token)
                .putString(Constants.PREFERENCE_USERNAME, data.username)
                .putString(Constants.PREFERENCE_EMAIL, data.email)
                .putString(Constants.PREFERENCE_PHOTO_URL, data.photoUrl)
                .putString(Constants.PREFERENCE_ROLE, data.role)
                .putBoolean(Constants.PREFERENCE_ACTIVE, data.active)
                .putString(Constants.PREFERENCE_CREATED, data.created)
                .commit()
    }
}