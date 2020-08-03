package fr.test.cyllene.utils

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferences @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun saveUsername(key: String?, value: String) {
        sharedPreferences.edit()
            .putString(key, value)
            .apply()
    }

    fun getUsername(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    fun saveLoginStatus(value: Boolean) {
        sharedPreferences.edit()
            .putBoolean(Constants.LOGIN, value)
            .apply()
    }

    fun getLoginStatus(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

}